<#import "parts/common.ftlh" as c>

<@c.page>

    <a class="btn btn-primary mt-3 mb-3" data-bs-toggle="collapse" href="#collapseBatch" role="button"
       aria-expanded="false"
       aria-controls="collapseExample">
        Несформированные партии
    </a>

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

    <form class="row g-3" action="/batch/save/${batch.id!""}" method="post">

        <h5>Подготовка шоколада</h5>

        <div class="col-md-4">
            <label for="validationDefault01" class="form-label">Температура</label>
            <input type="number" step="any" class="form-control" id="validationDefault01" name="chocolateTemperature"
                   value="${batch.chocolateTemperature!"0"}" required>
        </div>
        <div class="col-md-4">
            <label for="validationDefault02" class="form-label">Скорость перемешивания</label>
            <input type="number" step="any" class="form-control" id="validationDefault02" name="chocolateStirringSpeed"
                   value="${batch.chocolateStirringSpeed!"0"}" required>
        </div>
        <div class="col-md-4">
            <label for="validationDefault03" class="form-label">Размер порции</label>
            <input type="number" step="any" class="form-control" id="validationDefault03" name="chocolateServingSize"
                   value="${batch.chocolateServingSize!"0"}" required>
        </div>
        <div class="col-12">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button class="btn btn-primary" type="submit">Сохранить</button>
        </div>
    </form>
</@c.page>