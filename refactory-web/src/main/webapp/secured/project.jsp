<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@include file="../jspf/header.jspf"%>
<%@include file="../jspf/navbar.jspf"%>

<script>
	$('#project-site').addClass('active');
</script>
<script src="../resources/js/project.js"></script>

<div class="page-content">
	<div class="project-table-container">
		<table id="project-table" class="project-table">
			<thead>
				<tr>
					<th>Name</th>
					<th>Created</th>
				</tr>
			</thead>
			<tr>
				<td>ad</td>
				<td>2012.03.02.</td>
			</tr>
		</table>
	</div>

	<div class="form-editor project-form">
		<div class="form-editor-header">
			<div class="form-editor-header-title-container">
				<span id="form-editor-header-title" class="title">Project</span>
				<div class="form-editor-subtitle">
					<span>Id:</span> <span id="form-editor-header-id"></span>
				</div>
			</div>
			<div class="form-editor-header-button-container">
				<button id="create-ticket-button">Create</button>
				<button id="update-ticket-button" disabled="true">Update</button>
				<button id="delete-ticket-button" disabled="true">Delete</button>
			</div>
			<div class="clear"></div>
		</div>
		<hr class="faded" />
		<div class="form-editor-content">
			<div class="form-editor-content-descriptor-narrow ticket-editor-content-descriptor content-panel">
				<fieldset class="ticket-editor-details-fieldset fieldset-input-fixed-height">
					<legend>Details</legend>
					<div>
						<span class="attribute-name">Name:</span> <input id="ticket-editor-input-title" type="text">
					</div>
					<div>
						<span class="attribute-name">ID:</span> <span id="ticket-editor-id" class="ticket-editor-value-field"></span>
					</div>
					<div>
						<span class="attribute-name">Created:</span> <input id="ticket-editor-input-project" type="text">
					</div>
				</fieldset>
				<fieldset class="ticket-editor-description-fieldset">
					<legend>Description</legend>
					<div>
						<textarea rows="1" id="ticket-editor-textarea-description"></textarea>
					</div>
				</fieldset>
			</div>
			<div class="form-editor-content-narrow-second">
				<fieldset class="project-tickets-fieldset">
					<legend>Tickets</legend>
					<select name="project-tickets" id="project-tickets-list">
						<option>Need Info</option>
						<option>In Progress</option>
						<option>Ready</option>
						<option>Fixed</option>
					</select>
				</fieldset>
			</div>
			<div class="clear"></div>
			<div class="form-editor-content-descriptor content-panel">
				<fieldset class="project-managers-fieldset">
					<legend>Managers</legend>
					<select name="project-managers" id="project-managers-picklist" multiple="multiple">
						<option>Need Info</option>
						<option>In Progress</option>
						<option>Ready</option>
						<option>Fixed</option>
					</select>
				</fieldset>
				<fieldset class="project-workers-fieldset">
					<legend>Workers</legend>
					<select name="project-workers" id="project-workers-picklist" multiple="multiple">
						<option>Need Info</option>
						<option>In Progress</option>
						<option>Ready</option>
						<option>Fixed</option>
					</select>
				</fieldset>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="clear"></div>


</div>

<jsp:include page="../jspf/footer.jspf"></jsp:include>