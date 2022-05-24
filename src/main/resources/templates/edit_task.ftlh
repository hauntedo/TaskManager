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
    <title>Edit task</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body class="text-center">
<script src="<@spring.url "/static/js/textArea.js"/>"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>
<@header.header />
<div class="container">
    <div class="main-body">
        <@spring.bind path="taskForm"/>
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div class="card">
                    <div class="card-body">
                        <form action="" method="post">
                            <div class="row mb-3">
                                <label for="firstName" class="col-sm-3 col-form-label">
                                    <h6 class="mb-0">Title</h6></label>
                                <div class="col-sm-9">
                                    <@spring.formInput "taskForm.title" "name='title'
                                    class='form-control' value='${task.title}'"/>
                                    <@spring.showErrors "<br>" "error"/>
                                    <#--                                    <input class="form-control" id="firstName" name="firstName" value="${user.firstName}">-->
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label for="aboutMe" class="col-sm-3 col-form-label">
                                    <h6 class="mb-0">Annotation</h6></label>
                                <div class="col-sm-9">
                                    <#--                                        <@spring.formTextarea "editUser.aboutMe" "autocomplete='on'-->
                                    <#--                                        id='aboutMe' class='form-control'-->
                                    <#--                                        name='aboutMe' autocompelete='on'">${user.aboutMe}</@spring.formTextarea>-->
                                    <#--                                        <@spring.showErrors "<br>" "error"/>-->
                                    <textarea id="annotation" class="form-control" name="annotation"
                                              autocomplete="on">${task.annotation}</textarea>
                                </div>
                            </div>
                            <#if task.description??>
                            <#--value dont work-->
                                <div class="row mb-3">
                                    <label for="aboutMe" class="col-sm-3 col-form-label">
                                        <h6 class="mb-0">Description</h6></label>
                                    <div class="col-sm-9">
                                        <#--                                        <@spring.formTextarea "editUser.aboutMe" "autocomplete='on'-->
                                        <#--                                        id='aboutMe' class='form-control'-->
                                        <#--                                        name='aboutMe' autocompelete='on'">${user.aboutMe}</@spring.formTextarea>-->
                                        <#--                                        <@spring.showErrors "<br>" "error"/>-->
                                        <textarea id="description" class="form-control" name="description"
                                                  autocomplete="on">${task.description}</textarea>
                                    </div>
                                </div>
                            <#else>
                                <div class="row mb-3">
                                    <label for="aboutMe" class="col-sm-3 col-form-label">
                                        <h6 class="mb-0">Description</h6></label>
                                    <div class="col-sm-9">
                                        <@spring.formTextarea "taskForm.description" "id='description' class='form-control'
                                        name='description' placeholder='Description' autocompelete='on'"/>
                                        <@spring.showErrors "<br>" "error"/>
                                        <#--                                        <textarea id="aboutMe" class="form-control" name="aboutMe"-->
                                        <#--                                                  placeholder="About me" autocomplete="on"></textarea>-->
                                    </div>
                                </div>
                            </#if>
                            <div class="row">
                                <div class="col-sm-2"></div>
                                <div class="col-sm-8 text-secondary">
                                    <button type="submit" class="btn btn-primary px-4">Save</button>
                                    <button type="button" class="btn btn-danger" onclick="document.location='/tasks/${task.uuid}'">
                                        Cancel
                                    </button>
                                </div>
                                <div class="col-sm-2"></div>
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