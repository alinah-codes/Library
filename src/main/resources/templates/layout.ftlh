<#import "spring.ftl" as spring>
<#macro layout >

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>JobSearch</title>
        <link rel="stylesheet" href=../static/styles/styles.css>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
                crossorigin="anonymous"></script>
    </head>

    <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Library</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                    <li class="nav-item">
                        <a class="nav-link" href="/login">Логин</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Язык
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/?lang=ru">Русский</a></li>
                            <li><a class="dropdown-item" href="/?lang=en"> Английский</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="#">Something else here</a></li>
                        </ul>
                    </li>

                </ul>
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Поиск" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Поиск</button>
                </form>
            </div>

<#--            <#assign known = SPRING_SECURITY_CONTEXT?? >-->
<#--            <#if known>-->
<#--                <#assign-->
<#--                user = SPRING_SECURITY_CONTEXT.authentication.principal-->
<#--                name = user.getUsername()-->
<#--                >-->
<#--                <#if user??>-->
<#--                    <a href="/profile"><i class="bi bi-person-circle text-white fs-3" style="margin: 0 1px 0 8px; cursor: pointer;" ></i></a>-->
<#--                    <form action="/logout" method="post">-->
<#--                        <#if _csrf??>-->
<#--                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"-->
<#--                                   value="${(_csrf.token)!'csrf-token'}"/>-->
<#--                        </#if>-->
<#--                        <button type="submit" class="btn btn-outline-info m-2"-->
<#--                                id="logout">-->
<#--                            <@spring.message "layout.logout"/>-->
<#--                        </button>-->
<#--                    </form>-->
<#--                </#if>-->
<#--            <#else>-->
                <a href="/login"
                   class="btn btn-outline-info m-2">Login</a>
                <a href="/auth/register"
                   class="btn btn-outline-info m-2">Register</a>
<#--            </#if>-->

        </div>
    </nav>


    <div class="container">
        <#nested spring>
    </div>
    </body>

    </html>

</#macro>