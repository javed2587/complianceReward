<div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Dependent(s)</h4>
        </div>
        <div class="modal-body">
            <div class="table-responsive">
                <table id="dependentDataTable" class="table table-striped">
                    <thead>
                        <tr>
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
<jsp:include page="dataTable.jsp" />
<script type="text/javascript">
    $(document).ready(function () {
        listingDataTable("${id}", "${type}",'dependentDataTable');
        //$("#example").css({"width": "100%"});
        $('#dependentsModal').modal({
            show: 'true'
        });
    });
</script>