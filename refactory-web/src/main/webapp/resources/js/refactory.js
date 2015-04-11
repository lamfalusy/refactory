/* Hold tickets*/
var table;
/* Hold comments */
var commentList;

$(document).ready(
		function() {
			$('textarea').elastic();
			$('#ticket-editor-input-deadline').datepicker({
				dateFormat : 'yy.mm.dd'
			});
			$('#ticket-editor-select-priority').selectmenu();
			$('#ticket-editor-select-type').selectmenu();
			$('#ticket-editor-select-status').selectmenu();

			table = $('#ticket-table');

			/* Init comments */
			var options = {
				item : 'comment-item'
			};
			commentList = new List('ticket-editor-comment-container', options);
			/* Init comments */

			initTable();

			// TABLAZAT SORAIRA KATTINTVA A FORM FELTOLTESE
			table.on('click', 'tbody tr', function() {

				if (numberOfTickets() != 0) {
					if ($(this).hasClass('selected')) {
						clearInputs();
						$(this).removeClass('selected');
					} else {
						// Get the ticket from server with the given id
						table.$('tr.selected').removeClass('selected');
						$(this).addClass('selected');
						getTicket(getSelectedRowData().id);
						setButtonsDisabled(false);
					}
				}
			});

			// TICKET LETREHOZASA
			$('#create-ticket-button').click(
					function() {
						var ticket = new Object();
						ticket.project = $('#ticket-editor-input-project')
								.val();
						ticket.title = $('#ticket-editor-input-title').val();
						ticket.type = $('#ticket-editor-select-type')
								.selectmenu().val();
						ticket.status = $('#ticket-editor-select-status')
								.selectmenu().val();
						ticket.priority = $('#ticket-editor-select-priority')
								.selectmenu().val();
						ticket.reporter = $('#ticket-editor-input-reporter')
								.val();
						ticket.assignee = $('#ticket-editor-input-assignee')
								.val();
						ticket.deadline = $('#ticket-editor-input-deadline')
								.val();
						ticket.description = $(
								'#ticket-editor-textarea-description').val();
						if (checkFieldValue()) {
							createTicket(ticket);
						}
					});

			// TICKET TORLESE
			$('#delete-ticket-button').click(function() {
				deleteTicket(getSelectedRowData());
				clearInputs();
			});

			// TICKET MODOSITASA
			$('#update-ticket-button').click(
					function() {
						var ticket = new Object();
						ticket.id = $('#ticket-editor-id').text();
						ticket.project = $('#ticket-editor-input-project')
								.val();
						ticket.title = $('#ticket-editor-input-title').val();
						ticket.type = $('#ticket-editor-select-type')
								.selectmenu().val();
						ticket.status = $('#ticket-editor-select-status')
								.selectmenu().val();
						ticket.priority = $('#ticket-editor-select-priority')
								.selectmenu().val();
						ticket.reporter = $('#ticket-editor-input-reporter')
								.val();
						ticket.assignee = $('#ticket-editor-input-assignee')
								.val();
						ticket.deadline = $('#ticket-editor-input-deadline')
								.val();
						ticket.description = $(
								'#ticket-editor-textarea-description').val();
						if (checkFieldValue()) {
							updateTicket(ticket);
						}
					});

			// COMMENT HOZZÁADÁSA
			$('#add-comment-button').click(function() {
				var comment = new Object();
				comment.comment = $('#ticket-editor-textarea-comment').val();
				comment.user = "Horvath Adam";
				addComment(getSelectedRowData().id, comment);
			});
			
			// IMPORTALAS
			$('#sites-import-button').click(function() {
				var url = $('#sites-import-url').val();
				console.log(url);
				sitesImport(url);
			});
		});

function initTable() {
	table.dataTable({
		"bJQueryUI" : true,
		"ajax" : "ReadTicketServlet",
		"bAutoWidth": false,
		"columns" : [ {
			"data" : "id"
		}, {
			"data" : "title"
		}, {
			"data" : "type"
		}, {
			"data" : "created"
		} ]
	});
}

function addRow(ticket) {

	console.log(ticket.id);
	console.log(ticket.title);
	console.log(ticket.type);
	console.log(ticket.created);

	table.DataTable().row.add({
		"id" : ticket.id,
		"title" : ticket.title,
		"type" : ticket.type,
		"created" : ticket.created
	}).draw();
}

function getSelectedRowData() {
	return table.DataTable().row('.selected').data();
}

function getRowID() {
	return table.find('.selected').find("td:first").html();
}

