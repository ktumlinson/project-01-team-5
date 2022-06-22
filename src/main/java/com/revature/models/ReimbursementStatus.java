package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ers_reimbursement_status")
public class ReimbursementStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@OneToMany(mappedBy="ers_reimbursement_status")
	private int id;
	
	@Column(name="reimb_status_id")
	private String reimbursementStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReimbursementStatus() {
		return reimbursementStatus;
	}

	public void setReimbursementStatus(String reimbursementStatus) {
		this.reimbursementStatus = reimbursementStatus;
	}
	
	
}
