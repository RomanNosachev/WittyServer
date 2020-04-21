$(document).ready(function() {
	if (window.location.href.indexOf("request") > -1) {
		var fullRequestClassName = $('#requestType-select').val();
	
		displayRequestForm(fullRequestClassName);
	}
	
	if (window.location.href.indexOf("action") > -1) {
		var fullActionClassName = $('#actionType-select').val();
	
		displayActionForm(fullActionClassName);
	}
});

$('#requestType-select').on('change', function() {
	$('.request-div').hide();
	
	var fullRequestClassName = $(this).val();
	
	displayRequestForm(fullRequestClassName);
});

$('#actionType-select').on('change', function() {
	$('.action-div').hide();
	
	var fullActionClassName = $(this).val();
	
	displayActionForm(fullActionClassName);
});

function displayActionForm(fullActionClassName) {
	var actionClassName = /[A-Z]{1,}[a-zA-Z]{0,}/.exec(fullActionClassName);	
	var activeName = '#' + actionClassName + '-div';
		
	if (actionClassName != null) {		
		$(activeName).show();
		
		var actionForm = $(' .action-form', activeName);
		
		$('<input type="hidden" class="form-control' +		
			'actionClassName" name="actionClassName">')
			.attr('value', fullActionClassName)
			.appendTo(actionForm);
	}		
}

function displayRequestForm(fullRequestClassName) {
	var requestClassName = /[A-Z]{1,}[a-zA-Z]{0,}/.exec(fullRequestClassName);	
	var activeName = '#' + requestClassName + '-div';
		
	if (requestClassName != null) {		
		$(activeName).show();
		
		var requestForm = $(' .request-form', activeName);
		
		$('<input type="hidden" class="form-control' +		
			'requestClassName" name="requestClassName">')
			.attr('value', fullRequestClassName)
			.appendTo(requestForm);
	}		
}