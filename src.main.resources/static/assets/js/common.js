var G_URL_Rest = "/densify";

function showAlertMessage(data) {
	if (data.responseType == "SUCCESS") {
		$('#success-alert').show()
		setTimeout(function() {
			$('#success-alert').hide()
		}, 2000);
		$('#success-alert-message').html(data.message);
	} else if (data.responseType == "FAILURE") {
		$('#failure-alert').show()
		setTimeout(function() {
			$('#failure-alert').hide()
		}, 3000);
		$('#failure-alert-message').html(data.message);
	} else if (data.responseType == "INFO") {
		$('#info-alert').show()
		setTimeout(function() {
			$('#info-alert').hide()
		}, 2000);
		$('#info-alert-message').html(data.message);
	}
}

function showFailureAlertMessage(data) {
	$('#failure-alert').show();
	setTimeout(function() {
		$('#failure-alert').hide()
	}, 3000);
	if (data == null) {
		var defaultMsg = "Internal Server problem Happened,please try again after refresh, If problem exists contact support";
		$('#failure-alert-message').html(defaultMsg);
	} else {
		$('#failure-alert-message').html(data);
	}
}

function loadSettings() {
	var settings = {

	};

	doAjaxWithArgAndReturn("/settings", "post", settings, function(content) {
		$('#main-content').html(content);
	});
}

function saveSettings() {

	var settings = [
		{ "key": "NAME", "value": $('#name').val(), "tab": 1 },
		{ "key": "EMAIL", "value": $('#email').val(), "tab": 1 },
		{ "key": "MOBILE", "value": $('#mobile').val(), "tab": 1 },
		{ "key": "ADDRESS", "value": $('#address').val(), "tab": 1 },
		{ "key": "GSTIN", "value": $('#gstin').val(), "tab": 1 },
		{ "key": "IGST", "value": $('#igst').val(), "tab": 2 },
		{ "key": "CGST", "value": $('#cgst').val(), "tab": 2 },
		{ "key": "SGST", "value": $('#sgst').val(), "tab": 2 },
		{ "key": "MONTH", "value": $('#month').val(), "tab": 3 },
		{ "key": "AUTO_ASSIGN_EMPLOYEE", "value": $('#auto_assign_employee').val(), "tab": 4 },
		{ "key": "AUTO_SERVICE_PRICE", "value": $('#auto_service_price').val(), "tab": 4 }
	];

	doAjaxWithArgAndReturn("/settings", "put", settings, function(content) {
		showAlertMessage(content);
		loadSettings();
	});
}

function loadDashboard() {

	var dashboard = {
	};

	doAjaxWithArgAndReturn("/dashboard", "post", dashboard, function(content) {
		$('#main-content').html(content);
	});

}

function print(data) 
{
	//$('#print-example').html(data);
	var mywindow = window.open('ERP', 'ERP', '');
	mywindow.document.write(data);
	mywindow.document.close();
	mywindow.document.print();
	//mywindow.print();
	mywindow.close();
    return true;
}

function showCustViewAppService(id, page) {
	if(page==undefined){
		page="";
	}
	var appService = {
		"id": id,
		"page":page
	};
	doAjaxWithArgAndReturn("/a/"+$('#companyId').val()+"/cust/"+$('#mobile').val()+"/svc/view", "POST", appService, function(content) {
		$('#main-content').html(content);
	});
}

function showCustViewSale(id, page) {
	if(page==undefined){
		page="";
	}
	var sale = {
		"id": id,
		"page":page
	};
	doAjaxWithArgAndReturn("/a/"+$('#companyId').val()+"/cust/"+$('#mobile').val()+"/order/view", "POST", sale, function(content) {
		$('#main-content').html(content);
	});
}

function loadCustSvcView(){
	$('#main-tab').DataTable();
}

function saveCustReview(id) {
	var appService = {
		"id": id,
		"rating":$("#rateYo").rateYo("rating"),
		"review":$('#review').val(),
	};
	doAjaxWithArgAndReturn("/a/"+$('#companyId').val()+"/cust/"+$('#mobile').val()+"/svc/save-rating", "POST", appService, function(content) {
		$('#main-content').html(content);
		$('#success-alert').show()
		setTimeout(function() {
			$('#success-alert').hide()
		}, 2000);
		$('#success-alert-message').html("Review added successfully!");
	});
}
