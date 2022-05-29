<!doctype html>
<#import "spring.ftl" as spring/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Forgot password</title>
    <link rel="stylesheet" href="<@spring.url "/css/signin.css"/>">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
<div class="container d-flex flex-column">
    <div class="row align-items-center justify-content-center g-0 min-vh-100">
        <div class="col-lg-5 col-md-8 py-8 py-xl-0">
            <!-- Card -->
            <div class="card shadow">
                <!-- Card body -->
                <div class="card-body p-6">
                    <div class="mb-4">
                        <h1 class="mb-1 fw-bold">Forgot Password</h1>
                        <p>Fill the form to reset your password.</p>
                    </div>
                    <!-- Form -->
                    <form action="/forgot_password" method="post">
                        <!-- Email -->
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" id="email" class="form-control" name="email" placeholder="Enter Your Email" required="">
                        </div>
                        <#if message??>
                            <h6 class="text">${message}</h6>
                        </#if>
                        <!-- Button -->
                        <div class="mb-3 d-grid">
                            <button type="submit" class="btn btn-primary">
                                Send Reset Link
                            </button>
                        </div>
                        <span>Return to <a href="/sign_in">sign in</a></span>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>