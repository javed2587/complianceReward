<script type="text/javascript">
    function listingDataTable(id, type,tableId) {
//        $("#example").dataTable().fnDestroy();
        $('#'+tableId).dataTable({
            "dom": 'T<"clear">lfrtip',
            "bProcesing": true,
            "bServerSide": true,
            "bAutoWidth": false,
            "autoWidth": false,
            "iDisplayLength": 10,
            "responsive": true,
            "bSortable": false,
            "bInfo": false,
            "bFilter": false,
            "bLengthChange": false,
            "bDestroy" : true,
            "sAjaxSource": "${pageContext.request.contextPath}/" + type + "/" + id,
            "aoColumns": [
    ${col}
            ],
            "fnServerData": function (sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "sPaginationType": "full_numbers",
            "oLanguage": {
                "sLengthMenu": "_MENU_ ",
                "sEmptyTable": "No records found."
            },
            "aaSorting": [[1, "desc"]]
        });
    }
</script>