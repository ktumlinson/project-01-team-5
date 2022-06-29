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
	
	@Id @Column(name="reimb_status_id")
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

	public static EReimbursementStatus generater(String status) {
		String statusString = "";
		int id = 0;
		switch(status) {
		case "approved":
			statusString = "approved";
			id = 2;
			break;
		case "rejected":
			statusString = "rejected";
			id = 3;
			break;
		default:
			statusString = "pending";
			id = 1;
			break;
		}
		return new EReimbursementStatus(id, statusString);
	}


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
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
