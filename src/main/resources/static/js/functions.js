(function ($) {
    var tr = null;
    var id = null;

    $(".abreModal").click(function () {
        id = $(this).val()
        tr = $(this).parents("tr")
        $(".modal-footer #deleteModal").attr("value", id)

    })

    $("#deleteModal").click(function (e) {

        var id = $(this).attr("value");

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/delete/' + id,
            success: function (data) {
                tr.remove()
            },
            error: function (e) {
                console.log("Erro ao excluir Aluno" + e.Message)
            }
        })
    })

})(jQuery)
