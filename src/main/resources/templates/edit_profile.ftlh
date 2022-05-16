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
    <title>Edit profile</title>
    <link rel="stylesheet" href="<@spring.url "/css/profile.css"/>">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body class="text-center">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>
<@header.header />
<div class="container">
    <@spring.bind path="editProfileForm"/>
    <div class="main-body">
        <div class="row">
            <div class="col-lg-8">
                <div class="card">
                    <div class="card-body">
                        <form action="" method="post">
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">First name</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <@spring.formInput "editProfileForm.firstName" "placeholder='First name' class=form-control"/>
                                    <label for="floatingInput"></label>
                                    <@spring.showErrors "<br>" "error"/>
                                    <br>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Last name</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <@spring.formInput "editProfileForm.lastName" "placeholder='Last name' class=form-control"/>
                                    <label for="floatingInput"></label>
                                    <@spring.showErrors "<br>" "error"/>
                                    <br>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">About me</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <@spring.formTextarea "editProfileForm.aboutMe" "placeholder='About me' class=form-control"/>
                                    <label for="floatingPassword"></label>
                                    <@spring.showErrors "<br>" "error"/>
                                    <br>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Password</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <@spring.formPasswordInput "editProfileForm.password" "placeholder='Password' class=form-control"/>
                                    <label for="floatingPassword"></label>
                                    <@spring.showErrors "<br>" "error"/>
                                    <br>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-3"></div>
                                <div class="col-sm-9 text-secondary">
                                    <button type="submit" class="btn btn-primary px-4">Save</button>
                                    <a href="/profile" class="btn btn-danger">Back</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<@footer.footer />
</body>
</html>