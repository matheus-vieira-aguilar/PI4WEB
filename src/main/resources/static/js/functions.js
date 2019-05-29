(function($){

        $(".delete").click(function () {

            var id = $(this).attr("value");
            var element = $(this)
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/delete/' + id,
                success: function (data) {                    
                    $(element).parents('tr').remove()
                },
                error: function (e) {
                    alert("Erro ao excluir Aluno")
                }
            })
        })


    $("#submit_aluno").click(function(e) {

        var form = $(".form-salva-aluno")
        /*
        e.preventDefault();
            
            $.ajax({
                url: "/salva_aluno",
                type: "POST",
                contentType: "application/json",
                data: form.serializeArray(),
                success: function(result) {
                    alert(result)
                }
            })
            
            */
        console.log(JSON.stringify(form.val()))
        })
})(jQuery);
