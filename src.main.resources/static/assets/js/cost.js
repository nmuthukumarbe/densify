function searchAnalysis(){
	var cost = {
		"team": {"id": $('#team-id').val() },
	};
	$('#analysis').html("");
	doAjaxWithArgAndReturn("/cost/analysis", "POST", cost, function(content) {
		$('#analysis').html(content);
	});
}

function searchCost() {
	var cost = {
		"team": {"id": $('#team-id').val() },
	};
	$('#cost').html("");
	doAjaxWithArgAndReturn("/cost", "POST", cost, function(content) {
		$('#cost').html(content);
		$('#cost-table').DataTable();
	});

}

function saveCost(id) {

	var cost = {
		"id": id,
		"team": {"id": $('#team-id').val() },
		"mls": $('#mls').val(),
		"cassandra": $('#cassandra').val(),
		"wcnp": $('#wcnp').val(),
		"ingestion": $('#ingestion').val(),
		"sfnsf": $('#sfnsf').val(),
		"year": $('#year').val(),
		"month": $('#month').val()
	};
	
	doAjaxWithArgAndReturn("/cost", "PUT", cost, function(content) {
		showAlertMessage(content);
		if (content.responseType == "SUCCESS") {
			$('#cost').html("");
			alert(content.message);
			searchCost();
		} else {
			showAddCost(id);
		}
	});

}

function showAddCost(id) {
	var cost = {
		"id": id
	};
	$('#cost').html("");
	doAjaxWithArgAndReturn("/cost/add", "POST", cost, function(content) {
		$('#cost').html(content);
	});

}

function showViewCost(id) {
	var cost = {
		"id": id
	};
	doAjaxWithArgAndReturn("/cost/view", "POST", cost, function(content) {
		$('#cost').html(content);
	});

}
