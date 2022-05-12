<!doctype html>
<#import "spring.ftl" as spring/>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Sign Up</title>
    <link rel="stylesheet" href="<@spring.url "/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<@spring.url "/css/signin.css"/>">
</head>
<body class="text-center">

<main class="form-signin">
    <form action="/sign_up" method="post">
        <h1 class="h3 mb-3 fw-normal">Please sign up</h1>
        <@spring.bind path="signUpForm"/>
        <div class="form-floating">
            <@spring.formInput "signUpForm.firstName" "placeholder='Firstname' class=form-control"/>
            <label for="floatingInput">First name</label>
            <@spring.showErrors "<br>" "error"/>
            <br>
        </div>

        <div class="form-floating">
            <@spring.formInput "signUpForm.lastName" "placeholder='Lastname' class=form-control"/>
            <label for="floatingInput">Lastname</label>
            <@spring.showErrors "<br>" "error"/>
            <br>
        </div>

        <div class="form-floating">
            <@spring.formInput "signUpForm.userName" "placeholder='Username' class=form-control"/>
            <label for="floatingInput">Username</label>
            <@spring.showErrors "<br>" "error"/>
            <br>
        </div>

        <div class="form-floating">
            <@spring.formInput "signUpForm.email" "placeholder='Email' class=form-control"/>
            <label for="floatingInput">Email</label>
            <@spring.showErrors "<br>" "error"/>
            <br>
        </div>

        <div class="form-floating">
            <@spring.formPasswordInput "signUpForm.password" "placeholder='Password' class=form-control"/>
            <label for="floatingPassword">Password</label>
            <@spring.showErrors "<br>" "error"/>
            <br>
        </div>

        <button class="w-100 btn btn-lg btn-primary" type="submit">Sign up</button>
        <p class="text-center">
            <span class="light">
                Already have login and password?
                <a href="/sign_in">Sign in</a>
            </span>
        </p>
    </form>
</main>



</body>
</html>
