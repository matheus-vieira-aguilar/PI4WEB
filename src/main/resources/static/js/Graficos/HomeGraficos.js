// Primeiro Gráfico. De pizza:
var ctx = document.getElementById("chart1");
ctx.height = 180;
var myChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
        labels: ["Índice de massa", "Índice de gordura"],
        datasets: [{
                label: 'IMC',
                data: [12, 18],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(255, 159, 64, 1)'
                ]
            }]
    },
    options: {
        responsive: true,
        scales: {
            yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
        }
    }
});

// Segundo Gráfico. De Linha:
var ctx = document.getElementById("chart2");
var myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ["01/2019", "02/2019", "03/2019", "04/2019", "05/2019", "06/2019"],
        datasets: [{
                label: 'IMC',
                data: [35, 32, 36, 30, 22, 26],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
    },
    options: {
        responsive: true,
        scales: {
            yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
        }
    }
});

// Terceiro Gráfico. De Barras:
var ctx = document.getElementById("chart3");
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ["Rosca Inversa", "Remada Unilateral", "Crucifixo", "Puxada na Frente", "Puxada Alta", "Pulley", "Rosca Inversa", "Remada Unilateral", "Crucifixo", "Puxada na Frente", "Puxada Alta", "Pulley"],
        datasets: [{
                label: 'Frequência exercicios',
                data: [7, 4, 3, 5, 2, 3, 7, 4, 3, 5, 2, 3],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)',
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
    },
    options: {
        responsive: true,
        scales: {
            yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
        }
    }
});