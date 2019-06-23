


mediaFeminina = []
mediaMasculina = []

debugger
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