(function(){
    $(document).ready( function () {
        $('#table_id').DataTable();
    } );

    $(".avaliados").text(dados.porcentagemDeAvaliados + '%')
    $(".avaliados").css("width", dados.porcentagemDeAvaliados + '%')

    var totalGenero = dados.qtdMasculino + dados.qtdFeminino;

    var homensPorcentagem = parseFloat((dados.qtdMasculino / totalGenero) * 100).toFixed(2)
    $(".homens").text(homensPorcentagem + '%')
    $(".homens").css("width", homensPorcentagem + '%')

    var mulheresPorcentagem = parseFloat((dados.qtdFeminino / totalGenero) * 100).toFixed(2)
    $(".mulheres").text(mulheresPorcentagem + '%')
    $(".mulheres").css("width", mulheresPorcentagem + '%')

    var totalStatus = dados.qtdAtivados + dados.qtdInativados

    var ativadosPorcentagem = parseFloat((dados.qtdAtivados / totalStatus) * 100).toFixed(2)
    $(".ativados").text(ativadosPorcentagem + '%')
    $(".ativados").css("width", ativadosPorcentagem + '%')

    var inativadosPorcentagem = parseFloat((dados.qtdInativados / totalStatus) * 100).toFixed(2)
    $(".inativados").text(inativadosPorcentagem + '%')
    $(".inativados").css("width", inativadosPorcentagem + '%')

    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').trigger('focus')
    })


})();


