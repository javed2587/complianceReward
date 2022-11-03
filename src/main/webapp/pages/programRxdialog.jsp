<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">ALL PROGRAM RX's (${fn:length(totalRxProgram)})</h4>
        </div>
        <div class="modal-body">
            <div class="table-responsive">
                <table id="programRxDataTable" class="table table-striped">
                    <thead>
                        <tr>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>
<jsp:include page="../pages/portal/dataTable.jsp" />
<script type="text/javascript">
    $(document).ready(function () {
        listingDataTable("${id}", "${type}", 'programRxDataTable');
        //$("#example").css({"width": "100%"});
        $('#programRxModal').modal({
            show: 'true'
        });
    });
</script>