<#import "parts/common.ftlh" as c>

<@c.page>

    <div class="form-row">
        <div class="form-group col-md-4">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="ingredients" class="form-control" value=""${ingredients!""}
                       placeholder="Искать по ингридиентам"/>
                <button type="submit" class="btn btn-primary mt-3">Найти</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary mt-3" data-bs-toggle="collapse" href="#collapseRequest" role="button"
       aria-expanded="false"
       aria-controls="collapseExample">
        Добавить новую заявку
    </a>

    <div class="collapse" id="collapseRequest">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <input type="text" class="form-control" name="ingredients" placeholder="Введите сырье">
                <input type="number" class="form-control" name="quantity" placeholder="Введите количество">
                <input type="text" class="form-control" name="date" placeholder="Введите дату">
                <div class="mb-3">
                    <input class="form-control" type="file" name="file">
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Заказать</button>
            </form>
        </div>
    </div>

    <div class="row row-cols-1 row-cols-md-3 g-4">
        <#list requests as request>
            <div class="col">
                <div class="card my-3">
                    <#if request.filename??>
                        <img src="/img/${request.filename}" class="card-img-top">
                    </#if>
                    <div class="m-2">
                        Ингридиент: ${request.ingredients}
                    </div>
                    <div class="m-2">
                        Количество: ${request.quantity}
                    </div>
                    <div class="m-2">
                        Дата: ${request.date}
                    </div>
                    <div class="card-footer text-muted">
                        ${request.authorName}
                    </div>
                </div>
            </div>
        <#else>
            <h6 class="mt-5">Нет заявок</h6>
        </#list>
    </div>
</@c.page>