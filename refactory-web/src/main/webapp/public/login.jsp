<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../jspf/header.jspf" %>
	<div class="content">
		<div class="login-container">
			<div class="overlay"></div>

			<div class="login-panel">
				<div class="login-panel-header">
					<span>Login</span>
				</div>
				<div class="login-panel-content">
					<form id="loginform" method="post" action="j_security_check" name="loginform">
						<c:if test="${param.error}">
							<font color="red">Hibás felhasználóném vagy jelszó!</font>
						</c:if>
						<div class="field-group">
							<label accesskey="u" for="login-form-username" id="usernamelabel">
								<u>U</u>sername
							</label>
							<input id="login-form-username"	name="j_username" type="text"/>
						</div>
						<div class="field-group">
							<label accesskey="p" for="login-form-password" id="passwordlabel">
								<u>P</u>assword
							</label>
							<input id="login-form-password" name="j_password" type="password"/>
						</div>
						<fieldset class="group">
							<div class="checkbox" id="rememberme">
								<input class="checkbox" id="login-form-remember-me"
									name="os_cookie" type="checkbox" value="true"><label
									accesskey="r" for="login-form-remember-me"
									id="remembermelabel"><u>R</u>emember my login on this
									computer</label>
							</div>
						</fieldset>
						<div class="field-group" id="publicmodeooff">
							<div id="publicmodeoffmsg">Not a member? To request an
								account, please contact your JIRA administrators.</div>
						</div>
						<div class="buttons-container">
							<div class="buttons">
								<button class="button" id="login" name="login">Log In</button>
									<a class="cancel"
									href="#"
									id="forgotpassword" target="_parent">Can't access your
									account?</a>
							</div>
						</div>
					</form>
				</div>
			</div>

		</div>

	</div>
</body>
</html>