<!doctype html>
<#import "spring.ftl" as spring/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Edit profile</title>
    <link rel="stylesheet" href="<@spring.url "/css/profile.css"/>">
    <link rel="stylesheet" href="<@spring.url "/css/bootstrap.min.css"/>">
</head>
<body class="text-center">
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
                                    <@spring.formInput "editProfileForm.aboutMe" "placeholder='About me' class=form-control"/>
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

</body>
</html>