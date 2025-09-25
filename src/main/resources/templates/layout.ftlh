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
            <a class="navbar-brand" href="/">Литература</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <a class="nav-link" href="/">Мои книги</a>

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
                   class="btn btn-outline-info m-2">Авторизация</a>
                <a href="/auth/register"
                   class="btn btn-outline-info m-2">Регистрация</a>
<#--            </#if>-->

        </div>
    </nav>


    <div>
        <#nested spring>
    </div>
    </body>

    </html>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</#macro>