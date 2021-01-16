<#import "parts/common.ftlh" as c>

<@c.page>

    <form class="row g-3 mt-2 mb-2" action="/delivery/${batch.id}" method="post">

        <h5>Доставка</h5>

        <div class="col-md-4">
            <label for="validationDefault01" class="form-label">Заказ</label>
            <input type="text" class="form-control" id="validationDefault01"
                   name="order" required>
        </div>
        <div class="col-md-4">
            <label for="validationDefault02" class="form-label">Адрес доставки</label>
            <input type="text" class="form-control" id="validationDefault02"
                   name="deliveryAddress" required>
        </div>
        <div class="col-md-4">
            <label for="validationDefault03" class="form-label">Дата доставки</label>
            <input type="date" class="form-control" id="validationDefault03"
                   name="deliveryDate" required>
        </div>

        <div class="col-12">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button class="btn btn-primary" type="submit">Сформировать партию</button>
        </div>
    </form>

    <table class="table mt-5">
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
        </thead>
        <tbody>
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
        </tr>
        </tbody>
    </table>

</@c.page>