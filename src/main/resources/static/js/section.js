document.addEventListener('DOMContentLoaded', () => {
    document.querySelector('#open-tab').addEventListener('click', () => {

        $.ajax({
            url: '/tasks/tsl?section=OPEN',
            method: 'GET',
            success: function (data){

                let tasks = $('#tasks')
                tasks.html(data)
            },
            contentType: "text/html"
        })
    })
})
