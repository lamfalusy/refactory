package hu.neuron.java.refactory.login;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.eclipse.jetty.jaas.JAASPrincipal;
import org.eclipse.jetty.jaas.JAASRole;
import org.eclipse.jetty.jaas.callback.ObjectCallback;

public class LoginModule2 implements LoginModule {

	public boolean login() throws LoginException {

		System.out.println("Login Module - login called");
		if (callbackHandler == null) {
			throw new LoginException("Oops, callbackHandler is null");
		}

		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("name:");
		callbacks[1] = new PasswordCallback("password:", false);

		try {
			callbackHandler.handle(callbacks);
		} catch (IOException e) {
			throw new LoginException(
					"Oops, IOException calling handle on callbackHandler");
		} catch (UnsupportedCallbackException e) {
			throw new LoginException(
					"Oops, UnsupportedCallbackException calling handle on callbackHandler");
		}

		NameCallback nameCallback = (NameCallback) callbacks[0];
		PasswordCallback passwordCallback = (PasswordCallback) callbacks[1];

		String name = nameCallback.getName();
		String password = new String(passwordCallback.getPassword());

		if ("name".equals(name) && "password".equals(password)) {
			System.out.println("Success! You get to log in!");
			setAuthenticated(true);

		} else {
			System.out.println("Failure! You don't get to log in");
			setAuthenticated(false);
			throw new FailedLoginException("Sorry! No login for you.");
		}

		List<String> roleNames = new ArrayList<String>();
		roleNames.add("user");
		UserInfo userInfo = new UserInfo("name", null, roleNames);
		currentUser = new JAASUserInfo(userInfo);
		return authState;
	}

	private CallbackHandler callbackHandler;

	private boolean authState = false;
	private boolean commitState = false;
	private JAASUserInfo currentUser;
	private Subject subject;

	public class JAASUserInfo {
		private UserInfo user;
		private Principal principal;
		private List<JAASRole> roles;

		public JAASUserInfo(UserInfo u) {
			setUserInfo(u);
		}

		public String getUserName() {
			return this.user.getUserName();
		}

		public Principal getPrincipal() {
			return this.principal;
		}

		public void setUserInfo(UserInfo u) {
			this.user = u;
			this.principal = new JAASPrincipal(u.getUserName());
			this.roles = new ArrayList<JAASRole>();
			if (u.getRoleNames() != null) {
				Iterator<String> itor = u.getRoleNames().iterator();
				while (itor.hasNext())
					this.roles.add(new JAASRole((String) itor.next()));
			}
		}

		public void setJAASInfo(Subject subject) {
			subject.getPrincipals().add(this.principal);
			subject.getPrivateCredentials().add(this.user.getCredential());
			subject.getPrincipals().addAll(roles);
		}

		public void unsetJAASInfo(Subject subject) {
			subject.getPrincipals().remove(this.principal);
			subject.getPrivateCredentials().remove(this.user.getCredential());
			subject.getPrincipals().removeAll(this.roles);
		}

		public boolean checkCredential(Object suppliedCredential) {
			return this.user.checkCredential(suppliedCredential);
		}
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject s) {
		this.subject = s;
	}

	public JAASUserInfo getCurrentUser() {
		return this.currentUser;
	}

	public void setCurrentUser(JAASUserInfo u) {
		this.currentUser = u;
	}

	public CallbackHandler getCallbackHandler() {
		return this.callbackHandler;
	}

	public void setCallbackHandler(CallbackHandler h) {
		this.callbackHandler = h;
	}

	public boolean isAuthenticated() {
		return this.authState;
	}

	public boolean isCommitted() {
		return this.commitState;
	}

	public void setAuthenticated(boolean authState) {
		this.authState = authState;
	}

	public void setCommitted(boolean commitState) {
		this.commitState = commitState;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#abort()
	 * @throws LoginException
	 */
	public boolean abort() throws LoginException {
		this.currentUser = null;
		return (isAuthenticated() && isCommitted());
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#commit()
	 * @return true if committed, false if not (likely not authenticated)
	 * @throws LoginException
	 */
	public boolean commit() throws LoginException {

		if (!isAuthenticated()) {
			currentUser = null;
			setCommitted(false);
			return false;
		}

		setCommitted(true);
		currentUser.setJAASInfo(subject);
		return true;
	}

	public Callback[] configureCallbacks() {

		Callback[] callbacks = new Callback[3];
		callbacks[0] = new NameCallback("Enter user name");
		callbacks[1] = new ObjectCallback();
		callbacks[2] = new PasswordCallback("Enter password", false); // only
																		// used
																		// if
																		// framework
																		// does
																		// not
																		// support
																		// the
																		// ObjectCallback
		return callbacks;
	}

	public boolean isIgnored() {
		return false;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#logout()
	 * @return true always
	 * @throws LoginException
	 */
	public boolean logout() throws LoginException {
		this.currentUser.unsetJAASInfo(this.subject);
		return true;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#initialize(javax.security.auth.Subject,
	 *      javax.security.auth.callback.CallbackHandler, java.util.Map,
	 *      java.util.Map)
	 * @param subject
	 * @param callbackHandler
	 * @param sharedState
	 * @param options
	 */
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		this.callbackHandler = callbackHandler;
		this.subject = subject;
	}

}
