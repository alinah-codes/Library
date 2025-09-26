<#import '../layout.ftlh' as main>

<@main.layout ; spring>
    <div class="container mt-5">
        <h3>Оформление заявки на получение книги</h3>
        <form method="post" action="/request/${book.id}">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>
            <!-- Номер читательского билета -->
            <div class="mb-3">
                <label for="ticketNumber" class="form-label"> Номер читательского билета</label>
                <input type="text" class="form-control"
                       value="${ticketNumber}"
                       disabled>
                <input type="hidden" name="ticketNumber" value="${ticketNumber}">
            </div>

            <!-- Информация о книге -->
            <div class="mb-3">
                <label class="form-label">Книга</label>
                <input type="text" class="form-control"
                       value="${book.id} - ${book.author} - ${book.title}"
                       readonly>
                <input type="hidden" name="bookId" value="${book.id}">
            </div>

            <!-- Дата возврата -->
            <div class="mb-3">
                <label for="returnDate" class="form-label">Дата возврата</label>
                <input type="date" name="returnDate" required >
            </div>

            <!-- Кнопка -->
            <button type="submit" class="btn btn-primary">Подать заявку</button>
        </form>
        <#if errorMessage??>
            <span style="color: red">${errorMessage}</span>
        </#if>
    </div>
</@main.layout>
