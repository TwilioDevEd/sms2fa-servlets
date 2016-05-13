<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
  <h1> ${user.phoneNumber} </h1>

  <p> We have sent you a SMS with a code to the number above. </p>

  <p> To complete your phone number verification, please enter the 6-digits
 activation code. </p>

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
</t:layout>