package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ers_user_roles")
public class UserRole {
	
	@Id @Column(name="ers_user_role_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user_role")
	private String role;
	
	
	
	

	public UserRole() {
		super();
	}

	public UserRole(String role) {
		super();
		this.role = role;
	}

	public UserRole(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
	
	public static UserRole roleGenerator(String role) {
		int id = 0;
		String newRole = "";
		switch(role) {
		case "Manager":
			newRole = "Manager";
			id = 2;
			break;
			default:
				id = 1;
				newRole = "Employee";
				break;
		}
		return new UserRole(id, newRole);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, role);
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
		return id == other.id && Objects.equals(role, other.role);
	}

	@Override
	public String toString() {
		return "EUserRole [id=" + id + ", role=" + role + "]";
	}
	
	
	
}
