<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- Tell the JSP Page that please do not ignore Expression Language -->
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
     <link href="resources/css/cover.css" rel="stylesheet">

  </head>
  <body>
  <script src="resources/js/jquery.js"></script>
  <script src="resources/js/bootstrap.js"></script>
     <div class="site-wrapper">
       <div class="site-wrapper-inner">
         <div class="cover-container">
           <div class="masthead clearfix">
             <div class="inner">
               <h3 class="masthead-brand">Cover</h3>
               <nav>
                 <ul class="nav masthead-nav">
                   <li class="active"><a href="main">Home</a></li>
                   <li><a href="">Features</a></li>
                   <li><a href="about">Contact</a></li>
                 </ul>
               </nav>
             </div>
           </div>
           <div class="inner cover">
             <h1 class="cover-heading">Cover your page.</h1>
             <p class="lead">Cover is a one-page template for building simple and beautiful home pages. Download, edit the text, and add your own fullscreen background photo to make it your own.</p>
             <p class="lead">
               <a href="logout" class="btn btn-lg btn-default">Logout</a>
             </p>
            ${user.password}
           </div>
           <div class="mastfoot">
             <div class="inner">
               <p>Cover template for <a href="http://getbootstrap.com">Bootstrap</a>, by <a href="https://twitter.com/mdo">@mdo</a>.</p>
             </div>
           </div>
         </div>
       </div>
     </div>
   </body>
</html>