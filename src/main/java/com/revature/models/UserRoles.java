package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ers_user_roles")
public class UserRoles {

	@Id @Column(name="ers_user_role_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user_role")
	private String userRole;

	public UserRoles(int id, String userRole) {
		super();
		this.id = id;
		this.userRole = userRole;
	}

	public UserRoles(String userRole) {
		super();
		this.userRole = userRole;
	}

	public UserRoles() {
		super();
	}
	
	// GETTERS + SETTERS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "User_Roles [id=" + id + ", userRole=" + userRole + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userRole);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRoles other = (UserRoles) obj;
		return id == other.id && Objects.equals(userRole, other.userRole);
	}
	
	
	
}
