package com.revature.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ers_reimbursement_status")
public class ReimbursementStatus {
	
	@Id @Column(name="reimb_status_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="reimb_status")
	private ReimbursementStatusEnum reimbursementStatus;

	
	
	public ReimbursementStatus() {
		super();
	}

	public ReimbursementStatus(ReimbursementStatusEnum reimbursementStatus) {
		super();
		this.reimbursementStatus = reimbursementStatus;
	}

	public ReimbursementStatus(int id, ReimbursementStatusEnum reimbursementStatus) {
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

	public ReimbursementStatusEnum getReimbursementStatus() {
		return reimbursementStatus;
	}

	public void setReimbursementStatus(ReimbursementStatusEnum reimbursementStatus) {
		this.reimbursementStatus = reimbursementStatus;
	}
	
	

}
