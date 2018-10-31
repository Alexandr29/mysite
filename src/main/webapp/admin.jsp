<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="usertag"
           uri="/WEB-INF/table.tld" %>
<div>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" src="css/style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
          <body>
          <div align="right">
             <h3><c:out value="${login}"/></h3>
             <h3><a href="/logout">Logout</a><h3>
           </div>
              <div>
              <a href="/create">
                <img border="0" src="images/add.jpg" width="100" height="100">
                </a>
              </div>
              <div class="center mt-3">
                  <usertag:table users="${users}"/>
              </div>
          </body>
</html>