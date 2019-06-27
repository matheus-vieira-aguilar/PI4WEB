

mediaIMC = dados.mediaImc
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
/*
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

*/
Highcharts.chart('lineChart', {

    chart: {
        type: 'column'
    },

    title: {
        text: ''
    },

    xAxis: {
        categories: [
            'POLLOCK3D',
            'POLLOCK7D',
            'GUEDES',
            'PETROSKY',
            'THROLAND7D',
            'THROLAND3D'
        ]
    },

    yAxis: {
        allowDecimals: false,
        min: 0,
        title: {
            text: 'porcentagem (%)'
        }
    },

    tooltip: {
        formatter: function () {
            return '<b>' + this.x + '</b><br/>' +
                this.series.name + ': ' + this.y + '%' +'<br/>' 
                
        }
    },

    plotOptions: {
        column: {
            stacking: 'normal'
        }
    },

    series: [{
        name: 'Masculino',
        data: [mediaMasculina[0], mediaMasculina[1], mediaMasculina[2], mediaMasculina[3], mediaMasculina[4], mediaMasculina[5]],
        stack: 'male'
    }, {
        name: 'Feminino',
        data: [mediaFeminina[0], mediaFeminina[1], mediaFeminina[2], mediaFeminina[3], mediaFeminina[4], mediaFeminina[5]],
        stack: 'female'
    }]
});



Highcharts.chart('lineIMC', {
    chart: {
        zoomType: 'xy'
    },
    title: {
        text: ''
    },
    subtitle: {
        text: ''
    },
    xAxis: [{
        categories: ['15 - 20', '20 - 30', '30 - 40', '40 - 50',  '50 - 60', '60 - 70', '70 - 80'],
        crosshair: true
    }],
    yAxis: [{ // Primary yAxis
        labels: {
            format: '{value}',
            style: {
                color: Highcharts.getOptions().colors[1]
            }
        },
        title: {
            text: 'Media',
            style: {
                color: Highcharts.getOptions().colors[1]
            }
        }
    }, { // Secondary yAxis
        title: {
            text: 'Quantidade',
            style: {
                color: Highcharts.getOptions().colors[0]
            }
        },
        labels: {
            format: '{value}',
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
        name: 'Quantidade',
        type: 'column',
        yAxis: 1,
        data: [mediaIMC[0].qtd, mediaIMC[1].qtd, mediaIMC[2].qtd,mediaIMC[3].qtd,mediaIMC[4].qtd,mediaIMC[5].qtd, mediaIMC[6].qtd]

    }, {
        name: 'Media de IMC',
        type: 'spline',
        data: [mediaIMC[0].media, mediaIMC[1].media, mediaIMC[2].media,mediaIMC[3].media,mediaIMC[4].media,mediaIMC[5].media, mediaIMC[6].media]
    }]
});