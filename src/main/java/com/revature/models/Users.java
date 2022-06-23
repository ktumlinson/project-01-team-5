package com.revature.models;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ers_users")
public class Users {

	@Id @Column(name="ers_users_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="ers_username")
	private String username;
	
	@Column(name="ers_password")
	private String password;
	
	@Column(name="user_first_name")
	private String firstname;
	
	@Column(name="user_last_name")
	private String lastname;
	
	@Column(name="user_email")
	private String email;
	
	
	@ManyToOne(targetEntity=UserRoles.class, optional=false)
	@JoinColumn(name="user_role_id", referencedColumnName="ers_user_role_id")
	private int roleId;

	@Column(name="user_information") @Basic(optional=true)
	private String userInfo;
	
	
	public Users() {
		super();
	}




	public Users(String username, String password, String firstname, String lastname, String email, int roleId, String userInfo) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.roleId = roleId;
		this.userInfo = userInfo;
	}




	public Users(int ers_users_id, String username, String password, String firstname, String lastname, String email,
			int roleId, String userInfo) {
		super();
		this.userInfo = userInfo;
		this.id = ers_users_id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.roleId = roleId;
	}




	public String getUserInfo() {
		return userInfo;
	}




	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}




	public int getId() {
		return id;
	}




	public void setId(int ers_users_id) {
		this.id = ers_users_id;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getFirstname() {
		return firstname;
	}




	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}




	public String getLastname() {
		return lastname;
	}




	public void setLastname(String lastname) {
		this.lastname = lastname;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public int getRoleId() {
		return roleId;
	}




	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}




	@Override
	public int hashCode() {
		return Objects.hash(email, id, firstname, lastname, password, roleId, userInfo, username);
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		return Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(firstname, other.firstname) && Objects.equals(lastname, other.lastname)
				&& Objects.equals(password, other.password) && roleId == other.roleId
				&& Objects.equals(userInfo, other.userInfo) && Objects.equals(username, other.username);
	}




	@Override
	public String toString() {
		return "Users [ers_users_id=" + id + ", username=" + username + ", password=" + password
				+ ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", roleId=" + roleId
				+ ", userInfo=" + userInfo + "]";
	}
	
	
	
	
	
	
}
