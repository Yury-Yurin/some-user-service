<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!-- Tell the JSP Page that please do not ignore Expression Language -->
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<header>
</header>
<body>
<h1 align="center">Registration</h1>
<div align=center>
<form:form id="userForm" method="POST" commandName="user" action="addUser">
<table>
    <tr>
        <td align=right>Login:</td>
        <td><form:input id="login" path="login" type="text"/></td>
    </tr>
    <tr>
        <td align=right>Password:</td>
        <td><form:input path="password" id="pass" type="password"/></td>
    </tr>
    <tr>
         <td align=right>First name:</td>
         <td><form:input path="firstName" id="firstName" type="text"/></td>
     </tr>
     <tr>
          <td align=right>Last name:</td>
          <td><form:input path="lastName" id="lastName" type="text"/></td>
      </tr>
</table>
<input type="submit" value="Add"/>
</form:form>
</div>

</html>