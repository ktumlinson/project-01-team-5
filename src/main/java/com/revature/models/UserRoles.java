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

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@OneToMany(mappedBy="ers_user_roles")
	private int ers_user_role_id;
	
	@Column(name="user_role")
	private String userRole;

	public UserRoles(int id, String userRole) {
		super();
		this.ers_user_role_id = id;
		this.userRole = userRole;
	}

	public UserRoles(String userRole) {
		super();
		this.userRole = userRole;
	}

	public UserRoles() {
		super();
	}

	public int getId() {
		return ers_user_role_id;
	}

	public void setId(int id) {
		this.ers_user_role_id = id;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "User_Roles [id=" + ers_user_role_id + ", userRole=" + userRole + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ers_user_role_id, userRole);
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
		return ers_user_role_id == other.ers_user_role_id && Objects.equals(userRole, other.userRole);
	}
	
	
	
}