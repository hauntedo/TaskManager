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
    <title>Tasks</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>
<@header.header />
<a class="btn btn-primary" href="/tasks/add" role="button">Add</a>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Title</th>
        <th scope="col">State</th>
        <th scope="col">Created by</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <#if tasks??>
    <#list tasks as task>
        <tr>
            <td>${task.title}</td>
            <td>${task.taskState}</td>
            <td>${task.createdBy}</td>
            <td>
                <a class="btn btn-primary" role="button" href="/tasks/${task.uuid}">Open</a>
            </td>
        </tr>
    </#list>
    </#if>
    </tbody>
</table>
<@footer.footer />
</body>
</html>