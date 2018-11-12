<!doctype html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
         <script type="text/javascript" src="js/myscripts.js"></script>
    </head>
    <body>
<div class="alert alert-primary" role="alert">
          <c:out value="${error}"/>
        </div>
         <c:url value="/login" var="loginUrl" />
        <form method="post" action="${loginUrl}" class="needs-validation" novalidate>



         <div class="form-group">
            <div class="col-md-4 mb-4">
                <label for="exampleLogin">Login</label>
                <input  name="login" type="login" class="form-control" id="exampleLogin" placeholder="Enter login" aria-describedby="validationTooltipUsernamePrepend" required>
                <div class="invalid-tooltip">
                          Please choose valid login.
                        </div>
                <small id="loginHelp" class="form-text text-muted">Never share your login with anyone else.</small>
                <div class="valid-feedback"> Looks good! </div>
            </div>
         </div>
            <div class="form-group">
             <div class="col-md-4 mb-3">
                <label for="examplePassword">Password</label>
                <input name="password" type="password" class="form-control" id="examplePassword" placeholder="Password" aria-describedby="validationTooltipPasswordPrepend" required>
                <div class="invalid-tooltip">
                          Please choose valid password.
                        </div>
                <div class="valid-feedback">
                      Looks good!
                    </div>
                  </div>
              </div>
              <div class="col-md-4 mb-3">
            <button type="submit" class="btn btn-primary">Log In</button>
              <a href="/registration"> Register</a>
            </div>
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