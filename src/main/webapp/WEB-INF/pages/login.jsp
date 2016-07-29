<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!-- Tell the JSP Page that please do not ignore Expression Language -->
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<header>
</header>
<body>
<h1 align="center">List of users</h1>

<table align="center" border=2 color="red" width="900">
    <tbody id="addMalfunction">
 <c:forEach items="${users}" var="user">
             <tr height="50">
                 <td>${user.userId}</td>
                 <td>${user.login}</td>
                 <td>${user.password}</td>
        </tr>
         </c:forEach>
    </tbody>
</table>
</body>
</html>