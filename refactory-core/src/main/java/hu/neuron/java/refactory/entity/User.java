package hu.neuron.java.refactory.entity;

import hu.neuron.java.refactory.type.RoleType;

public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String loginName;
	private String fullName;
	private String email;
	private RoleType role;
	
	public User() {
		
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
	public RoleType getRole() {
		return role;
	}
	public void setRole(RoleType role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [loginName=" + loginName + ", fullName=" + fullName
				+ ", email=" + email + ", role=" + role + "]";
	} 
}
