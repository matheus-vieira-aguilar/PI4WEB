(function($){

    $(".abreModal").click(function () {

        var id = $(this).val()
        console.log(id)
        $(".modal-footer #deleteModal").attr("value",id)

    })

    $(".delete").click(function (e) {
        e.preventDefault();
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
