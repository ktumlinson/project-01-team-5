package com.revature.models;


import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ers_user_roles")
public class UserRole {

	@Id @Column(name="ers_user_role_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="user_role")
	private UserRoleEnum userRole;

	
	
	public UserRole() {
		super();
	}

	public UserRole(UserRoleEnum userRole) {
		super();
		this.userRole = userRole;
	}

	public UserRole(int id, UserRoleEnum userRole) {
		super();
		this.id = id;
		this.userRole = userRole;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserRoleEnum getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoleEnum userRole) {
		this.userRole = userRole;
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
		UserRole other = (UserRole) obj;
		return id == other.id && Objects.equals(userRole, other.userRole);
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", userRole=" + userRole + "]";
	}

	

}
