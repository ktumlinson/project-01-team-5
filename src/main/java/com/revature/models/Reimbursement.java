package com.revature.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ers_reimbursement")
public class Reimbursement {

	@Id @Column(name="reimb_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
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

	@ManyToOne(targetEntity=Users.class, optional=false)
	@JoinColumn(name="reimb_author", referencedColumnName="ers_users_id")
	private int employeeId;
	
	
	// FK to UserId of Manager resolver

	@ManyToOne(targetEntity=Users.class, optional=false)
	@JoinColumn(name="reimb_resolver", referencedColumnName="ers_users_id")
	private int managerId;
	
	// FK to reim_type_status
	@ManyToOne(targetEntity=ReimbursementStatus.class, optional=false)
	@JoinColumn(name="reimb_status_id", referencedColumnName="reimb_status_id")
	private int statusId;
	
	// FK to reim_type_id
	@ManyToOne(targetEntity=ReimbursementType.class, optional=false)
	@JoinColumn(name="reimb_type_id", referencedColumnName="reimb_type_id")
	private int reimbursementId;

	// CONSTRUCTORS
	
	
	
	
	
	

	public Reimbursement() {
		super();
	}
	
	public Reimbursement(int reimbursementAmt, Timestamp timeSubmitted, Timestamp timeResolved, String description,
			int employeeId, int managerId, int statusId, int reimbursementId) {
		super();
		this.reimbursementAmt = reimbursementAmt;
		this.timeSubmitted = timeSubmitted;
		this.timeResolved = timeResolved;
		this.description = description;
		this.employeeId = employeeId;
		this.managerId = managerId;
		this.statusId = statusId;
		this.reimbursementId = reimbursementId;
	}

	public Reimbursement(int id, int reimbursementAmt, Timestamp timeSubmitted, Timestamp timeResolved,
			String description, int employeeId, int managerId, int statusId, int reimbursementId) {
		super();
		this.id = id;
		this.reimbursementAmt = reimbursementAmt;
		this.timeSubmitted = timeSubmitted;
		this.timeResolved = timeResolved;
		this.description = description;
		this.employeeId = employeeId;
		this.managerId = managerId;
		this.statusId = statusId;
		this.reimbursementId = reimbursementId;
	}

	// GETTERS + SETTERS
		public int getId() {
			return id;
		}

	public void setId(int id) {
		this.id = id;
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

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	
	
	
	
	
	
	
}
