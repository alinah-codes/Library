<#import '../layout.ftlh' as main>
<@main.layout ; spring>

    <div class="container mt-4">
        <h2>Профиль пользователя</h2>
        <p><b>ФИО:</b> ${user.fullName}</p>
        <p><b>Читательский билет:</b> ${user.passportNumber}</p>

        <hr>

        <h4>История книг</h4>

        <!-- Фильтр по диапазону дат -->
        <form method="get" action="/profile" class="mb-3">
            <div class="row">
                <div class="col">
                    <input type="date" name="from" class="form-control" placeholder="С даты">
                </div>
                <div class="col">
                    <input type="date" name="to" class="form-control" placeholder="По дату">
                </div>
                <div class="col">
                    <button type="submit" class="btn btn-primary w-100">Фильтровать</button>
                </div>
            </div>
        </form>

        <h3>Мои заявки</h3>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Книга</th>
                <th>Дата выдачи</th>
                <th>Дата возврата</th>
                <th>Статус</th>
                <th>Возврат</th>
            </tr>
            </thead>
            <tbody>
            <#list requests as req>
                <tr>
                    <td>${req.book.title}</td>
                    <td>
                        <#if req.created??>
                            ${req.created}
                        <#else>-</#if>
                    </td>
                    <td>
                        <#if req.returnDate??>
                            <#if req.overdue>
                                <span style="color:red;">
                                ${req.returnDate} (просрочено)
                            </span>
                            <#else>
                                ${req.returnDate}
                            </#if>
                        <#else>-</#if>
                    </td>
                    <#if req.status == "ВОЗВРАЩЕНО" >
                        <td style="color: green">${req.status}</td>

                    <#elseif req.status == "ОЖИДАЕТСЯ">
                        <td style="color: #abab9a">${req.status}</td>
                    <#else>
                        <td style="color: red">${req.status}</td>
                    </#if>

                    <#if req.status == "ВОЗВРАЩЕНО" >
                        <td><span>-</span></td>

                    <#else>
                        <form action="request/return/${req.book.id}" method="post">
                            <#if _csrf??>
                                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                       value="${(_csrf.token)!'csrf-token'}"/>
                            </#if>
                            <td>
                                <button type="submit" class="btn btn-success">Возврат книг</button>
                            </td>
                        </form>
                    </#if>
                </tr>
            </#list>
            </tbody>
        </table>

    </div>
</@main.layout>
