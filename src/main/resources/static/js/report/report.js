$(function () {
	$('#periodical').change(
		function() {
			var selectedPeriod = $('#periodical').val();
			selectedPeriod === 'Weekly' ? $('#weekly').show() : $('#weekly').hide();
			selectedPeriod === 'Monthly' 
				|| selectedPeriod === 'Quarterly' 
				|| selectedPeriod === 'Half yearly' 
				|| selectedPeriod === 'Annually' ? $('#yearly').show() : $('#yearly').hide();
			selectedPeriod === 'Ad-hoc' ? $('#ad-hoc').show() : $('#ad-hoc').hide();
		});

	$('#resultPanel').hide();
	
	$('#genReport').on('click', function () {
            var url = "/reports";
            var param = "";
            
            if($('#periodical').val() === 'Weekly') {
            	url = url + "/weekly";
            	param = 'weekDay=' + $('#weekDay').val();
            } else if($('#periodical').val() === 'Monthly') {
            	url = url + "/monthly";
            	param = 'year=' + $('#year').val();
            } else if($('#periodical').val() === 'Quarterly') {
            	url = url + "/quarterly";
            	param = 'year=' + $('#year').val();
            } else if($('#periodical').val() === 'Half yearly') {
            	url = url + "/halfyearly";
            	param = 'year=' + $('#year').val();
            } else if($('#periodical').val() === 'Annually') {
            	url = url + "/annually";
            	param = 'year=' + $('#year').val();
            } else if($('#periodical').val() === 'Ad-hoc') {
            	url = url + "/adhoc";
            	param = 'adhocFromDate=' + $('#adhocFromDate').val() + "&adhocToDate=" + $('#adhocToDate').val();
            } else {
            	return;
            }
            console.log($('#reportType').val());
            if($('#reportType').val() === '1') {
            	param = param + "&type=product";
            } else if($('#reportType').val() === '2') {
            	param = param + "&type=category";
            } else {
            	param = param + "&type=overall";
            }
            
            console.log(url);
            console.log(param);
            $('#reportView').html("");
            $.post(url, param)
                .done(function (data) {
                	$('#reportView').html(data);
//                	console.log(data);
                })
                .fail(function () {
                	$('#reportView').html("Failed to generate report");
                    console.log("FAILED - Failed to generate report !");
                    
                });
            $('#resultPanel').show();
            $('#selectPanel').hide();
        });
	
	$('#backToReport').on('click', function () {
		$('#selectPanel').show();
		$('#resultPanel').hide();
    });
	
	$('#save_as').on('click', function () {
		var url = "/reports";
        var param = "";
        
        if($('#periodical').val() === 'Weekly') {
        	url = url + "/weekly";
        	param = 'weekDay=' + $('#weekDay').val();
        } else if($('#periodical').val() === 'Monthly') {
        	url = url + "/monthly";
        	param = 'year=' + $('#year').val();
        } else if($('#periodical').val() === 'Quarterly') {
        	url = url + "/quarterly";
        	param = 'year=' + $('#year').val();
        } else if($('#periodical').val() === 'Half yearly') {
        	url = url + "/halfyearly";
        	param = 'year=' + $('#year').val();
        } else if($('#periodical').val() === 'Annually') {
        	url = url + "/annually";
        	param = 'year=' + $('#year').val();
        } else if($('#periodical').val() === 'Ad-hoc') {
        	url = url + "/adhoc";
        	param = 'adhocFromDate=' + $('#adhocFromDate').val() + "&adhocToDate=" + $('#adhocToDate').val();
        } else {
        	return;
        }
        
        console.log($('#reportType').val());
        if($('#reportType').val() === '1') {
        	param = param + "&type=product";
        } else if($('#reportType').val() === '2') {
        	param = param + "&type=category";
        } else {
        	param = param + "&type=overall";
        }
        
        url = url + '/' + $('input[name=exportType]:checked').val();
        console.log(url);
        console.log(param);
        $.post(url, param)
            .done(function (data) {
            	$('#reportView').html(data);
//            	console.log(data);
            })
            .fail(function () {
            	$('#reportView').html("Failed to generate report");
                console.log("FAILED - Failed to generate report !");
                
            });
        $('#resultPanel').show();
        $('#selectPanel').hide();
	});
	
	$('#testPayment').on('click', function () {
		var url = "/payment/1";
        $.post(url, null)
            .done(function (data) {
            	$('#reportView').html(data);
//            	console.log(data);
            })
            .fail(function () {
            	$('#reportView').html("Failed to generate report");
                console.log("FAILED - Failed to generate report !");
                
            });
        $('#resultPanel').show();
        $('#selectPanel').hide();
	});
})