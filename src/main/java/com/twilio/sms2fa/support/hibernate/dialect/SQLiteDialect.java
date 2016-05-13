package com.twilio.sms2fa.support.hibernate.dialect;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.AbstractAnsiTrimEmulationFunction;
import org.hibernate.dialect.function.NoArgSQLFunction;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.LockAcquisitionException;
import org.hibernate.exception.spi.SQLExceptionConverter;
import org.hibernate.exception.spi.TemplatedViolatedConstraintNameExtracter;
import org.hibernate.exception.spi.ViolatedConstraintNameExtracter;
import org.hibernate.type.StandardBasicTypes;

import java.sql.SQLException;
import java.sql.Types;

public class SQLiteDialect extends Dialect {

    @Override
    public String getAddColumnString() {
        return super.getAddColumnString();
    }

    public static final String NO_PRIMARY_KEY_SYNTAX =
            "No add primary key syntax supported by SQLiteDialect";
    public static final String NO_FOREIGN_KEY_SYNTAX =
            "No add foreign key syntax supported by SQLiteDialect";
    public static final String NO_DROP_FOREIGN_KEY_SYNTAX =
            "No drop foreign key syntax supported by SQLiteDialect";

    public SQLiteDialect() {
        registerColumnType(Types.BIT, "boolean");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.REAL, "real");
        registerColumnType(Types.DOUBLE, "double");
        registerColumnType(Types.NUMERIC, "numeric($p, $s)");
        registerColumnType(Types.DECIMAL, "decimal");
        registerColumnType(Types.CHAR, "char");
        registerColumnType(Types.VARCHAR, "varchar($l)");
        registerColumnType(Types.LONGVARCHAR, "longvarchar");
        registerColumnType(Types.DATE, "date");
        registerColumnType(Types.TIME, "time");
        registerColumnType(Types.TIMESTAMP, "datetime");
        registerColumnType(Types.BINARY, "blob");
        registerColumnType(Types.VARBINARY, "blob");
        registerColumnType(Types.LONGVARBINARY, "blob");
        registerColumnType(Types.BLOB, "blob");
        registerColumnType(Types.CLOB, "clob");
        registerColumnType(Types.BOOLEAN, "boolean");

