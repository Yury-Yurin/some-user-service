<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- Tell the JSP Page that please do not ignore Expression Language -->
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
 <link href="resources/css/bootstrap.min.css" rel="stylesheet">
 <link href="resources/css/signin.css" rel="stylesheet">
</head>
<body>
<script src="resources/js/jquery.js"></script>
<script src="resources/js/bootstrap.js"></script>
<h3 align="center">If you not have account<p> follow to registration</h3>
<p>
<div class="container">
  <form:form class="form-signin" id="goToRegistration" method="GET" action="registration" accept-charset="UTF-8">
                 <button class="btn btn-lg btn-my btn-block" type="submit">Sign Up</button>
                   </form:form>
      <form:form class="form-signin" method="POST" commandName="user" action="signIn">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <form:input type="login" id="inputLogin" path="login" class="form-control" placeholder="Email address"/>
        <label for="inputPassword" class="sr-only">Password</label>
        <form:input type="password" id="inputPassword" path="password" class="form-control" placeholder="Password"/>
        <button class="btn btn-lg btn-my btn-block" type="submit">Sign in</button>
      </form:form>
    </div> <!-- /container -->
</body>
</html>