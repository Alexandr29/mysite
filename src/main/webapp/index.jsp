<!doctype html>
<html lang="en">

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <body>
        <form method="post"action='/index'>
            <div class="form-group">
                <label for="exampleLogin">Login</label>
                <input name="login" type="login" class="form-control" id="exampleLogin" aria-describedby="loginHelp" placeholder="Enter login">
                <small id="loginHelp" class="form-text text-muted">We'll never share your login with anyone else.</small>
            </div>
            <div class="form-group">
                <label for="examplePassword">Password</label>
                <input name="password" type="password" class="form-control" id="examplePassword" placeholder="Password">
              </div>
            <button type="submit" class="btn btn-primary">Log In</button>
        </form>
    </body>
</html>