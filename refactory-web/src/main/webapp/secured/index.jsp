<%@include file="../jspf/header.jspf"%>
<%@include file="../jspf/navbar.jspf"%>

<script>
	$('#ticket-site').addClass('active');
</script>

<div class="page-content">
	<div class="ticket-table-container">
		<table id="ticket-table" class="ticket-table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Title</th>
					<th>Type</th>
					<th>Created</th>

				</tr>
			</thead>
			<tr>
				<td></td>
			</tr>
		</table>
	</div>

	<div class="form-editor ticket-form">
		<div class="form-editor-header">
			<div class="form-editor-header-title-container">
				<span id="ticket-editor-header-title" class="title">Title</span>
				<div class="form-editor-subtitle">
					<span>Ticket id:</span> <span id="ticket-editor-header-id"></span>
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
						<span class="attribute-name">Title:</span> <input id="ticket-editor-input-title" type="text">
					</div>
					<div>
						<span class="attribute-name">ID:</span> <span id="ticket-editor-id" class="ticket-editor-value-field"></span>
					</div>
					<div>
						<span class="attribute-name">Project:</span> <input id="ticket-editor-input-project" type="text">
					</div>
					<div>
						<span class="attribute-name">Type:</span> <select name="type" id="ticket-editor-select-type" class="select-menu">
							<option selected="selected">Bug</option>
							<option>Task</option>
							<option>New Feature</option>
							<option>Improvement</option>
							<option>Support</option>
						</select>
					</div>
					<div>
						<span class="attribute-name">Priority:</span> <select name="priority" id="ticket-editor-select-priority"
							class="select-menu">
							<option selected="selected">Trivial</option>
							<option>Minor</option>
							<option>Major</option>
							<option>Critical</option>
							<option>Blocker</option>
						</select>
					</div>
					<div>
						<span class="attribute-name">Status:</span> <select name="status" id="ticket-editor-select-status"
							class="select-menu">
							<option selected="selected">None</option>
							<option>Need Info</option>
							<option>In Progress</option>
							<option>Ready</option>
							<option>Fixed</option>
						</select>
					</div>
				</fieldset>
				<fieldset class="ticket-editor-description-fieldset">
					<legend>Description</legend>
					<div>
						<textarea rows="1" id="ticket-editor-textarea-description"></textarea>
					</div>
				</fieldset>
				<fieldset class="ticket-editor-comments-fieldset">
					<legend>Comments</legend>
					<div>
						<textarea rows="2" id="ticket-editor-textarea-comment"></textarea>
						<button id="add-comment-button" disabled="true">Add Comment</button>

						<div class="clear"></div>

						<div id="ticket-editor-comment-container">
							<ul class="list"></ul>
						</div>

						<div style="display: none;">
							<li id="comment-item">
								<hr class="faded" />
								<div>
									<span class="user"></span> <span class="added"></span>
									<p class="comment"></p>
								</div>
							</li>
						</div>
					</div>
				</fieldset>
			</div>
			<div class="form-editor-content-narrow-second">
				<fieldset class="ticket-editor-people-fieldset fieldset-input-fixed-height">
					<legend>People</legend>
					<div>
						<span class="attribute-name">Reporter:</span> <span id="ticket-editor-input-reporter"
							class="form-editor-fixed-value-field"></span>
					</div>
					<div>
						<span class="attribute-name">Assignee:</span> <input id="ticket-editor-input-assignee" type="text" class="user">
					</div>
				</fieldset>
				<fieldset class="ticket-editor-dated-fieldset fieldset-input-fixed-height">
					<legend>Dates</legend>
					<div>
						<span class="attribute-name">Created:</span> <span id="ticket-editor-created"
							class="form-editor-fixed-value-field"></span>
					</div>
					<div>
						<span class="attribute-name">Deadline:</span> <input id="ticket-editor-input-deadline" type="text"
							class="calendar">
					</div>
				</fieldset>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="clear"></div>
</div>


<jsp:include page="../jspf/footer.jspf"></jsp:include>