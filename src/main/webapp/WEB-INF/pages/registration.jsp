<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!-- Tell the JSP Page that please do not ignore Expression Language -->
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
 <link href="resources/css/signin.css" rel="stylesheet">
</head>
<body>
<script src="resources/js/jquery.js"></script>
<script src="resources/js/bootstrap.js"></script>
<div class="container">
<form:form class="form-signin" id="userForm" method="POST" commandName="user" action="addUser" accept-charset="UTF-8">
 <h2 class="form-signin-heading">Please sign up</h2>
  <label for="inputLogin" class="sr-only">Email address</label>
  <form:input type="login" id="inputLogin" path="login" class="form-control" placeholder="Login"/>
  <label for="inputPassword" class="sr-only">Password</label>
  <form:input type="password" id="inputPassword" path="password" class="form-control" placeholder="Password"/>
  <label for="inputFirstName" class="sr-only">First name</label>
  <form:input type="text" id="inputFirstName" path="firstName" class="form-control" placeholder="First name"/>
  <label for="inputLastName" class="sr-only">Last name</label>
  <form:input type="text" id="inputLastName" path="password" class="form-control" placeholder="Last name"/>
  <label for="inputBirthDate"><h4>Birth date</h4></label>
  <form:input type="date" id="inputLastName" path="birthDate" class="form-control" placeholder="Birth date"/>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>
</form:form>
</div>
</body>
</html>