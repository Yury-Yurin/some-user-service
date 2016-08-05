<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://cloudinary.com/jsp/taglib" prefix="cl" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!-- Tell the JSP Page that please do not ignore Expression Language -->
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>

<div th:if="${message}">
    <h2 th:text="${message}"/>
</div>

<div>
    <form method="POST" enctype="multipart/form-data" action="addImg/${user.userId}">
        <table>
            <tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
            <tr><td></td><td><input type="submit" value="Upload" /></td></tr>
        </table>
    </form>
</div>
</body>
</html>