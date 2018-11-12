<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.ArrayList"%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
        <body>
        <div class="alert alert-primary" role="alert">

                                            		<b><c:out value="${error}"/></b>
                </div>

<form:form method="post" name="userform" modelAttribute="user" class="needs-validation">
<br>
<table align="center" width="300px" class="table">

<tr><td colspan=2 style="font-weight:bold;" align="center">Edit User</td></tr>
<tr><td colspan=2 align="center" height="10px"></td></tr>
    <tr>
        <td align="right">Login</td>
        <td>
         <input name="login" type="text" value="${logintoedit}" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter login" readonly>
         </td>
    </tr>
    <tr>
            <td align="right">Password</td>
            <td>
             <input name="password" type="password" value="${user.password}" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter password" required>
             </td>
     </tr>
     <tr>
                 <td align="right">Password again</td>
                 <td>
                  <input name="passwordagain" type="password" value="${user.password}" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter password" required>
                  </td>
 </tr>

  <tr>
                  <td align="right">First Name</td>
                  <td>
                   <input name="firstName" type="text" value="${user.firstName}" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter firstname" required>
                   </td>
  </tr>
<tr>
                  <td align="right">Last Name</td>
                  <td>
                   <input name="lastName" type="text" value="${user.lastName}" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter firstname" required>
                   </td>
  </tr>
  <tr>
                      <td align="right">Email</td>
                      <td>
                       <input name="email" type="email" value="${user.email}" class="form-control" id="exampleEmail" aria-describedby="EmailHelp" placeholder="Enter email" required>
                       </td>
      </tr>
 <tr>
                  <td align="right">Birth date</td>
                  <td>
                   <input name="birthday" type="date" min="1920-01-01" max="2008-12-31" value="${user.birthday}" class="form-control" id="exampleDate" aria-describedby="loginHelp" placeholder="Enter date" required>
                   </td>
  </tr>

   <tr>
               <td align="right">Role</td>
     <td><select name="role_id" class="selectpicker">
            <option value="${role}" selected ><c:out value="${rolename}"/></option>
     <c:forEach items="${roles}" var="role">
            <option value="${role.id}"><c:out value="${role.name}"/></option>
     </c:forEach>
         </select>
                        </td>
       </tr>


    <tr>
        <td></td>
        <td></p> <input method="post" type="submit" name="Submit" value="Save"><a href="/admin" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true">Cancel</a></p></td></p></td>

    </tr>
    <tr><td colspan=2 align="center" height="10px"></td></tr>
</table>
</form:form>
</body>
</html>