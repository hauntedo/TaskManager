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
<div class="container">
    <header class="p-3 mb-3 border-bottom">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="/" class="nav-link px-2 link-dark"><h5>TaskManager</h5></a></li>
                    <li><a href="/tasks" class="nav-link px-2 link-dark"><h5>Tasks</h5></a></li>
                    <li><a href="#" class="nav-link px-2 link-dark"><h5>About</h5></a></li>
                </ul>
                <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
                    <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
                </form>
                <div class="dropdown text-end">
                    <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-menu-button-wide" viewBox="0 0 16 16">
                            <path d="M0 1.5A1.5 1.5 0 0 1 1.5 0h13A1.5 1.5 0 0 1 16 1.5v2A1.5 1.5 0 0 1 14.5 5h-13A1.5 1.5 0 0 1 0 3.5v-2zM1.5 1a.5.5 0 0 0-.5.5v2a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5v-2a.5.5 0 0 0-.5-.5h-13z"/>
                            <path d="M2 2.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5zm10.823.323-.396-.396A.25.25 0 0 1 12.604 2h.792a.25.25 0 0 1 .177.427l-.396.396a.25.25 0 0 1-.354 0zM0 8a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V8zm1 3v2a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2H1zm14-1V8a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v2h14zM2 8.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0 4a.5.5 0 0 1 .5-.5h6a.5.5 0 0 1 0 1h-6a.5.5 0 0 1-.5-.5z"/>
                        </svg>
                    </a>
                    <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1" style="">
                        <li><a class="dropdown-item" href="/profile">Profile</a></li>
                        <li><a class="dropdown-item" href="/profile/edit">Edit profile</a></li>
                        <li><a class="dropdown-item" href="/tasks/my">My Tasks</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="/sign_out">Sign out</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </header>
</div>
<div class="container">
    <a class="btn btn-primary" href="/tasks/add" role="button">Add</a>
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
        <#if tasks??>
            <#list tasks as task>
                <div class="col">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h2>${task.title}</h2>
                            <p class="card-text">${task.annotation}</p>
                            <small class="text-muted">${task.createdBy}</small>
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