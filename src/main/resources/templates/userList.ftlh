<#import "parts/common.ftlh" as c>

<@c.page>
    <h5 class="mb-4">Пользователи</h5>

    <table class="table">
        <thead>
        <th>Имя пользователя</th>
        <th>Роль</th>
        <th></th>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td>
                    <#list user.roles as role>${role}<#sep>, </#list>
                </td>
                <td><a href="/user/${user.id}">Редактировать</a></td>
            </tr>
        </#list>
        </tbody>
    </table>

</@c.page>