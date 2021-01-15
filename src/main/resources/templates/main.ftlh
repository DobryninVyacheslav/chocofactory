<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div>
        <@l.logout/>
        <span><a href="/user">User List</a></span>
    </div>

    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="ingredients" placeholder="Введите сырье"/>
            <input type="number" name="quantity" placeholder="Введите количество">
            <input type="text" name="date" placeholder="Введите дату">
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>Список сообщений</div>
    <form method="get" action="/main">
        <input type="text" name="ingredients" value=""${ingredients!""}>
        <button type="submit">Найти</button>
    </form>
    <#list requests as request>
        <div>
            <b>${request.id}</b>
            <span>${request.ingredients}</span>
            <i>${request.quantity}</i>
            <span>${request.date}</span>
            <strong>${request.authorName}</strong>
            <div>
                <#if request.filename??>
                    <img src="/img/${request.filename}">
                </#if>
            </div>
        </div>
    <#else>
        Нет заявок
    </#list>
</@c.page>