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
        <div class="alert alert-primary" role="alert">
                             <c:out value="${errorMessage}"/>
                          </div>

<form name="userform" method="post" class="needs-validation">
<br>
<table align="center" width="300px"  class="table">

<tr><td colspan=2 style="font-weight:bold;" align="center">Create User</td></tr>
<tr><td colspan=2 align="center" height="10px"></td></tr>
    <tr>
        <td>Login</td>
        <td><label for="exampleLogin">Login</label>
         <input name="login" type="login" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter login" required>
         </td>
    </tr>
    <tr>
            <td>Password</td>
            <td><label for="examplePassword">password</label>
             <input name="password" type="password" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter password" required>
             </td>
     </tr>
     <tr>
                 <td>Password again</td>
                 <td><label for="examplePassword">passwordagain</label>
                  <input name="passwordagain" type="password" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter password" required>
                  </td>
 </tr>

  <tr>
                  <td>First Name</td>
                  <td><label for="exampleFirstName">Login</label>
                   <input name="firstname" type="text" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter firstname" required>
                   </td>
  </tr>
<tr>
                  <td>Last Name</td>
                  <td><label for="exampleFirstName">Last Name</label>
                   <input name="lastname" type="text" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter lastname" required>
                   </td>
  </tr>
  <tr>
                    <td>Email</td>
                    <td><label for="exampleemail">Last Name</label>
                     <input name="email" type="email" class="form-control" id="exampleEmail" aria-describedby="EmailHelp" placeholder="Enter email" required>
                     </td>
    </tr>
 <tr>
                  <td>Birth date</td>
                  <td><label for="exampleDate">Login</label>
                   <input name="date" type="date" class="form-control" id="exampleDate" aria-describedby="loginHelp" placeholder="Enter date" required>
                   </td>
  </tr>
  <tr>
            <td>Role</td>
  <td><select name="rolevalue" class="selectpicker">

  <c:forEach items="${roles}" var="role">
                       <option value="${role.name}"><c:out value="${role.name}"/></option>
                     </c:forEach>
      </select>
                     </td>
    </tr>


    <tr>
        <td></td>
        <td></p> <input method="post" type="submit" name="Submit" value="Save">  <button type="reset" class="btn btn-secondary my-button">Cancel</button></p></td>

    </tr>
    <tr><td colspan=2 align="center" height="10px"></td></tr>
</table>
</form>
<script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function() {
          'use strict';
          window.addEventListener('load', function() {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function(form) {
              form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                  event.preventDefault();
                  event.stopPropagation();
                }
                form.classList.add('was-validated');
              }, false);
            });
          }, false);
        })();
        </script>
</body>
</html>