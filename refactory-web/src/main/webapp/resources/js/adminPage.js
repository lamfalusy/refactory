$(function() {
	var dialog, form,
//	$( "#role" ).selectmenu();
	// From
	// http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
	emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/, 
	fullName = $("#fullName"), 
	name = $("#name"), 
	email = $("#email"), 
	password = $("#password"), 
	role = $("#role"),
	allFields = $([]).add(fullName).add(name).add(email).add(password).add(role), tips = $(".validateTips");

	function updateTips(t) {
		tips.text(t).addClass("ui-state-highlight");
		setTimeout(function() {
			tips.removeClass("ui-state-highlight", 1500);
		}, 500);
	}

	function checkLength(o, n, min, max) {
		if (o.val().length > max || o.val().length < min) {
			o.addClass("ui-state-error");
			updateTips("Length of " + n + " must be between " + min + " and " + max + ".");
			return false;
		} else {
			return true;
		}
	}

	function checkRegexp(o, regexp, n) {
		if (!(regexp.test(o.val()))) {
			o.addClass("ui-state-error");
			updateTips(n);
			return false;
		} else {
			return true;
		}
	}

	function addUser() {
		var valid = true;
		allFields.removeClass("ui-state-error");

		valid = valid && checkLength(fullName, "full name", 3, 16);
		valid = valid && checkLength(name, "login name", 3, 16);
		valid = valid && checkLength(email, "email", 6, 80);
		valid = valid && checkLength(password, "password", 5, 16);

		var fullNameRegexError = "Full name may consist of a-z, spaces and must begin with a letter.";
		var loginNameRegexError = "Login name may consist of a-z, 0-9, underscores, spaces and must begin with a letter.";
		valid = valid && checkRegexp(fullName, /^[a-z]([a-z\s])+$/i, fullNameRegexError);
		valid = valid && checkRegexp(name, /^[a-z]([0-9a-z_\s])+$/i, loginNameRegexError);
		valid = valid && checkRegexp(email, emailRegex, "eg. ui@jquery.com");
		valid = valid && checkRegexp(password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9");

		console.log(valid);
		if (valid) {
			console.log(role.val());
			$("#users tbody").append(
					"<tr>" + "<td>" + fullName.val() + "</td>" + "<td>" + name.val() + "</td>" + "<td>" + email.val()
							+ "</td>" + "<td>" + password.val() + "</td>" + "<td>" + role.val() + "</td>" + "</tr>");
			
			$.post("UserHandlerServlet", {
				fullName : fullName.val(),
				name : name.val(),
				email : email.val(),
				password : password.val(),
				role : role.val(),
			}, function(data) {
				console.log(data);
			});
			dialog.dialog("close");
		}
		return valid;
	}

	dialog = $("#dialog-form").dialog({
		autoOpen : false,
		height : 600,
		width : 600,
		modal : true,
		buttons : {
			"Create an account" : addUser,
			Cancel : function() {
				dialog.dialog("close");
			}
		},
		close : function() {
			form[0].reset();
			allFields.removeClass("ui-state-error");
		}
	});

	form = dialog.find("form").on("submit", function(event) {
		event.preventDefault();
		addUser();
	});

	$("#create-user").button().on("click", function() {
		dialog.dialog("open");
	});
});