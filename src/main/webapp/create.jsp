<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page import="java.util.ArrayList"%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
        <body>

<form name="userform" method="post">
<br><br><br>
<table align="center" width="300px" style="background-color:#EDF6EA;border:1px solid #000000;">

<tr><td colspan=2 style="font-weight:bold;" align="center">Edit User</td></tr>
<tr><td colspan=2 align="center" height="10px"></td></tr>
    <tr>
        <td>Login</td>
        <td><label for="exampleLogin">Login</label>
         <input name="login" type="login" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter login">
         </td>
    </tr>
    <tr>
            <td>Password</td>
            <td><label for="examplePassword">password</label>
             <input name="password" type="password" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter password">
             </td>
     </tr>
     <tr>
                 <td>Password again</td>
                 <td><label for="examplePassword">passwordagain</label>
                  <input name="passwordagain" type="password" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter password">
                  </td>
 </tr>

  <tr>
                  <td>First Name</td>
                  <td><label for="exampleFirstName">Login</label>
                   <input name="firstname" type="text" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter firstname">
                   </td>
  </tr>
<tr>
                  <td>Last Name</td>
                  <td><label for="exampleFirstName">Last Name</label>
                   <input name="lastname" type="text" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter firstname">
                   </td>
  </tr>
 <tr>
                  <td>Birth date</td>
                  <td><label for="exampleDate">Login</label>
                   <input name="date" type="date" class="form-control" id="exampleDate" aria-describedby="loginHelp" placeholder="Enter date">
                   </td>
  </tr>
  <tr>
  <td><select name="rolevalue">

  <c:forEach items="${roles}" var="role">
                       <option value="${role.name}"><c:out value="${role.name}"/></option>
                     </c:forEach>
      </select>
                     </td>
    </tr>


    <tr>
        <td></td>
        <td><input method="post" type="submit" name="Submit" value="Save" style="background-color:#49743D;font-weight:bold;color:#ffffff;"></td>
    </tr>
    <tr><td colspan=2 align="center" height="10px"></td></tr>
</table>


                 <br>
        <TABLE style="background-color: #E3E4FA;"
               WIDTH="30%" border="1">
            <tr><th>Data Modified successfully
                    in database.</th></tr>
        </TABLE>
</form>
</body>
</html>