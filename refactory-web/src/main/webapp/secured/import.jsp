<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../jspf/header.jspf"%>
<%@include file="../jspf/navbar.jspf"%>
<div class="content">
		<div class="ticket-editor-container">
			<div class="ticket-editor">
				<div class="ticket-editor-header">
					<div class="ticket-editor-header-title-container">
						<span id="ticket-editor-header-title" class="ticket-title">Sites
							import</span>
					</div>
					<div>
						<span class="attribute-name">Sites url:</span> <input
							id="sites-import-url" type="text">
					</div>
					<button id="sites-import-button">Import</button>
				</div>
			</div>
		</div>
</div>

<jsp:include page="../jspf/footer.jspf"></jsp:include>