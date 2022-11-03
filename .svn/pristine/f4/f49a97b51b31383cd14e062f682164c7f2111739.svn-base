/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.patientActivity = {
    getPatientBloodPressureDetails: function () {
        var patientId = 1;
        if (patientId !== '') {
            $("#patientActivityDetailHeading").text("Blood Pressure Details");
            $("#detailSubHDate").text($("#date").html());
            $("#detailSubHName").text($("#patientId").html());
            $("#detailSubHAge").text($("#patientAge").html());
            $("#patientActivityDetailsTableHeadCol1").html("Blood Pressure");
            $("#patientActivityDetailsTableHeadCol2").html("Time");
            $("#patientActivityDetailsTableBody").html("<tr><td style='width=20%'>12/12</td><td>10:00 pm</td></tr>");
            $("#patientActivityDetails").modal('show');
//            $.ajax({
//                url: window.pharmacyNotification.appUrl + "/ConsumerPortal/patientDetailByMobileNumber/" + mobileNumber,
//                type: "POST",
//                contentType: 'application/json',
//                success: function (data) {
//                    data = JSON.parse(data);
//                    
//                },
//                error: function (e) {
//                    
//                }
//            });
        }
        
        }
    }