        registerFunction("concat", new VarArgsSQLFunction(
                StandardBasicTypes.STRING, "", "||", ""));
        registerFunction("mod", new SQLFunctionTemplate(
                StandardBasicTypes.INTEGER, "?1 % ?2"));
        registerFunction("quote", new StandardSQLFunction("quote",
                StandardBasicTypes.STRING));
        registerFunction("random", new NoArgSQLFunction("random",
                StandardBasicTypes.INTEGER));
        registerFunction("round", new StandardSQLFunction("round"));
        registerFunction("substr", new StandardSQLFunction("substr",
                StandardBasicTypes.STRING));
        registerFunction("trim", new AbstractAnsiTrimEmulationFunction() {
            protected SQLFunction resolveBothSpaceTrimFunction() {
                return new SQLFunctionTemplate(StandardBasicTypes.STRING,
                        "trim(?1)");
            }

            protected SQLFunction resolveBothSpaceTrimFromFunction() {
                return new SQLFunctionTemplate(StandardBasicTypes.STRING,
                        "trim(?2)");
            }

            protected SQLFunction resolveLeadingSpaceTrimFunction() {
                return new SQLFunctionTemplate(StandardBasicTypes.STRING,
                        "ltrim(?1)");
            }

            protected SQLFunction resolveTrailingSpaceTrimFunction() {
                return new SQLFunctionTemplate(StandardBasicTypes.STRING,
                        "rtrim(?1)");
            }

            protected SQLFunction resolveBothTrimFunction() {
                return new SQLFunctionTemplate(StandardBasicTypes.STRING,
                        "trim(?1, ?2)");
            }

            protected SQLFunction resolveLeadingTrimFunction() {
                return new SQLFunctionTemplate(StandardBasicTypes.STRING,
                        "ltrim(?1, ?2)");
            }

            protected SQLFunction resolveTrailingTrimFunction() {
                return new SQLFunctionTemplate(StandardBasicTypes.STRING,
                        "rtrim(?1, ?2)");
            }
        });
    }

    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public boolean bindLimitParametersInReverseOrder() {
        return true;
    }

    @Override
    protected String getLimitString(final String query,
                                    final boolean hasOffset) {
        return query + (hasOffset ? " limit ? offset ?" : " limit ?");
    }

    @Override
    public boolean supportsCurrentTimestampSelection() {
        return true;
    }

    @Override
    public boolean isCurrentTimestampSelectStringCallable() {
        return false;
    }

    @Override
    public String getCurrentTimestampSelectString() {
        return "select current_timestamp";
    }

    private static final int SQLITE_BUSY = 5;
    private static final int SQLITE_LOCKED = 6;
    private static final int SQLITE_IOERR = 10;
    private static final int SQLITE_CORRUPT = 11;
    private static final int SQLITE_NOTFOUND = 12;
    private static final int SQLITE_FULL = 13;
    private static final int SQLITE_CANTOPEN = 14;
    private static final int SQLITE_PROTOCOL = 15;
    private static final int SQLITE_TOOBIG = 18;
    private static final int SQLITE_CONSTRAINT = 19;
    private static final int SQLITE_MISMATCH = 20;
    private static final int SQLITE_NOTADB = 26;

    @Override
    public SQLExceptionConverter buildSQLExceptionConverter() {
        return (SQLExceptionConverter) (sqlException, message, sql) -> {

            final int errorCode = sqlException.getErrorCode();
            if (errorCode == SQLITE_CONSTRAINT) {
                final String constraintName = EXTRACTER
                        .extractConstraintName(sqlException);
                return new ConstraintViolationException(message, sqlException,
                        sql, constraintName);
            } else if (errorCode == SQLITE_TOOBIG
                    || errorCode == SQLITE_MISMATCH) {
                return new DataException(message, sqlException, sql);
            } else if (errorCode == SQLITE_BUSY || errorCode == SQLITE_LOCKED) {
                return new LockAcquisitionException(message, sqlException, sql);
            } else if ((errorCode >= SQLITE_IOERR
                    && errorCode <= SQLITE_PROTOCOL)
                    || errorCode == SQLITE_NOTADB) {
                return new JDBCConnectionException(message, sqlException, sql);
            }
            return new GenericJDBCException(message, sqlException, sql);
        };
    }

    public static final ViolatedConstraintNameExtracter EXTRACTER =
            new TemplatedViolatedConstraintNameExtracter() {
        public String extractConstraintName(final SQLException sqle) {
            return extractUsingTemplate("constraint ", " failed",
                    sqle.getMessage());
        }

        @Override
        protected String doExtractConstraintName(final SQLException sqle)
                throws NumberFormatException {
            return null;
        }
    };

    @Override
    public boolean supportsUnionAll() {
        return true;
    }

    @Override
    public boolean hasAlterTable() {
        return false;
    }

    @Override
    public boolean dropConstraints() {
        return false;
    }

    @Override
    public String getForUpdateString() {
        return "";
    }

    @Override
    public boolean supportsOuterJoinForUpdate() {
        return false;
    }

    @Override
    public String getDropForeignKeyString() {
        throw new UnsupportedOperationException(NO_DROP_FOREIGN_KEY_SYNTAX);
    }

    @Override
    public String getAddForeignKeyConstraintString(
            final String constraintName,
            final String[] foreignKey,
            final String referencedTable,
            final String[] primaryKey,
            final boolean referencesPrimaryKey) {
        throw new UnsupportedOperationException(NO_FOREIGN_KEY_SYNTAX);
    }

    @Override
    public String getAddPrimaryKeyConstraintString(
            final String constraintName) {
        throw new UnsupportedOperationException(NO_PRIMARY_KEY_SYNTAX);
    }

    @Override
    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }

    @Override
    public boolean supportsTupleDistinctCounts() {
        return false;
    }

    @Override
    public String getSelectGUIDString() {
        return "select hex(randomblob(16))";
    }
}
