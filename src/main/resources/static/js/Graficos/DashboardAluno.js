(function  () {
    debugger;
    var objetoParsed = objeto;
    objetoParsed.data = objetoParsed.data.reverse();
        var categories = []
        var grafico = [
            { "POLLOCK3D": 
                {
                    name: "POLLOCK3D",
                    data: []
                } 
            },
            {  "POLLOCK7D":
                {
                name: "POLLOCK7D",
                data: []
                }
            },
            { "GUEDES":
            {
                name: "GUEDES",
                data: []
            }},
            {"PETROSKY":{
                name: "PETROSKY",
                data: []
            }},
            {"THORLAND7D": {
                name: "THORLAND7D",
                data: []
            }},
            {"THORLAND3D": {
                name: "THORLAND3D",
                data: []
            }}
        ]

        var data = objetoParsed.data
        var limit = 0

        if (data.length > 12) {
            limit = data.length - 12
        }

        for (let index = limit; index != data.length ; index++) {
            
            var day = objeto.data[index].dataAvaliacao.dayOfMonth;
            var month = objeto.data[index].dataAvaliacao.monthValue - 1;
            var year = objeto.data[index].dataAvaliacao.year;

            var date = new Date(Date.UTC(year, month, day)).toLocaleDateString();

            categories.push(date)

            var porcentagemDeGordura = data[index].porcentagemDeGordura

            for (let i = 0; i < porcentagemDeGordura.length; i++) {
                var autor = porcentagemDeGordura[i].autor
                
                if(autor == null || undefined){
                    continue;
                }

                switch (autor.$name) {
                    case "POLLOCK3D":
                        grafico[0]["POLLOCK3D"].data.push(parseFloat(porcentagemDeGordura[i].porcentagemDeGordura.toFixed(2)))
                        break;
                    case 'POLLOCK7D':
                        grafico[1]["POLLOCK7D"].data.push(parseFloat(porcentagemDeGordura[i].porcentagemDeGordura.toFixed(2)))
                        break;
                    case 'GUEDES':
                        grafico[2]["GUEDES"].data.push(parseFloat(porcentagemDeGordura[i].porcentagemDeGordura.toFixed(2)))
                        break;
                    case 'PETROSKY':
                        grafico[3]["PETROSKY"].data.push(parseFloat(porcentagemDeGordura[i].porcentagemDeGordura.toFixed(2)))
                        break;
                    case 'THORLAND7D':
                        grafico[4]["THORLAND7D"].data.push(parseFloat(porcentagemDeGordura[i].porcentagemDeGordura.toFixed(2)))
                        break;
                    case 'THORLAND3D':
                        grafico[5]["THORLAND3D"].data.push(parseFloat(porcentagemDeGordura[i].porcentagemDeGordura.toFixed(2)))
                        break;                                    
                }
            }            
        }

        Highcharts.chart('lineChart', {
            chart: {
                type: 'line'
            },
            title: {
                text: 'Porcentagem de Gordura'
            },
            xAxis: {
                categories: categories
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
                name: grafico[0]["POLLOCK3D"]["name"],
                data: grafico[0]["POLLOCK3D"]["data"]
            },
            {
                name: grafico[1]["POLLOCK7D"]["name"],
                data: grafico[1]["POLLOCK7D"]["data"]
            },
            {
                name: grafico[2]["GUEDES"]["name"],
                data: grafico[2]["GUEDES"]["data"]
            },
            {
                name: grafico[3]["PETROSKY"]["name"],
                data: grafico[3]["PETROSKY"]["data"]
            },
            {
                name: grafico[4]["THORLAND7D"]["name"],
                data: grafico[4]["THORLAND7D"]["data"]
            },
            {
                name: grafico[5]["THORLAND3D"]["name"],
                data: grafico[5]["THORLAND3D"]["data"]
            }
            
        ]
    });
    
    var ultimaAvaliacao = data[data.length -1]

    // Build the chart
    Highcharts.chart('pizzaChart', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Porcentagem de Massas'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [{
            name: 'Massas',
            colorByPoint: true,
            data: [{
                name: 'Massa de Gordura',
                y: ultimaAvaliacao.massaDeGordura,
                sliced: true,
                selected: true
            }, {
                name: 'Massa Magra',
                y: ultimaAvaliacao.massaMagra
            }]
        }]
    });

    // Grafico 3:
    Highcharts.chart('barChart', {
        chart: {
            type: 'column'
        },
        title: {
            text: 'Massas da Última Avaliação'
        },
        xAxis: {
            type: 'category'
        },
        yAxis: {
            title: {
                text: 'Total percent market share'
            }
    
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true,
                    format: '{point.y:.2f}'
                }
            }
        },
    
        tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}KG</b> ao total<br/>'
        },
    
        series: [
            {
                name: "Valores",
                colorByPoint: true,
                data: [
                    {
                        name: "Peso Atual",
                        y: ultimaAvaliacao.massa,                      
                    },
                    {
                        name: "Massa de Gordura",
                        y: ultimaAvaliacao.massaDeGordura,                        
                    },
                    {
                        name: "Massa Magra",
                        y: ultimaAvaliacao.massaMagra,                        
                    },
                    {
                        name: "Peso Ideal",
                        y: ultimaAvaliacao.pesoIdeal,                        
                    },
                    {
                        name: "Peso em Excesso",
                        y: ultimaAvaliacao.pesoEmExcesso,
                    }
                ]
            }
        ]
    });
    
})()

