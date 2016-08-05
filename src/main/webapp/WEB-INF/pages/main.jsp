<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
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
      <script src="resources/js/jquery.js"></script>
       <script src="resources/js/bootstrap.js"></script>
 <script type="text/javascript" src="resources/js/jquery.fancybox.pack.js"></script>
    <script type="text/javascript" src="resources/js/jquery.mousewheel-3.0.6.pack.js"></script>
    <link rel="stylesheet" href="resources/js/jquery.fancybox.css?v=2.1.5" type="text/css" media="screen" />
    <link rel="stylesheet" href="resources/css/popup.css" type="text/css" media="screen" />
    <script type="text/javascript">
      $(document).ready(function() {

      	/* This is basic - uses default settings */

      	$("a#single_image").fancybox({
      	    'showNavArrows' : true });

      	/* Using custom settings */

      	$("a#inline").fancybox({
      		'hideOnContentClick': true
      	});

      	/* Apply fancybox to multiple items */

      	$("a.group").fancybox({
      		'transitionIn'	:	'elastic',
      		'transitionOut'	:	'elastic',
      		'speedIn'		:	600,
      		'speedOut'		:	200,
      		'overlayShow'	:	false,
      		'showNavArrows' : true
      	});

      });
    </script>
  </head>
  <body>
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
                 <li><a href="logout">Logout</a></li>
               </nav>
             </div>
           </div>

           <div class="inner cover">
             <h1 class="cover-heading">Add you images!</h1>
             <p class="lead"><a href="addImgForm" class="btn btn-lg btn-default">Upload</a></p>
             <p class="lead">
             </p>
             <table>
             <c:if test="${imgs != null && !imgs.isEmpty()}">
             <tr>
             <c:forEach var="i" begin="0" end="${imgs.size()-1}">
             <c:if test="${i%5==0}">
                             <tr>
                             </c:if>
                <td>
          <a id="single_image" rel="group" href="${imgs[i].url}">
          <img src="${imgsResized[i].url}" alt=""/>
          </a>
              <script>
                        function deleteImg${imgs[i].imgId}() {
                           var res = "${imgs[i].url}".split("/");
                           var publicID = res[res.length - 1].split(".")[0];
                               $.ajax({
                                      type: 'POST',
                                      contentType: 'application/json',
                                      url: 'deleteImg?imgId=${imgs[i].imgId}&pubId=' + publicID,
                                      success: function() {
                                         location.reload();
                                      },
                                      error: function (jqXHR, textStatus, errorThrown) {
                                          console.log(jqXHR, textStatus, errorThrown);
                                          alert('deleteApplication: ' + textStatus);
                                      }
                              });
                           }
                         </script>
                             <p> <button class="btn-default" onClick="deleteImg${imgs[i].imgId}()">Delete</button>
             </td>
             <c:if test="${i==6}">
                </tr>
                </c:if>
             </c:forEach>
             </c:if>
            </tr>
             </table>
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