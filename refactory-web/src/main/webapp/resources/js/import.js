$(document).ready(
	function() {
	
		// IMPORTALAS
		$('#sites-import-button').click(function() {
			var url = $('#sites-import-url').val();
			console.log(url);
			sitesImport(url);
		});
});

function sitesImport(url) {
	console.log(url);
	$.ajax({
		url : 'SitesImportServlet',
		data : {
			url : url,
		},
		dataType : 'json',
		success : function(response) {
			createMessageBox("info", "Success import!");
		}
	});
}