<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
  <h1>We're going to be best friends</h1>
  <p>Thanks for your interest in signing up! Can you tell us a bit about yourself?</p>
  <form action="/users/" method="POST" id="new_user" class="form-horizontal">
    <div class="form-group">
      <label for="first_name" class="col-md-2 control-label">Firstname</label>
      <div class="col-sm-10">
        <input name="first_name" type="text" class="form-control"/>
      </div>
    </div>
    <div class="form-group">
      <label for="last_name" class="col-md-2 control-label">Last
      name</label>
      <div class="col-sm-10">
        <input name="last_name" type="text" class="form-control"/>
      </div>
    </div>
    <div class="form-group">
      <label for="email" class="col-md-2 control-label">Email</label>
      <div class="col-sm-10">
        <input name="email" type="email" class="form-control"/>
      </div>
    </div>
    <div class="form-group">
      <label for="phone_number" class="col-md-2 control-label">Phone number</label>
      <div class="col-sm-10">
        <input name="phone_number" type="tel" class="form-control"/>
      </div>
    </div>
    <div class="form-group">
      <label for="password" class="col-md-2 control-label">Password</label>
      <div class="col-sm-10">
        <input name="password" type="password" class="form-control"/>
      </div>
    </div>
    <div class="form-group">
      <label for="password_confirmation" class="col-md-2 control-label">Confirm password</label>
      <div class="col-sm-10">
        <input name="password_confirmation" type="password" class="form-control"/>
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-success">Sign Up</button>
      </div>
    </div>
  </form>
</t:layout>