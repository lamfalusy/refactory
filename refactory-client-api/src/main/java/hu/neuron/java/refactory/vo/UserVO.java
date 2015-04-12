package hu.neuron.java.refactory.vo;

import hu.neuron.java.refactory.type.RoleType;

import java.io.Serializable;

public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String loginName;
	private String fullName;
	private String email;
	private String password;
	private RoleType role;
	
	public UserVO() {
		
	}
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleType getRole() {
		return role;
	}
	public void setRole(RoleType role) {
		this.role = role;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserVO [loginName=" + loginName + ", fullName=" + fullName
				+ ", email=" + email + ", role=" + role + "]";
	} 
	
}
