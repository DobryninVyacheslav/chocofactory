<#import "parts/common.ftlh" as c>

<@c.page>

    <a class="btn btn-primary mt-3" data-bs-toggle="collapse" href="#collapseReport" role="button"
       aria-expanded="false"
       aria-controls="collapseExample">
        Параметры отчёта
    </a>

    <div class="collapse" id="collapseReport">
        <div class="form-group mt-3">
            <form id="report-period" name="report-period">
                <input type="date" class="form-control" name="startOfPeriod" placeholder="Начало периода">
                <input type="date" class="form-control mt-2" name="endOfPeriod" placeholder="Конец периода">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary mt-2">Сгенерировать</button>
            </form>
        </div>
    </div>

    <div class="mb-5" id="chart-container" style="height: 400px;"></div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="/static/index.js"></script>
</@c.page>