function getTicket(id) {
	$.ajax({
		url : 'ReadTicketServlet',
		data : {
		id : id
		},
		dataType : 'json',
		success : function(response) {
		$('#ticket-editor-header-title').html(response.title);
		$('#ticket-editor-header-id').html(response.id);
		$('#ticket-editor-input-title').val(response.title);
		$('#ticket-editor-id').html(response.id);
		$('#ticket-editor-input-project').val(response.project);
		$('#ticket-editor-input-reporter').val(response.reporter);
		$('#ticket-editor-input-assignee').val(response.assignee);
		setValueOfSelect($('#ticket-editor-select-status'),
			response.status);
		setValueOfSelect($('#ticket-editor-select-type'),
			response.type);
		setValueOfSelect($('#ticket-editor-select-priority'),
			response.priority);
		$('#ticket-editor-created').html(response.created);
		$('#ticket-editor-input-deadline').val(response.deadline);
		$('#ticket-editor-textarea-description').val(
			response.description);

		if (response.comment != null) {
			commentList.add(response.comments);
		}
	}});
}

function createTicket(ticket) {

	console.log("creating tickt");
	console.log(ticket.title);
	$.ajax({
		url : 'CreateTicketServlet',
		data : {
			ticket : JSON.stringify(ticket),
		},
		dataType : 'json',
		success : function(response) {
			addRow(response);
			clearInputs();
			$('#ticket-table tr.selected').removeClass('selected');
			createMessageBox("Ticket[" + response.id + "] - " + response.title
					+ " was created!");
		}
	});
}

function deleteTicket(row) {
	$.ajax({
		url : 'DeleteTicketServlet',
		data : {
			id : row.id
		},
		success : function(response) {
			if (response != null) {
				table.DataTable().row('.selected').remove().draw(false);
				createMessageBox("Ticket[" + row.id + "] - " + row.title
						+ " was deleted!");
			}
		}
	});
}

function updateTicket(ticket) {
	$.ajax({
		url : 'UpdateTicketServlet',
		data : {
			ticket : JSON.stringify(ticket),
		},
		dataType : 'json',
		success : function(response) {
			clearInputs();
			table.DataTable().row('.selected').remove().draw(false);
			addRow(response);
			createMessageBox("Ticket[" + ticket.id + "] - " + ticket.title
					+ " was updated!");
		}
	});
}

function addComment(id, comment) {
	$.ajax({
		url : 'AddCommentServlet',
		data : {
			id : id,
			comment : JSON.stringify(comment),
		},
		dataType : 'json',
		success : function(response) {
			$('#ticket-editor-textarea-comment').val('');
			commentList.clear();
			commentList.add(response);
			createMessageBox("The following comment (" + comment.comment
					+ ") was added to Ticket[" + id + "]!")
		}
	});
}

function sitesImport(url) {
	console.log(url);
	$.ajax({
		url : 'SitesImportServlet',
		data : {
			url : url,
		},
		dataType : 'json',
		success : function(response) {
			createMessageBox("Success import!");
		}
	});
}

function numberOfTickets() {
	return table.fnGetData().length;
}

function setValueOfSelect(selectMenu, value) {
	selectMenu.children().each(function() {
		if (value === $(this).val()) {
			selectMenu.val($(this).val());
			selectMenu.selectmenu("refresh");
			return false;
		}
	});
}

function clearInputs() {
	$('#ticket-editor-input-title').val('');
	$('#ticket-editor-id').html('');
	$('#ticket-editor-input-project').val('');
	$('#ticket-editor-input-reporter').val('');
	$('#ticket-editor-input-assignee').val('');
	$('#ticket-editor-input-created').val('');
	$('#ticket-editor-input-deadline').val('');
	$('#ticket-editor-textarea-description').val('');
	$('#ticket-editor-select-priority').val("Trivial").selectmenu("refresh");
	$('#ticket-editor-select-type').val("Bug").selectmenu("refresh");
	$('#ticket-editor-select-status').val("None").selectmenu("refresh");
	$('#ticket-editor-header-title').html("Title");
	$('#ticket-editor-header-id').html('');
	console.log("gfdsaf");
	commentList.clear();
	setButtonsDisabled(true);
}

function createMessageBox(message) {
	$.growl({
		title : "Information",
		message : message
	});
}

function createErrorBox(message) {
	$.growl.error({
		message : message
	});
}

function setButtonsDisabled(option) {
	$('#update-ticket-button').attr("disabled", option);
	$('#delete-ticket-button').attr("disabled", option);
	$('#add-comment-button').attr("disabled", option);
}

function checkFieldValue() {

	var isValid = true;

	if ($.trim($('#ticket-editor-input-title').val()) == "") {
		createErrorBox("Title field cannot be empty!");
		isValid = false;
	}
	if ($.trim($('#ticket-editor-input-project').val()) == "") {
		createErrorBox("Project field cannot be empty!");
		isValid = false;
	}

	return isValid;
}

function parseDate(date) {
	var w = JSON.parse(date);
	var v;
	console.log(w);
	return;

}
