package hu.neuron.java.refactory.login;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.eclipse.jetty.jaas.spi.AbstractLoginModule;
import org.eclipse.jetty.jaas.spi.UserInfo;
import org.eclipse.jetty.util.security.Credential;

public class LoginModule extends AbstractLoginModule {

	@Override
	public boolean login() throws LoginException {
		// TODO Auto-generated method stub
		return super.login();
	}

	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		return super.logout();
	}

	@Override
	public UserInfo getUserInfo(String username) throws Exception {

		if (!"test".equals(username)) {
			return null;
		}

		Credential credential = new Credential() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean check(Object arg0) {
				return "test".equals(arg0);
			}

		};
		List<String> roles = new ArrayList<String>();
		roles.add("user");

		return new UserInfo(username, credential, roles);
	}

}
