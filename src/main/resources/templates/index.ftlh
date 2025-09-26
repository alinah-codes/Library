<#import "layout.ftlh" as main>
<#import "./macros/pagination.ftlh" as p>
<@main.layout ; spring>
    <#if successMessage??>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            ${successMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </#if>
    <div style="display: flex">

        <div class="sidebar"
             style="height: 90vh; overflow: auto;  width: 250px; background: #f8f9fa; padding: 20px; border-right: 1px solid #ddd;">
            <h5 style="margin-bottom: 15px;">Жанры</h5>
            <ul class="list-group list-index" style="width: 100%; border-radius: 8px; overflow: hidden;">
                <li class="list-group-item ${(selectedCategoryId??)?then('', 'active')}">
                    <a href="/books" class="category-link">Все</a>
                </li>

                <#list categories as category>
                    <li class="list-group-item ${(selectedCategoryId?? && selectedCategoryId == category.id)?then('active','')}">
                        <a href="/books?categoryId=${category.id}" class="category-link">${category.name}</a>
                    </li>
                </#list>
            </ul>
        </div>


        <div class="content" style="flex: 1; display: flex; flex-wrap: wrap; gap: 20px; padding: 30px;">
            <#list books.getContent() as book>
                <div class="card"
                     style="width: 33rem; height: 16rem;  display: flex; flex-direction: row; align-items: stretch; border-radius: 10px; box-shadow: 0 2px 6px rgba(0,0,0,0.1); overflow: hidden;">

                    <#if book.image??>
                        <img src="/static/images/book2.jpg"
                             alt="${book.title}"
                             style="width: 300px; height: auto; object-fit: cover;">
                    <#else>
                        <img src="/static/images/default.jpg"
                             alt="Default"
                             style="width: 300px; height: auto; object-fit: cover;">
                    </#if>

                    <div class="card-body"
                         style="padding: 15px; display: flex; flex-direction: column; justify-content: space-between; flex: 1;">
                        <div>
                            <h5 class="card-title">${book.title}</h5>
                            <h5 class="card-title">${book.author}</h5>
                            <p class="card-text">${book.description}</p>
                        </div>
                        <#if userRequests?? && userRequests[book.id?string]??>
                            <div class="alert alert-info w-100">
                                Дата возврата: ${userRequests[book.id?string]?date("yyyy-MM-dd")?string("dd.MM.yyyy")}
                            </div>
                        <#else>
                            <a href="/request/${book.id}" class="btn btn-primary w-100">Получить</a>
                        </#if>


                    </div>
                </div>

            </#list>
        </div>
    </div>
    <@p.pager url="/books" page=books direction=direction categoryId=selectedCategoryId! />

    <script>
        document.addEventListener("DOMContentLoaded", () => {
            const items = document.querySelectorAll(".list-group-item");

            items.forEach(item => {
                item.addEventListener("click", () => {
                    items.forEach(el => el.classList.remove("active"));
                    item.classList.add("active");
                });
            });
        });
    </script>



</@main.layout>