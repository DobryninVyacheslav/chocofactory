<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>

    <form action="/batch/create" method="post">
        <a class="btn btn-primary mt-3 mb-3" data-bs-toggle="collapse" href="#collapseBatch" role="button"
           aria-expanded="false"
           aria-controls="collapseExample">
            Несформированные партии
        </a>

        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-primary ms-2" type="submit">Добавить партию</button>
    </form>

    <div class="collapse" id="collapseBatch">
        <table class="table">
            <thead>
            <th>Темп. шок-да</th>
            <th>Скорость перемеш-я шок-да</th>
            <th>Размер порции шок-да</th>
            <th>Время взбив-я сливок</th>
            <th>Скорость взбив-я сливок</th>
            <th>Тип напол-я</th>
            <th>Консистенция напол-я</th>
            <th>Вес напол-я</th>
            <th>Вес орехов</th>
            <th>Тип помола орехов</th>
            <th>Тип упаковки</th>
            <th>Выбор</th>
            </thead>
            <tbody>
            <#list batches as batch>
                <tr>
                    <td>${batch.chocolateTemperature!""}</td>
                    <td>${batch.chocolateStirringSpeed!""}</td>
                    <td>${batch.chocolateServingSize!""}</td>
                    <td>${batch.creamWhippingTime!""}</td>
                    <td>${batch.creamWhippingSpeed!""}</td>
                    <td>${batch.fillerType!""}</td>
                    <td>${batch.fillerConsistency!""}</td>
                    <td>${batch.fillerWeight!""}</td>
                    <td>${batch.nutsWeight!""}</td>
                    <td>${batch.nutsGrindingType!""}</td>
                    <td>${batch.packagingType!""}</td>
                    <td><a href="/batch/${batch.id}">Выбрать</a></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <#if isTechnologist || isAdmin>
        <form class="row g-3" action="/batch/save-chocolate/${batch.id!""}" method="post">

            <h5>Подготовка шоколада</h5>

            <div class="col-md-4">
                <label for="validationDefault01" class="form-label">Температура</label>
                <input type="number" step="any" class="form-control" id="validationDefault01"
                       name="chocolateTemperature"
                       value="${batch.chocolateTemperature!"0"}" required>
            </div>
            <div class="col-md-4">
                <label for="validationDefault02" class="form-label">Скорость перемешивания</label>
                <input type="number" step="any" class="form-control" id="validationDefault02"
                       name="chocolateStirringSpeed"
                       value="${batch.chocolateStirringSpeed!"0"}" required>
            </div>
            <div class="col-md-4">
                <label for="validationDefault03" class="form-label">Размер порции</label>
                <input type="number" step="any" class="form-control" id="validationDefault03"
                       name="chocolateServingSize"
                       value="${batch.chocolateServingSize!"0"}" required>
            </div>
            <div class="col-12">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button class="btn btn-primary" type="submit">Сохранить</button>
            </div>
        </form>
    </#if>

    <#if isBeater || isAdmin>
        <form class="row g-3 mt-2" action="/batch/save-cream/${batch.id!""}" method="post">

            <h5>Взбивание сливок</h5>

            <div class="col-md-6">
                <label for="validationDefault01" class="form-label">Время взбивания</label>
                <input type="number" step="any" class="form-control" id="validationDefault01"
                       name="creamWhippingTime"
                       value="${batch.creamWhippingTime!"0"}" required>
            </div>
            <div class="col-md-6">
                <label for="validationDefault02" class="form-label">Скорость взбивания</label>
                <input type="number" step="any" class="form-control" id="validationDefault02"
                       name="creamWhippingSpeed"
                       value="${batch.creamWhippingSpeed!"0"}" required>
            </div>
            <div class="col-12">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button class="btn btn-primary" type="submit">Сохранить</button>
            </div>
        </form>
    </#if>

    <#if isResearcher || isAdmin>
        <form class="row g-3 mt-2" action="/batch/save-filler/${batch.id!""}" method="post">

            <h5>Задать наполнитель</h5>

            <div class="col-md-4">
                <label for="validationDefault01" class="form-label">Тип наполнителя</label>
                <input type="text" class="form-control" id="validationDefault01"
                       name="fillerType"
                       value="${batch.fillerType!""}" required>
            </div>
            <div class="col-md-4">
                <label for="validationDefault02" class="form-label">Консистенция наполнителя</label>
                <input type="text" class="form-control" id="validationDefault02"
                       name="fillerConsistency"
                       value="${batch.fillerConsistency!""}" required>
            </div>
            <div class="col-md-4">
                <label for="validationDefault03" class="form-label">Вес наполнителя</label>
                <input type="number" step="any" class="form-control" id="validationDefault03"
                       name="fillerWeight"
                       value="${batch.fillerWeight!"0"}" required>
            </div>
            <div class="col-12">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button class="btn btn-primary" type="submit">Сохранить</button>
            </div>
        </form>
    </#if>

    <#if isSquirrel || isAdmin>
        <form class="row g-3 mt-2" action="/batch/save-nuts/${batch.id!""}" method="post">

            <h5>Порция орехов</h5>

            <div class="col-md-6">
                <label for="validationDefault01" class="form-label">Вес орехов</label>
                <input type="number" step="any" class="form-control" id="validationDefault01"
                       name="nutsWeight"
                       value="${batch.nutsWeight!"0"}" required>
            </div>
            <div class="col-md-6">
                <label for="validationDefault02" class="form-label">Тип помола</label>
                <input type="text" class="form-control" id="validationDefault02"
                       name="nutsGrindingType"
                       value="${batch.nutsGrindingType!""}" required>
            </div>
            <div class="col-12">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button class="btn btn-primary" type="submit">Сохранить</button>
            </div>
        </form>
    </#if>

    <#if isPacker || isAdmin>
        <form class="row g-3 mt-2" action="/batch/save-packaging/${batch.id!""}" method="post">

            <h5>Упаковка партии</h5>

            <div class="col-md-12">
                <label for="validationDefault01" class="form-label">Тип упаковки</label>
                <input type="text" class="form-control" id="validationDefault01"
                       name="packagingType"
                       value="${batch.packagingType!""}" required>
            </div>

            <div class="col-12">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button class="btn btn-primary" type="submit">Сохранить</button>
            </div>
        </form>
    </#if>

    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <a class="btn btn-success mt-5 mb-5" href="">Оформить доставку</a>
    </div>

</@c.page>