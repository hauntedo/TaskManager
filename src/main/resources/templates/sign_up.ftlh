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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="<@spring.url "/css/signin.css"/>">
</head>
<body class="text-center">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>
<main class="form-signin">
    <form action="/sign_up" onsubmit="checkPassword()" method="post">
        <h1 class="h3 mb-3 fw-normal">Please sign up</h1>
        <@spring.bind path="signUpForm"/>
        <div class="form-floating">
            <@spring.formInput "signUpForm.firstName" "placeholder='Firstname' class=form-control required"/>
            <label for="firstName">First name</label>
            <@spring.showErrors "<br>" "error"/>
            <br>
        </div>

        <div class="form-floating">
            <@spring.formInput "signUpForm.lastName" "placeholder='Lastname' class=form-control required"/>
            <label for="lastName">Lastname</label>
            <@spring.showErrors "<br>" "error"/>
            <br>
        </div>

        <div class="form-floating">
            <@spring.formInput "signUpForm.userName" "placeholder='Username' class=form-control required"/>
            <label for="userName">Username</label>
            <@spring.showErrors "<br>" "error"/>
            <br>
        </div>

        <div class="form-floating">
            <@spring.formInput "signUpForm.email" "placeholder='Email' class=form-control required"/>
            <label for="email">Email</label>
            <@spring.showErrors "<br>" "error"/>
            <br>
        </div>

        <div class="form-floating">
            <@spring.formPasswordInput "signUpForm.password" "id='password' name='password'
            placeholder='Password' class=form-control required"/>
            <label for="password">Password</label>
            <@spring.showErrors "<br>" "error"/>
            <br>
        </div>

        <div class="form-floating">
            <input type="password" class="form-control" id="checkPassword" name="checkPassword"
            placeholder="Password" required>
            <label for="repeatPassword">Repeat Password</label>
            <#--            <@spring.formPasswordInput "signUpForm.checkPassword" "id='checkPassword' name='checkPassword'-->
<#--            placeholder='Password' class=form-control required"/>-->

<#--            <@spring.showErrors "<br>" "error"/>-->
            <br>
        </div>

        <#if message??>
            <h6 class="text-danger">${message}</h6>
        </#if>

        <script>
            let password = document.getElementById('password').value;
            let passwordCheck = document.getElementById('checkPassword').value;

            function checkPassword() {
                if (password !== passwordCheck) {
                    alert("Password is mismatch")
                } else {
                    alert();
                }
            }
        </script>

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
