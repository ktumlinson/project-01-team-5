package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employees")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="first_name")
	private String fistName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(unique=true)
	private String username;
	
	@Column(name="pwd")
	private String password;

	public Employee(int id, String fistName, String lastName, String username, String password) {
		super();
		this.id = id;
		this.fistName = fistName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public Employee(String fistName, String lastName, String username, String password) {
		super();
		this.fistName = fistName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public Employee() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(fistName, id, lastName, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(fistName, other.fistName) && id == other.id && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fistName=" + fistName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFistName() {
		return fistName;
	}

	public void setFistName(String fistName) {
		this.fistName = fistName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	
}
