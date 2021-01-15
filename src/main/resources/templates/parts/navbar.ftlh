<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Шоколадная фабрика</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/">Главная</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/main">Заявки</a>
                </li>

                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link" href="/user">Список пользователей</a>
                    </li>
                </#if>
            </ul>

            <div class="navbar-text me-3">${name!""}</div>
            <@l.logout/>

        </div>
    </div>
</nav>

