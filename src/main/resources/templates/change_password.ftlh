<!doctype html>
<#import "spring.ftl" as spring/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Password change</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="<@spring.url "/css/signin.css"/>">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>
<main class="form-signin">
    <div class="container">
        <form action="/change/${confirm}" method="post">
            <h1 class="h3 mb-3 fw-normal">Please change password</h1>
            <@spring.bind path="passwordForm"/>

            <div class="form-floating">
                <@spring.formPasswordInput "passwordForm.password" "id='password' name='password'
            placeholder='Password' class=form-control required"/>
                <label for="password">Password</label>
                <@spring.showErrors "<br>" "error"/>
                <br>
            </div>

            <div class="form-floating">
                <@spring.formPasswordInput "passwordForm.passwordCheck" "id='passwordCheck' name='passwordCheck'
            placeholder='Password' class=form-control required"/>
                <label for="repeatPassword">Repeat Password</label>
                <@spring.showErrors "<br>" "error"/>
                <br>
            </div>

            <#if message??>
                <h6 class="text-center text-danger">${message}</h6>
            </#if>

            <button class="w-100 btn btn-lg btn-primary" type="submit">Change</button>
        </form>
    </div>
</main>

</body>
</html>