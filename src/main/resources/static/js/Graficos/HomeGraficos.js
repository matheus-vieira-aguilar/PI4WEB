


mediaFeminina = []
mediaMasculina = []

for (let index = 0; index < media.length; index++) {

    var autor = media[index].autor.$name

    switch (autor) {
        case "POLLOCK7D":
            if (media[index].sexo.$name == "MASCULINO") { mediaMasculina[0] = parseFloat(media[index].media.toFixed(2)) }
            if (media[index].sexo.$name == "FEMININO") { mediaFeminina[0] = parseFloat(media[index].media.toFixed(2)) }
            break;
        case "POLLOCK3D":
            if (media[index].sexo.$name == "MASCULINO") { mediaMasculina[1] = parseFloat(media[index].media.toFixed(2)) }
            if (media[index].sexo.$name == "FEMININO") { mediaFeminina[1] = parseFloat(media[index].media.toFixed(2)) }
            break;
        case "GUEDES":
            if (media[index].sexo.$name == "MASCULINO") { mediaMasculina[2] = parseFloat(media[index].media.toFixed(2)) }
            if (media[index].sexo.$name == "FEMININO") { mediaFeminina[2] = parseFloat(media[index].media.toFixed(2)) }
            break;
        case "PETROSKY":
            if (media[index].sexo.$name == "MASCULINO") { mediaMasculina[3] = parseFloat(media[index].media.toFixed(2)) }
            if (media[index].sexo.$name == "FEMININO") { mediaFeminina[3] = parseFloat(media[index].media.toFixed(2)) }
            break;
        case "THORLAND7D":
            if (media[index].sexo.$name == "MASCULINO") { mediaMasculina[4] = parseFloat(media[index].media.toFixed(2)) }
            if (media[index].sexo.$name == "FEMININO") { mediaFeminina[4] = parseFloat(media[index].media.toFixed(2)) }
            break;
        case "THORLAND3D":
            if (media[index].sexo.$name == "MASCULINO") { mediaMasculina[5] = parseFloat(media[index].media.toFixed(2)) }
            if (media[index].sexo.$name == "FEMININO") { mediaFeminina[5] = parseFloat(media[index].media.toFixed(2)) }
            break;
    }

}

Highcharts.chart('lineChart', {
    chart: {
        type: 'line'
    },
    title: {
        text: ''
    },
    xAxis: {
        categories: ['POLLOCK7D', 'POLLOCK3D', 'GUEDES', 'PETROSKY', 'THORLAND7D', 'THORLAND3D']
    },
    yAxis: {
        title: {
            text: 'Porcentagem (%)'
        }
    },
    plotOptions: {
        line: {
            dataLabels: {
                enabled: true
            },
            enableMouseTracking: false
        }
    },
    series: [{
        name: 'MASCULINO',
        data: mediaMasculina
    }, {
        name: 'FEMININO',
        data: mediaFeminina
    }]
});



Highcharts.chart('lineIMC', {
    chart: {
        zoomType: 'xy'
    },
    title: {
        text: 'Average Monthly Temperature and Rainfall in Tokyo'
    },
    subtitle: {
        text: 'Source: WorldClimate.com'
    },
    xAxis: [{
        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
            'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
        crosshair: true
    }],
    yAxis: [{ // Primary yAxis
        labels: {
            format: '{value}°C',
            style: {
                color: Highcharts.getOptions().colors[1]
            }
        },
        title: {
            text: 'Temperature',
            style: {
                color: Highcharts.getOptions().colors[1]
            }
        }
    }, { // Secondary yAxis
        title: {
            text: 'Rainfall',
            style: {
                color: Highcharts.getOptions().colors[0]
            }
        },
        labels: {
            format: '{value} mm',
            style: {
                color: Highcharts.getOptions().colors[0]
            }
        },
        opposite: true
    }],
    tooltip: {
        shared: true
    },
    legend: {
        layout: 'vertical',
        align: 'left',
        x: 120,
        verticalAlign: 'top',
        y: 100,
        floating: true,
        backgroundColor:
            Highcharts.defaultOptions.legend.backgroundColor || // theme
            'rgba(255,255,255,0.25)'
    },
    series: [{
        name: 'Rainfall',
        type: 'column',
        yAxis: 1,
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4],
        tooltip: {
            valueSuffix: ' mm'
        }

    }, {
        name: 'Temperature',
        type: 'spline',
        data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6],
        tooltip: {
            valueSuffix: '°C'
        }
    }]
});