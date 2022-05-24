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
    <title>Task</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
<@header.header />
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>
<main>
    <div class="container">
        <div class="container py-4">
            <div class="p-5 mb-4 bg-light rounded-3">
                <div class="container-fluid py-5">
                    <h1 class="display-5 fw-bold">${task.title}</h1>
                    <p class="col-md-8 fs-4">${task.description}</p>
                    <p>Created by: ${task.createdBy}</p>
                    <button type="button" onClick="document.location='/tasks'"
                            class="btn btn-primary btn-lg">Back</button>
                    <#if chief>
                        <button type="button" onClick="document.location='/tasks/${task.uuid}/edit'"
                            class="btn btn-primary btn-lg">Edit</button>
                    </#if>
                    <#if task.taskState == "OPEN">
                        <#if !chief>
                            <a class="btn btn-primary btn-lg" role="button" href="/tasks/${task.uuid}?action=add">Add</a>
                        </#if>
                    </#if>
                    <div class="mb-3">
                            <#if task.taskState != "COMPLETED">
                                <form action="/tasks/${task.uuid}" method="post" enctype="multipart/form-data">
                                    <textarea class="form-control" name="content" id="exampleFormControlTextarea1" rows="3"></textarea>
                                    <input class="form-control" name="file" type="file" id="formFileMultiple" multiple>
                                    <button class="btn btn-primary btn-lg" type="submit">Save</button>
                                </form>
                            </#if>
                    </div>
                        <div class="d-grid gap-2 d-md-block">
                            <#if chief>
                                <#if task.taskState == "RESOLVED">
                                    <a class="btn btn-primary btn-lg" role="button"
                                       href="/tasks/${task.uuid}?action=resend">Resend</a>
                                    <a class="btn btn-primary btn-lg" role="button"
                                       href="/tasks/${task.uuid}?action=accept">Accept</a>
                                </#if>
                            <#else>
                                <#if task.taskState == "IN_PROGRESS">
                                    <a class="btn btn-primary btn-lg" role="button"
                                       href="/tasks/${task.uuid}?action=send">Send</a>
                                </#if>
                            </#if>
                        </div>
                </div>
            </div>
        </div>
        <#if task.taskState != "OPEN">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Author</th>
                    <th scope="col">Content</th>
                    <th scope="col">Date</th>
                    <th scope="col">Files</th>
                </tr>
                </thead>
                <tbody>
                <#if activity??>
                    <#list activity.commentResponseList as comment>
                        <tr>
                            <td>${comment.createdBy}</td>
                            <td>${comment.content}</td>
                            <td>${comment.date}</td>
                            <td>
                                <#if comment.files??>
                                    <#list comment.files as file>
                                        <a target="_blank" href="http://localhost/files/${file.storageFileName}" class="btn btn-primary px-4"
                                           role="button">${file.originalFileName}</a>
                                    </#list>
                                </#if>
                            </td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </#if>
    </div>
</main>
<@footer.footer />
</body>
</html>