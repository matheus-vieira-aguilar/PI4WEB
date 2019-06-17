(function($){
    debugger;
    var objetoParsed = objeto;
    var data = objetoParsed.data.reverse();

    $('.table').DataTable( {
        data: data,
        columns: [
            { data: "dataAvaliacao",
                render: function ( data, type, row, meta ) {
                    var day = data.dayOfMonth;
                    var month = data.monthValue - 1;
                    var year = data.year;
        
                    var date = new Date(Date.UTC(year, month, day)).toLocaleDateString();
                    return date;
                }    
            },
            { data: "massa" },
            { data: 'massaDeGordura' },
            { data: "massaMagra" },
            { data: "imc" },
            { data: "pccg"},
            {
                data: "iD", 
                render: function ( data, type, row, meta ) { 
                    debugger;
                    var acoes = '<a class="btn btn-outline-primary fas fas fa-eye" href="/avaliacao/'+ row.idAluno +'?avaliacao=' + row.avaliacao +'" role="button"></a>';
                    if(row.avaliacao == 1){
                        acoes = acoes + ' <a class="btn btn-outline-secondary fas fas fa-edit" href="/avaliacao/'+ row.idAluno +'" role="button"></a>'
                    }            

                    return acoes;
                } 
            }
        ]
    } );
})(jQuery)
