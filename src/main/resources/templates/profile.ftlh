<#import "parts/common.ftlh" as c>

<@c.page>

    <h5>Пользователь: ${username}</h5>

    <form method="post">
        <div class="form-group row mt-4">
            <label class="col-sm-2 col-form-label">Новый email:</label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" placeholder="some@some.com"
                       value="${email!""}"/>
            </div>
        </div>
        <div class="form-group row mt-2 mb-2">
            <label class="col-sm-2 col-form-label">Новый пароль:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Пароль"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-primary" type="submit">Сохранить</button>
    </form>

    <h5 class="mt-5">Сообщения</h5>

    <div class="list-group mt-5 mb-5">
        <#list notifications as notification>
            <a class="list-group-item list-group-item-action">
                <div class="d-flex w-100 justify-content-between">
                    <h6 class="mb-1">Партия №${notification.batchId}: ${notification.message}</h6>
                    <small class="text-muted">${notification.creationTime}</small>
                </div>
            </a>
        </#list>
    </div>
</@c.page>