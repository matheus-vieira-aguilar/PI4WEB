(function ($) {
    var tr = null;
    var id = null;

    $(".abreModal").click(function () {
        id = $(this).val()
        tr = $(this).parents("tr")
        $(".modal-footer #deleteModal").val(id)
        $(".modal-footer #AtivarModal").val(id)

    })

    $("#deleteModal").click(async function (e) {

        var id = $(this).attr("value");

        await $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/delete/' + id,
            success: function (data) {
                location.reload();
            },
            error: function (e) {
                console.log("Erro ao excluir Aluno" + e.Message)
            }
        })
    })

    $("#AtivarModal").click(async function (e) {
        var id = $(this).attr("value");

        await $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/ativarAluno/' + id,
            success: function (data) {
                location.reload();
            },
            error: function (e) {
                console.log("Erro ao ativar Aluno" + e.Message)
            }
        })
    })

})(jQuery)
