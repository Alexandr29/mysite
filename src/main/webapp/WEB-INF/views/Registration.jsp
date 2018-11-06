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
         <script type='text/javascript' src='https://code.jquery.com/jquery-latest.min.js'></script>
       <script src="https://www.google.com/recaptcha/api.js"></script>

    </head>
        <body>
        <div class="alert alert-primary" role="alert">
                             <c:out value="${errorMessage}"/>
                          </div>
 <c:url value="/registration" var="loginUrl" />
<form name="userform" method="post" action="${loginUrl}" class="needs-validation">
<table align="center" width="300px"  class="table">

<tr><td colspan=2 style="font-weight:bold;" align="center">Create User</td></tr>
<tr><td colspan=2 align="center" height="10px"></td></tr>
    <tr>
        <td align="right">Login</td>
        <td>
         <input name="login" type="login" class="form-control ntSaveForms" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter login" required>
         </td>
    </tr>
    <tr>
            <td align="right">Password</td>
            <td>
             <input name="password" type="password" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter password" required>
             </td>
     </tr>
     <tr>
                 <td align="right">Password again</td>
                 <td>
                  <input name="passwordagain" type="password" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter password" required>
                  </td>
 </tr>

  <tr>
                  <td align="right">First Name</td>
                  <td>
                   <input name="firstname" type="text" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter firstname" required>
                   </td>
  </tr>
<tr>
                  <td align="right">Last Name</td>
                  <td>
                   <input name="lastname" type="text" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter lastname" required>
                   </td>
  </tr>
  <tr>
                    <td align="right">Email</td>
                    <td>
                     <input name="email" type="email" class="form-control" id="exampleEmail" aria-describedby="EmailHelp" placeholder="Enter email" required>
                     </td>
    </tr>
 <tr>
                  <td align="right">Birth date</td>
                  <td>
                   <input name="date" type="date" class="form-control" id="exampleDate" aria-describedby="loginHelp" placeholder="Enter date" required>
                   </td>
  </tr>
  <tr>
  <td><select style="display: none" name="rolevalue" class="selectpicker">

  <c:forEach items="${roles}" var="role">
                       <option value=""><c:out value="${role.name}"/></option>
                     </c:forEach>
      </select></td>
    </tr>

    <tr>
    <td></td>
    <td><div data-theme="dark" class="g-recaptcha" data-sitekey="6Ld6CHkUAAAAAMbqfh04Dqbul8wG_xmtOTwoc8T7"></div></td>
    </tr>

    <tr>
        <td></td>
        <td></p> <input class="ntSaveFormsSubmit" method="post" type="submit" name="Submit" value="Save"> <a href="/login" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true">Cancel</a></p></td>

    </tr>
    <tr><td colspan=2 align="center" height="10px"></td></tr>
</table>
</form>
</script>
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