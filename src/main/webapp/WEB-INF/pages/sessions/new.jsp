<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>SMS 2 Factor Authentication</title>
    <meta content="width=device-width, initial-scale=1, user-scalable=no" name="viewport"/>
    <link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/bootswatch/3.3.6/paper/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/application.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/turbolinks/1.3.0/turbolinks.min.js"></script>
  </head>
  <body>
    <div class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand" href="/"> Control <i class="fa fa-globe"></i> Kaos</a>
          <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
          <ul class="nav navbar-nav navbar-right">
              <c:choose>
                <c:when test="${authenticated == true}">
                  <li>
                    <a href="/logout/">Log out</a>
                  </li>
                </c:when>
                <c:otherwise>
                  <li>
                    <a href="/users/new/">Sign Up</a>
                  </li>
                  <li>
                  <a href="/sessions/new/">Sign In</a>
                  </li>
                </c:otherwise>
              </c:choose>

          </ul>
        </div>
      </div>
    </div>

    <c:if test="${errorMessage != null}">
        <div class="alert.alert-success.alert-dismissible" role="alert"
          <button class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true"> &times;</span>
          </button>
        </div>
    </c:if>

    <div class="container">
        <h1>Welcome back</h1>
        <p>You never write. You never call. But we're glad you came, regardless! Be a pal and confirm your username and password, would you?</p>
        <form class="form-horizontal" action="/sessions/" accept-charset="UTF-8" method="post" _lpchecked="1">
            <div class="form-group">
              <label class="col-md-2 control-label" for="email">Email</label>
              <div class="col-sm-10">
                <input type="text" name="email" id="email" class="form-control" style="cursor: auto; background-image: url(&quot;data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAASCAYAAABSO15qAAAAAXNSR0IArs4c6QAAAPhJREFUOBHlU70KgzAQPlMhEvoQTg6OPoOjT+JWOnRqkUKHgqWP4OQbOPokTk6OTkVULNSLVc62oJmbIdzd95NcuGjX2/3YVI/Ts+t0WLE2ut5xsQ0O+90F6UxFjAI8qNcEGONia08e6MNONYwCS7EQAizLmtGUDEzTBNd1fxsYhjEBnHPQNG3KKTYV34F8ec/zwHEciOMYyrIE3/ehKAqIoggo9inGXKmFXwbyBkmSQJqmUNe15IRhCG3byphitm1/eUzDM4qR0TTNjEixGdAnSi3keS5vSk2UDKqqgizLqB4YzvassiKhGtZ/jDMtLOnHz7TE+yf8BaDZXA509yeBAAAAAElFTkSuQmCC&quot;); background-attachment: scroll; background-size: 16px 18px; background-position: 98% 50%; background-repeat: no-repeat;">
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-2 control-label" for="password">Password</label>
              <div class="col-sm-10">
                <input type="password" name="password" id="password" class="form-control" style="cursor: auto; background-image: url(&quot;data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAASCAYAAABSO15qAAAAAXNSR0IArs4c6QAAAPhJREFUOBHlU70KgzAQPlMhEvoQTg6OPoOjT+JWOnRqkUKHgqWP4OQbOPokTk6OTkVULNSLVc62oJmbIdzd95NcuGjX2/3YVI/Ts+t0WLE2ut5xsQ0O+90F6UxFjAI8qNcEGONia08e6MNONYwCS7EQAizLmtGUDEzTBNd1fxsYhjEBnHPQNG3KKTYV34F8ec/zwHEciOMYyrIE3/ehKAqIoggo9inGXKmFXwbyBkmSQJqmUNe15IRhCG3byphitm1/eUzDM4qR0TTNjEixGdAnSi3keS5vSk2UDKqqgizLqB4YzvassiKhGtZ/jDMtLOnHz7TE+yf8BaDZXA509yeBAAAAAElFTkSuQmCC&quot;); background-attachment: scroll; background-size: 16px 18px; background-position: 98% 50%; background-repeat: no-repeat;">
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <input type="submit" name="commit" value="Sign In" class="btn btn-success">
              </div>
            </div>
      </form>

    </div>
    <footer class="container">
      Made with <i class="fa fa-heart"></i> by your pals <a href="http://www.twilio.com">@twilio</a>
    </footer>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  </body>
</html>
