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