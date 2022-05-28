<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <title>Error Page</title>
</head>
<body>
<div class="container text-center">
    <div class="p-5 mb-8 rounded-3">
        <div class="container-fluid py-5">
            <h1 class="display-5 fw-bold">Oops! ${status}</h1>
            <p class="col-md-12 fs-4">Sorry, an error has occured, ${message}!</p>
            <a href="/" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
                Take Me Home </a>
        </div>
    </div>
</div>
</body>
</html>