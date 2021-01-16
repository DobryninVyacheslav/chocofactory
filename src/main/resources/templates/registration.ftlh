<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<h5 class="mb-4">Регистрация</h5>
${message!""}
<@l.login "/registration" true/>
</@c.page>