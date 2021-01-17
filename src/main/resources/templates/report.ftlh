<#import "parts/common.ftlh" as c>

<@c.page>

    <a class="btn btn-primary mt-3" data-bs-toggle="collapse" href="#collapseReport" role="button"
       aria-expanded="false"
       aria-controls="collapseExample">
        Параметры отчёта
    </a>

    <button id="download" class="btn btn-primary ms-2 mt-3" type="submit">Сохранить отчёт как PDF</button>

    <div class="collapse" id="collapseReport">
        <div class="form-group mt-3">
            <form id="report-period" name="report-period">
                <input type="date" class="form-control" name="startOfPeriod" placeholder="Начало периода">
                <input type="date" class="form-control mt-2" name="endOfPeriod" placeholder="Конец периода">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button id="generate-button" type="submit" class="btn btn-primary mt-3">Сгенерировать</button>
            </form>
        </div>
    </div>

    <div id="report">
        <div class="mb-5" id="chart-container" style="height: 400px;"></div>

        <table id="batch-table" class="table mt-5"></table>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.2/html2pdf.bundle.min.js"></script>
    <script src="/static/chartAndPdfConverner.js"></script>
    <script src="/static/table.js"></script>
</@c.page>