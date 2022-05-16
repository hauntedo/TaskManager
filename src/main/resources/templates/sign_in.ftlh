<!doctype html>
<#import "spring.ftl" as spring/>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Sign In</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="<@spring.url "/css/signin.css"/>">
</head>
<body class="text-center">

<main class="form-signin">
    <form action="/sign_in" method="post">
        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

        <#if message??>
            <h6 class="text-danger">${message}</h6>
        </#if>

        <div class="form-floating">
            <input type="text" name="userName" class="form-control" id="floatingInput" placeholder="Username">
            <label for="floatingInput">Username</label>
            <br>
        </div>
        <div class="form-floating">
            <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password">
            <label for="floatingPassword">Password</label>
            <br>
        </div>

        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
        <p class="text-center">
            Don't have an account yet?
            <a href="/sign_up">Register now</a>
        </p>
    </form>
</main>



</body>
</html>
