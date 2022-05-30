<!doctype html>
<#import "spring.ftl" as spring/>
<#import "layouts/header.ftlh" as header>
<#import "layouts/footer.ftlh" as footer>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Settings</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body class="text-center">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>
<@header.header />
<div class="container">
    <div class="main-body">
        <@spring.bind path="editUser"/>
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div class="card">
                    <div class="card-body">
                        <form action="/settings?action=edit" method="post">
                            <div class="row mb-3">
                                <label for="firstName" class="col-sm-3 col-form-label">
                                    <h6 class="mb-0">First name</h6></label>
                                <div class="col-sm-9">
                                    <@spring.formInput "editUser.firstName" "name='firstName'
                                    class='form-control' placeholder='${user.firstName}'"/>
                                    <@spring.showErrors "<br>" "error"/>
<#--                                    <input class="form-control" id="firstName" name="firstName" value="${user.firstName}">-->
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label for="lastName" class="col-sm-3 col-form-label">
                                    <h6 class="mb-0">Last name</h6></label>
                                <div class="col-sm-9">
                                    <@spring.formInput "editUser.lastName" "name='lastName'
                                    class='form-control' placeholder='${user.lastName}'"/>
                                    <@spring.showErrors "<br>" "error"/>
<#--                                    <input class="form-control" id="lastName" name="lastName" value="${user.lastName}">-->
                                </div>
                            </div>
                            <#if user.aboutMe??>
                                <#--value dont work-->
                                <div class="row mb-3">
                                    <label for="aboutMe" class="col-sm-3 col-form-label">
                                        <h6 class="mb-0">About me</h6></label>
                                    <div class="col-sm-9">
<#--                                        <@spring.formTextarea "editUser.aboutMe" "autocomplete='on'-->
<#--                                        id='aboutMe' class='form-control'-->
<#--                                        name='aboutMe' autocompelete='on'">${user.aboutMe}</@spring.formTextarea>-->
<#--                                        <@spring.showErrors "<br>" "error"/>-->
                                        <textarea id="aboutMe" class="form-control" name="aboutMe"
                                                  autocomplete="on">${user.aboutMe}</textarea>
                                    </div>
                                </div>
                                <#else>
                                <div class="row mb-3">
                                    <label for="aboutMe" class="col-sm-3 col-form-label">
                                        <h6 class="mb-0">About me</h6></label>
                                    <div class="col-sm-9">
                                        <@spring.formTextarea "editUser.aboutMe" "id='aboutMe' class='form-control'
                                        name='aboutMe' placeholder='About me' autocompelete='on'"/>
                                        <@spring.showErrors "<br>" "error"/>
<#--                                        <textarea id="aboutMe" class="form-control" name="aboutMe"-->
<#--                                                  placeholder="About me" autocomplete="on"></textarea>-->
                                    </div>
                                </div>
                            </#if>
                            <div class="row mb-3">
                                <label for="password" class="col-sm-3 col-form-label">
                                    <h6 class="mb-0">Password</h6></label>
                                <div class="col-sm-9">
                                    <@spring.formPasswordInput "editUser.password" "name='password' id='password'
                                    class='form-control' placeholder='Password'"/>
                                    <@spring.showErrors "<br>" "error"/>
<#--                                    <input type="password" class="form-control" id="password" name="password">-->
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label for="password" class="col-sm-3 col-form-label">
                                    <h6 class="mb-0">Repeat Password</h6></label>
                                <div class="col-sm-9">
                                    <input type="password" class="form-control" id="passwordCheck" name="passwordCheck"
                                           placeholder="Password" required>
                                </div>
                            </div>
                            <#if message??>
                                <h6 class="text-danger">${message}</h6>
                            </#if>
                            <div class="row">
                                <div class="col-sm-2"></div>
                                <div class="col-sm-8 text-secondary">
                                    <button type="submit" class="btn btn-primary px-4">Update profile</button>
                                </div>
                                <div class="col-sm-2"></div>
                            </div>
                        </form>
                        <hr class="my-4">
                        <form action="/settings?action=delete" onsubmit="return delete_account()" method="post">
                            <div>
                                <h2>Delete account</h2>
                                <p>Once you delete your account, there is no going back. Please be certain.</p>
                                <button type="submit" id="confirm" name="confirm" class="btn btn-danger px-4">Delete</button>
                                <script>
                                    function delete_account(){
                                        return confirm('Are you sure you want to delete your account?');
                                    }
                                </script>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
    </div>
</div>
<@footer.footer />
</body>
</html>