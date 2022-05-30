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
<div class="container">
    <a class="btn btn-primary" href="/tasks/add" role="button">Add</a>
    <ul class="nav nav-tabs justify-content-center">
        <li class="nav-item">
            <a class="nav-link" href="/tasks?section=OPEN">Open</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/tasks?section=IN_PROGRESS">In progress</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/tasks?section=RESOLVED">Resolved</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/tasks?section=COMPLETED">Completed</a>
        </li>
    </ul>

    <br>
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
        <#if tasks??>
            <#list tasks as task>
                <div class="col">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h2>${task.title}</h2>
                            <p class="card-text">${task.annotation}</p>
                            <small class="text-muted">${task.createdBy}</small>
                            <br>
                            <small class="text-muted">${task.taskState}</small>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" onClick="document.location='/tasks/${task.uuid}'" class="btn btn-sm btn-outline-secondary">View</button>
                                </div>
                                <small class="text-muted">${task.date}</small>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </#if>
    </div>
<#--    <table class="table">-->
<#--        <thead>-->
<#--        <tr>-->
<#--            <th scope="col">Title</th>-->
<#--            <th scope="col">State</th>-->
<#--            <th scope="col">Created by</th>-->
<#--            <th scope="col">Action</th>-->
<#--        </tr>-->
<#--        </thead>-->
<#--        <tbody>-->
<#--        <#if tasks??>-->
<#--            <#list tasks as task>-->
<#--                <tr>-->
<#--                    <td>${task.title}</td>-->
<#--                    <td>${task.taskState}</td>-->
<#--                    <td>${task.createdBy}</td>-->
<#--                    <td>-->
<#--                        <a class="btn btn-primary" role="button" href="/tasks/${task.uuid}">Open</a>-->
<#--                    </td>-->
<#--                </tr>-->
<#--            </#list>-->
<#--        </#if>-->
<#--        </tbody>-->
<#--    </table>-->
</div>
<@footer.footer />
</body>
</html>