<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page import="java.util.ArrayList"%>
<html lang="en">
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
          <body>
          <div align="right">
                  <h3><c:out value="${firstName} ${lastName}"/></h3>
                  <h4><a href="/logout">Logout</a><h4>
              </div>
              <div>
              <a href="/create">
                                   <img border="0" src="images/add.jpg" width="100" height="100">
                                    </a>
              </div>
              <table id="mytable" class="table table-striped" border="1">
                <thead>
                    <th>id</th>
                    <th>Login</th>
                    <th>Password</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Age</th>
                    <th>Role</th>
                    <th>Actions</th>


                </thead>
                <tbody>
                 <c:forEach items="${users}" var="name">
                     <tr>
                       <td><c:out value="${name.id}"/></td>
                       <td><c:out value="${name.login}"/></td>
                       <td><c:out value="${name.password}"/></td>
                       <td><c:out value="${name.firstName}"/></td>
                       <td><c:out value="${name.lastName}"/></td>
                       <td><c:out value="${name.age}"/></td>
                       <td><c:out value="${name.role_id}"/></td>
                       <td>

                       <c:url value="/edit" var="url" scope="request"></c:url>

                                                                     <a href="${url}?logintoedit=<c:out value="${name.login}"/>">
                                                                      <img border="0"  src="images/edit.png" width="30" height="30">
                                                                      </a>

                       <c:url value="/delete" var="url" scope="request">
                                               		<c:param name="id" value="${name.id}" />
                                               	</c:url>

                                              <a href="/delete?logintodelete=<c:out value="${name.login}"/>">
                                               <img border="0"  src="images/trash.jpg" width="30" height="30">
                                               </a>
                     </tr>
                   </c:forEach>
                </tbody>
              </table>
          </body>
</html>