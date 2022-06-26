package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ers_reimbursement_status")
public class EReimbursementStatus {
	
	@Id @Column(name="erimb_status_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="reimb_status")
	private String status;

	
	
	public EReimbursementStatus() {
		super();
	}



	public EReimbursementStatus(String status) {
		super();
		this.status = status;
	}



	public EReimbursementStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id, status);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EReimbursementStatus other = (EReimbursementStatus) obj;
		return id == other.id && Objects.equals(status, other.status);
	}



	@Override
	public String toString() {
		return "EReimbursementStatus [id=" + id + ", status=" + status + "]";
	}
	
	
	
}
