$(function () {
	$('#periodical').change(
		function() {
			var selectedPeriod = $('#periodical').val();
			selectedPeriod === 'Weekly' ? $('#weekly').show() : $('#weekly').hide();
			selectedPeriod === 'Monthly' ? $('#monthly').show() : $('#monthly').hide();
			selectedPeriod === 'Quarterly' ? $('#quarterly').show() : $('#quarterly').hide();
			selectedPeriod === 'Half yearly' ? $('#half-yearly').show() : $('#half-yearly').hide();
			selectedPeriod === 'Annually' ? $('#annually').show() : $('#annually').hide();
			selectedPeriod === 'Ad-hoc' ? $('#ad-hoc').show() : $('#ad-hoc').hide();
		});

//	$('#genReport').post(
//		"/reports",data,function(data,status,xhr),dataType);
	
	$('#genReport').on('click', function () {
            var url = "/reports/" + $('#periodical').val();
            var param = 'fromDate=' + $('#fromDate').val() + "&toDate=" + $('#toDate').val();
            
            console.log(url);
            $.post(url, param)
                .done(function (data) {
                	console.log(data);
                })
                .fail(function () {
                    console.log("FAILED - Failed to generate report !");
                });
        });
})