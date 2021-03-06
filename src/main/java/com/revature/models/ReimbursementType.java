package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ers_reimbursement_type")
public class ReimbursementType {
	
	@Id @Column(name="reimb_type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="reimb_type")
	private String type;
	
	public ReimbursementType() {
		super();
	}

	public ReimbursementType(String type) {
		super();
		this.type = type;
	}

	public ReimbursementType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	
	public static ReimbursementType generater(String type) {
		String typeString = "";
		int id = 0;
		switch(type) {
		case "lodging":
			typeString = "lodging";
			id = 1;
			break;
		case "travel":
			typeString = "travel";
			id = 2;
			break;
		case "food":
			typeString = "food";
			id = 3;
			break;
		default:
			typeString = "other";
			id = 4;
			break;
		}
		return new ReimbursementType(id, typeString);
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(id, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementType other = (ReimbursementType) obj;
		return id == other.id && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "EReimbursementType [id=" + id + ", type=" + type + "]";
	}
	
	
	
	
}
