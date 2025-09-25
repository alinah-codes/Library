<#import "/spring.ftl" as spring>

<#macro pager url page sortBy="updateTime" direction="desc" categoryId="">
    <#if page?? && (page.getTotalPages() gt 1)>
    <#-- Собираем query параметры -->
        <#assign queryParams = "size=${page.getSize()}&sortBy=${sortBy}&direction=${direction}" />
        <#if categoryId?? && categoryId?has_content>
            <#assign queryParams = queryParams + "&categoryId=" + categoryId />
        </#if>

        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <!-- Prev -->
                <#if page.getNumber() gt 0>
                    <li class="page-item">
                        <a class="page-link"
                           href="${url}?page=${page.getNumber() - 1}&${queryParams}"
                           aria-label="Previous">
                            Пред
                        </a>
                    </li>
                <#else>
                    <li class="page-item disabled" aria-disabled="true">
                        <span class="page-link" aria-hidden="true">Пред</span>
                    </li>
                </#if>

                <!-- Pages -->
                <#list 1..page.getTotalPages() as p>
                    <#assign isActive = (p == page.getNumber() + 1)>
                    <li class="page-item ${isActive?then('active','')}" <#if isActive>aria-current="page"</#if>>
                        <#if isActive>
                            <span class="page-link">${p}</span>
                        <#else>
                            <a class="page-link"
                               href="${url}?page=${p - 1}&${queryParams}">${p}</a>
                        </#if>
                    </li>
                </#list>

                <!-- Next -->
                <#if page.getNumber() lt (page.getTotalPages() - 1)>
                    <li class="page-item">
                        <a class="page-link"
                           href="${url}?page=${page.getNumber() + 1}&${queryParams}"
                           aria-label="Next">
                            След
                        </a>
                    </li>
                <#else>
                    <li class="page-item disabled" aria-disabled="true">
                        <span class="page-link">След</span>
                    </li>
                </#if>
            </ul>
        </nav>
    </#if>
</#macro>
