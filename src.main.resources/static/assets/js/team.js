function searchTeam() {

	$('#main').html("");
	debugger;
	
	var team = {
		"id":  $('#team-id').val() ,
	};
	
	doAjaxWithArgAndReturn("/team", "POST", team, function(content) {
		$('#main').html(content);
		$('#team-table').DataTable();
	});
}

function saveTeam(id) {

	var team = {
		"id": id,
		"name": $('#team-name').val(),
		"onboardingPageUrl": $('#onboard-page').val(),
		"goldenSignal": $('#golden-signal').prop('checked'),
		"availability": $('#availability').prop('checked'),
		"goldenSignalUrl": $('#golden-signal-page').val(),
		"availabilityUrl": $('#availability-page').val(),
		"ut": $('#ut').val(),
		"it": $('#it').val(),
		"more": $('#more-info').val()
	};
	if ($('#team-name').val().trim() == "") {
		alert("Team Name is Required");
		return;
	}

	doAjaxWithArgAndReturn("/team", "PUT", team, function(content) {
		showAlertMessage(content);
		if (content.responseType == "SUCCESS") {
			$('#main').html("");
			alert(content.message);
			searchTeam();
		} else {
			showAddTeam(id);
		}
	});

}

function showAddTeam(id) {
	var team = {
		"id": id
	};
	$('#main').html("");
	doAjaxWithArgAndReturn("/team/add", "POST", team, function(content) {
		$('#main').html(content);
	});

}

function showViewTeam(id) {
	var team = {
		"id": id
	};
	doAjaxWithArgAndReturn("/team/view", "POST", team, function(content) {
		$('#main').html(content);
	});

}
