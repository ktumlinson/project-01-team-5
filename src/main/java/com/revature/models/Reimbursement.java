package com.revature.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ers_reimbursement")
public class Reimbursement {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reimb_id;
	
	@Column(name="reimb_amount")
	private int reimbursementAmt;
	
	@Column(name="reimb_submitted")
	private java.sql.Timestamp timeSubmitted;
	
	@Column(name="reimb_resolved") @Basic(optional=true)
	private java.sql.Timestamp timeResolved;
	
	@Column(name="reimb_description") @Basic(optional=true)
	private String description;
	
	// Optional Field for stretch goal
	//private String receipt;
	
	// FK to UserId of Employee
	@Column(name="reimb_author")
	private int employeeId;
	
	
	// FK to UserId of Manager resolver
	@Column(name="reimb_resolver")
	private int managerId;
	
	// FK to reim_type_status
	@Column(name="reimb_status_id")
	private int statusId;
	
	// FK to reim_type_id
	@Column(name="reimb_type_id")
	private int reimbursementTypeId;

	public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public int getReimbursementAmt() {
		return reimbursementAmt;
	}

	public void setReimbursementAmt(int reimbursementAmt) {
		this.reimbursementAmt = reimbursementAmt;
	}

	public java.sql.Timestamp getTimeSubmitted() {
		return timeSubmitted;
	}

	public void setTimeSubmitted(java.sql.Timestamp timeSubmitted) {
		this.timeSubmitted = timeSubmitted;
	}

	public java.sql.Timestamp getTimeResolved() {
		return timeResolved;
	}

	public void setTimeResolved(java.sql.Timestamp timeResolved) {
		this.timeResolved = timeResolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getReimbursementTypeId() {
		return reimbursementTypeId;
	}

	public void setReimbursementTypeId(int reimbursementTypeId) {
		this.reimbursementTypeId = reimbursementTypeId;
	}
	
	
	
	
}
