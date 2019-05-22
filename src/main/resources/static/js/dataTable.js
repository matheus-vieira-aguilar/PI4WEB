$(document).ready( function () {
    $('a[data-toggle="tab"]').on( 'shown.bs.tab', function (e) {
        $.fn.dataTable.tables( {visible: true, api: true} ).columns.adjust();
    } );

    $('.table').DataTable({
        scrollY:        200,
        scrollCollapse: true,
    });

});
