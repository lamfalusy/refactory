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
			
			initUsers();

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
						clearInputs();
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
						var assignee = new Object();
						assignee.fullName = $('#ticket-editor-input-assignee').val();
						ticket.assignee = assignee;
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

function initUsers() {
	
	var users = [];
	
	$.ajax({
		url : 'GetUsersServlet',
		dataType : 'json',
		success : function(response) {
			
			response.data.forEach(function(element) {
				users.push(element.fullName);
				console.log(element.fullName);
			});
			console.log(users);
			$('#ticket-editor-input-assignee').autocomplete({
			      source: users
			});
			$('#ticket-editor-input-assignee').attr('autocomplete', 'on');
		}
	});
}

function addRow(ticket) {

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
		$('#ticket-editor-input-project').val(response.projectName);
		$('#ticket-editor-input-reporter').html(response.reporter.fullName);
		$('#ticket-editor-input-assignee').val(response.assignee.fullName);
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

		response.comments.forEach(function(element) {
			var comment = new Object();
			comment.user = element.user.fullName;
			comment.added = element.added;
			comment.comment = element.comment;
			commentList.add(comment);
		});
	}});
}

function createTicket(ticket) {

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
			createMessageBox("info", "Ticket[" + response.id + "] - " + response.title
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
				createMessageBox("info", "Ticket[" + row.id + "] - " + row.title
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
			createMessageBox("info", "Ticket[" + ticket.id + "] - " + ticket.title
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
			createMessageBox("info", "The following comment (" + comment.comment
					+ ") was added to Ticket[" + id + "]!")
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
	$('#ticket-editor-input-reporter').html('');
	$('#ticket-editor-input-assignee').val('');
	$('#ticket-editor-input-created').html('');
	$('#ticket-editor-input-deadline').val('');
	$('#ticket-editor-textarea-description').val('');
	$('#ticket-editor-select-priority').val("Trivial").selectmenu("refresh");
	$('#ticket-editor-select-type').val("Bug").selectmenu("refresh");
	$('#ticket-editor-select-status').val("None").selectmenu("refresh");
	$('#ticket-editor-header-title').html("Title");
	$('#ticket-editor-header-id').html('');
	$('#ticket-editor-textarea-comment').val('');
	commentList.clear();
	setButtonsDisabled(true);
}

function createMessageBox(level, message) {
	if (level === "info") {
		$.growl({title : "Information",
			message : message
		});
	} else if (level === "error") {
		$.growl.error({
			message : message
		});
	}
}

function setButtonsDisabled(option) {
	$('#update-ticket-button').attr("disabled", option);
	$('#delete-ticket-button').attr("disabled", option);
	$('#add-comment-button').attr("disabled", option);
}

function checkFieldValue() {

	var isValid = true;

	if ($.trim($('#ticket-editor-input-title').val()) == "") {
		createMessageBox("error", "Title field cannot be empty!");
		isValid = false;
	}
	if ($.trim($('#ticket-editor-input-project').val()) == "") {
		createMessageBox("error", "Project field cannot be empty!");
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
