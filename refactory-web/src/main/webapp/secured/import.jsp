<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@include file="../jspf/header.jspf"%>
<%@include file="../jspf/navbar.jspf"%>

<script>
	$('#import-site').addClass('active');
</script>
<div class="page-content">
	<div class="form-editor">

		<div class="form-editor-header">
			<div class="form-editor-header-title-container">
				<span id="form-editor-header-title" class="title">Sites import</span>
			</div>
			<div>
				<span class="attribute-name">Sites url:</span> <input id="sites-import-url" type="text">
			</div>
			<button id="sites-import-button">Import</button>
		</div>

	</div>
</div>

<jsp:include page="../jspf/footer.jspf"></jsp:include>