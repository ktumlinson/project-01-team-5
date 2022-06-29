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
import javax.persistence.Table;



@Entity
@Table(name="ers_users")
public class User {

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
	



	@ManyToOne()
	@JoinColumn(name="ers_user_roles")
	private EUserRole role;

	
	



	public User() {
		super();
	}






	public User(String username, String password, String firstname, String lastname, String email, EUserRole role) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
	}






	public User(int id, String username, String password, String firstname, String lastname, String email,
			EUserRole role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
	}


	public static User managerGenerator() {
		return new User(6, "manager", "password", "blah", "blah", "blah@yahoo.com", EUserRole.roleGenerator("Manager"));
	}



	public int getId() {
		return id;
	}






	public void setId(int id) {
		this.id = id;
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






	public EUserRole getRole() {
		return role;
	}






	public void setRole(EUserRole role) {
		this.role = role;
	}






	@Override
	public int hashCode() {
		return Objects.hash(email, firstname, id, lastname, password, role, username);
	}






	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstname, other.firstname) && id == other.id
				&& Objects.equals(lastname, other.lastname) && Objects.equals(password, other.password)
				&& Objects.equals(role, other.role) && Objects.equals(username, other.username);
	}






	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", role=" + role + "]";
	}




	
	
	
	
}
	
	
	
	
	
	
	

