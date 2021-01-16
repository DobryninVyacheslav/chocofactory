<#import "parts/common.ftlh" as c>

<@c.page>
    <h5 class="mb-4">Редактор пользователя</h5>
    <form action="/user" method="post">
        <input type="text" name="username" class="form-control mb-2" value="${user.username}">


        <#list roles as role>

            <div class="form-check">
                <label>
                    <input type="checkbox" class="form-check-input"
                           name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>
                    ${role}
                </label>
            </div>

        </#list>

        <input type="hidden" name="userId" value="${user.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary mt-2">Сохранить</button>
    </form>
</@c.page>