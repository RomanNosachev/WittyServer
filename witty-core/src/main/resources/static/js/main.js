$(document).ready(function() {
	var fullRequestClassName = $('#requestType-select').val();
	
	displayRequestForm(fullRequestClassName);
	
	var fullActionClassName = $('#actionType-select').val();
	
	displayActionForm(fullActionClassName);
});

$('#requestType-select').on('change', function() {	
	$('.request-div').hide();
	
	var fullRequestClassName = $(this).val();
	
	displayRequestForm(fullRequestClassName);
});

/*
$('editScenario-button').click(function() {
	window.location.href = window.location.hostname + 'editScenario';
});

$('deleteScenario-button').click(function() {
});
*/

$('.table td').click(function() {
	$(this).parents('tr').addClass('selected').siblings().removeClass('selected');
});

$('#actionType-select').on('change', function() {
	$('.action-div').hide();
	
	var fullActionClassName = $(this).val();
	
	displayActionForm(fullActionClassName);
});

function displayRequestForm(fullRequestClassName) {	
	var requestClassName = /[A-Z]{1,}[a-zA-Z]{0,}/.exec(fullRequestClassName);	
	var activeName = '#' + requestClassName + '-div';
				
	if (requestClassName != null) {		
		$(activeName).show();
	}		
}

function displayActionForm(fullActionClassName) {
	var actionClassName = /[A-Z]{1,}[a-zA-Z]{0,}/.exec(fullActionClassName);	
	var activeName = '#' + actionClassName + '-div';
				
	if (actionClassName != null) {		
		$(activeName).show();
	}		
}