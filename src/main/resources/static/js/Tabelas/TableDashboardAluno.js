(function ($) {

    var objetoParsed = objeto;
    var data = objetoParsed.reverse();

    $('.table').DataTable({
        data: data,
        language: {
            emptyTable: "Nenhum registro encontrado",
            sInfo: "",
            sInfoEmpty: "Mostrando 0 até 0 de 0 registros",
            sProcessing: "Processando...",
            sLengthMenu: "_MENU_",
            sSearch: "Pesquisar",
            oPaginate: {
                sNext: "Próximo",
                sPrevious: "Anterior",
                sFirst: "Primeiro",
                sLast: "Último"
            }
        },
        columns: [
            {
                data: "avaliacao",
                class: "avaliacaoTable"
            },
            {
                data: "dataAvaliacao",
                render: function (data, type, row, meta) {
                    var day = data.dayOfMonth + 1;
                    var month = data.monthValue - 1;
                    var year = data.year;

                    var date = new Date(Date.UTC(year, month, day)).toLocaleDateString();
                    return date;
                }
            },
            {
                data: "estatura",
                render: function (data) {
                    var double = parseFloat(data.toFixed(2))
                    return double
                }
            },
            {
                data: "massa",
                render: function (data) {
                    var double = parseFloat(data.toFixed(2))
                    return double
                }
            },
            {
                data: 'massaDeGordura',
                render: function (data) {
                    var double = parseFloat(data.toFixed(2))
                    return double
                }
            },
            {
                data: "massaMagra",
                render: function (data) {
                    var double = parseFloat(data.toFixed(2))
                    return double
                }
            },
            {
                data: "imc",
                render: function (data) {
                    var double = parseFloat(data.toFixed(2))
                    return double
                }
            },
            {
                data: "pccg",
                render: function (data) {
                    var double = parseFloat(data.toFixed(3))
                    return double
                }
            },
            {
                data: "iD",
                render: function (data, type, row, meta) {
                    var visualizar = '<a class="btn btn-outline-primary fas fas fa-eye" href="/avaliacao/' + row.idAluno + '?avaliacao=' + row.avaliacao + '" role="button"></a>';
                    var editar = ' <a class="btn btn-outline-success fas fas fa-edit" href="/editarAvaliacao/' + row.idAluno + '/' + row.iD + '" role="button"></a>';

                    var acoes = visualizar;
                    if (row.avaliacao === 1) {
                        acoes = acoes + editar;
                    }

                    return acoes;
                }
            }
        ]
    });
})(jQuery)
