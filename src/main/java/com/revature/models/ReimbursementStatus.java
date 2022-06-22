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
	
	@Id @Column(name="reimb_status_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@OneToMany(mappedBy="ers_reimbursement_status")
	private int id;
	
	@Column(name="reimb_status")
	private String reimbursementStatus;

	
	
	public ReimbursementStatus() {
		super();
	}

	public ReimbursementStatus(String reimbursementStatus) {
		super();
		this.reimbursementStatus = reimbursementStatus;
	}

	public ReimbursementStatus(int id, String reimbursementStatus) {
		super();
		this.id = id;
		this.reimbursementStatus = reimbursementStatus;
	}

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
