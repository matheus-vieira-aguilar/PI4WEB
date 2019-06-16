(function($){

    $(".abreModal").click(function () {

        var id = $(this).val()
        $(".modal-footer #deleteModal").attr("value",id)

    })

    $("#deleteModal").click(function (e) {
        
        var id = $(this).attr("value");
        var element = $(this)
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/delete/' + id,
            success: function (data) {                    
                $(element).parents('tr').remove()
            },
            error: function (e) {
                console.log("Erro ao excluir Aluno" + e.Message)
            }
        })
    })

})(jQuery)
