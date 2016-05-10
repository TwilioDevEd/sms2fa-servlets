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
              <li>
                <a href="/users/new/">Sign Up</a>
              </li>
              <li>
              <a href="/sessions/new/">Sign In</a>
              </li>
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

        <h1> <c:out value="${user.phoneNumber}"/> </h1>

        <p> We have sent you a SMS with a code to the number above. </p>

        <p> To complete your phone number verification, please enter the 6-digits activation code. </p>

        <form action="/confirmations/" method="POST" class="form-horizontal">
            <div class="form-group">
                <label for="verification_code" class="col-md-2 control-label">Verification Code</label>
                <div class="col-sm-10">
                    <input type="text" name="verification_code" class="form-control"/>
                    <input type="hidden" name="user_id"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" class="btn btn-success" value="Confirm"/>
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
