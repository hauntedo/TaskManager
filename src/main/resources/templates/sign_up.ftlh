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

    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/sign-in/">

    <!-- Bootstrap core CSS -->
    <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body class="text-center">

<main class="form-signin">
    <form action="/sign_up" method="post">
        <h1 class="h3 mb-3 fw-normal">Please sign up</h1>
        <@spring.bind path="signUpForm"/>
        <div class="form-floating">
            <@spring.formInput "signUpForm.firstName" "placeholder='Firstname'"/>
            <label for="floatingInput">Firstname</label>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="form-floating">
            <@spring.formInput "signUpForm.lastName" "placeholder='Lastname'"/>
            <label for="floatingInput">Lastname</label>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="form-floating">
            <@spring.formInput "signUpForm.userName" "placeholder='Username'"/>
            <label for="floatingInput">Username</label>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="form-floating">
            <@spring.formInput "signUpForm.email" "placeholder='Email'"/>
            <label for="floatingInput">Email</label>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="form-floating">
            <@spring.formPasswordInput "signUpForm.password" "placeholder='Password'"/>
            <label for="floatingPassword">Password</label>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <button class="w-100 btn btn-lg btn-primary" type="submit">Sign up</button>
    </form>
</main>



</body>
</html>
