<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@include file="../jspf/header.jspf"%>
<%@include file="../jspf/navbar.jspf"%>
<!--  style>
div#users-contain, fieldset, .validateTips {
	font-size: 75%;
}

label, input {
	display: block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 500px;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td, div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>-->

<script>
	$('#admin-site').addClass('active');
</script>

<div class="page-content">

	<div id="users-contain">
		<table id="users" class="display" cellspacing="0">
			<thead>
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Role</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>John Doe</td>
					<td>john.doe@example.com</td>
					<td>User</td>
				</tr>
				<tr>
					<td>Rohn Doe</td>
					<td>rohn.doe@example.com</td>
					<td>Admin</td>
				</tr>
			</tbody>
			</tbody>
		</table>
	</div>

	<div class="form-editor user-form">
		<div class="form-editor-header">
			<div class="form-editor-header-title-container">
				<span id="ticket-editor-header-title" class="title">User</span>
				<div class="form-editor-subtitle">Name</div>
			</div>
			<div class="form-editor-header-button-container">
				<button id="create-user">Create</button>
				<button id="update-ticket-button" disabled="true">Update</button>
				<button id="delete-ticket-button" disabled="true">Delete</button>
			</div>
			<div class="clear"></div>
		</div>
		<hr class="faded" />
		<div class="form-editor-content">
			<div class="form-editor-content-descriptor content-panel">

				<form>
					<fieldset>
						<legend>Details</legend>

						<div>
							<span class="attribute-name">Name:</span> <input id="full-name" name="full-name" type="text" value="Kiss Pisti">
						</div>

						<div>
							<span class="attribute-name">User name:</span> <input id="user-name" type="text" value="kissp">
						</div>

						<div>
							<span class="attribute-name">Email:</span> <input id="email" name="email" type="email" value="kiss@pisti.hu">
						</div>

						<div>
							<span class="attribute-name">Role:</span> 
							<select name="role" id="role" class="select-menu">
								<option selected="selected">User</option>
								<option>Admin</option>
							</select>
						</div>

					</fieldset>
				</form>
			</div>
		</div>
	</div>


</div>

<jsp:include page="../jspf/footer.jspf"></jsp:include>