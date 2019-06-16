(function($){
    var objetoParsed = JSON.parse(objeto)
    var data = objetoParsed.data

    $('.table').DataTable( {
        data: data,
        columns: [
            { data: "dataAvaliacao" },
            { data: "massa" },
            { data: 'massaDeGordura' },
            { data: "massaMagra" },
            { data: "imc" },
            { data: "pccg" }
        ]
    } );
})(jQuery)
