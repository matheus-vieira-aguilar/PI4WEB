(function($){

    
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
