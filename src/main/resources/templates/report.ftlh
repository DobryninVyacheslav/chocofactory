<#import "parts/common.ftlh" as c>

<@c.page>

    <a class="btn btn-primary mt-3" data-bs-toggle="collapse" href="#collapseReport" role="button"
       aria-expanded="false"
       aria-controls="collapseExample">
        Добавить новый отчёт
    </a>

    <div class="collapse" id="collapseReport">
        <div class="form-group mt-3">
            <form method="post" id="report-period" name="report-period">
                <input type="date" class="form-control" name="startOfPeriod" placeholder="Начало периода">
                <input type="date" class="form-control mt-2" name="endOfPeriod" placeholder="Конец периода">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </form>
        </div>
    </div>

    <div id="chart-container" style="height: 400px;"></div>

    <div class="row row-cols-1 row-cols-md-3 g-4">
        <#list reports as report>
            <div class="col">
                <div class="card my-3">
                    <#if report.filename??>
                        <img src="/img/${report.filename}" class="card-img-top">
                    </#if>
                    <div class="m-2">
                        Отчет №${report.id}
                    </div>
                    <div class="m-2">
                        Начало периода: ${report.startOfPeriod}
                    </div>
                    <div class="m-2">
                        Конец периода: ${report.endOfPeriod}
                    </div>
                    <div class="card-footer text-muted">
                        ${report.authorName}
                    </div>
                </div>
            </div>
        <#else>
            <h6 class="mt-5">Нет отчётов</h6>
        </#list>
    </div>



    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="/static/index.js"></script>
</@c.page>