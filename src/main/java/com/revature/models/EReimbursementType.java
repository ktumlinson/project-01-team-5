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
public class EReimbursementType {
	
	@Id @Column(name="reimb_type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="reimb_type")
	private String type;
	
	public EReimbursementType() {
		super();
	}

	public EReimbursementType(String type) {
		super();
		this.type = type;
	}

	public EReimbursementType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
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
		EReimbursementType other = (EReimbursementType) obj;
		return id == other.id && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "EReimbursementType [id=" + id + ", type=" + type + "]";
	}
	
	
	
	
}
