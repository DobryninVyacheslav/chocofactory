window.onload = function() {
    document.getElementById('report-period').onsubmit = function() {
        /* do what you want with the form */

        let startOfPeriod = $('#report-period input[name="startOfPeriod"]').val();
        let endOfPeriod = $('#report-period input[name="endOfPeriod"]').val();
        let data = 'startOfPeriod=' + startOfPeriod + '&endOfPeriod=' + endOfPeriod;

        $.ajax({
            url:'/report/chart-data',
            data: data,
            success: function (result) {
                var days = JSON.parse(result).days;
                var batches = JSON.parse(result).batches;
                drawChart(days, batches);
            }
        })

        return false;
    }

    document.getElementById("download").addEventListener("click", () => {
        let chartContainer = this.document.getElementById("report");
        console.log(chartContainer);
        console.log(window);
        var opt = {
            margin:       1,
            filename:     'Отчёт.pdf',
            image:        { type: 'jpeg', quality: 0.8 },
            html2canvas:  { scale: 1 },
            jsPDF:        { unit: 'mm', format: 'a4', orientation: 'landscape' }
        };

        html2pdf().from(chartContainer).set(opt).save();
    })
}

function drawChart(days, batches) {
    Highcharts.chart('chart-container', {

        title: {
            text: 'График числа пратий в сутки'
        },

        yAxis: {
            title: {
                text: 'Число партий'
            }
        },

        xAxis: {
            categories: days
        },

        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle'
        },

        series: [{
            name: 'Партии',
            data: batches
        }],

        responsive: {
            rules: [{
                condition: {
                    maxWidth: 500
                },
                chartOptions: {
                    legend: {
                        layout: 'horizontal',
                        align: 'center',
                        verticalAlign: 'bottom'
                    }
                }
            }]
        }

    });
}