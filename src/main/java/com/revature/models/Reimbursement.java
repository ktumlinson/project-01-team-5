package com.revature.models;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ers_reimbursement")
public class Reimbursement {

	@Id
	@Column(name = "reimb_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="reimb_type_id")
	private EReimbursementType type;
	
	@ManyToOne()
	@JoinColumn(name="reimb_status_id")
	private EReimbursementStatus status;

	@Column(name = "reimb_amount")
	private double reimbursementAmt;

	@Column(name = "reimb_submitted")
	private java.sql.Timestamp timeSubmitted;

	@Column(name = "reimb_resolved") 
	@Basic(optional = true)
	private java.sql.Timestamp timeResolved;

	@Column(name = "reimb_description")
	@Basic(optional = true)
	private String description;

	// Optional Field for stretch goal
	// private String receipt;

	// FK to UserId of Employee
	@ManyToOne(targetEntity = User.class, optional = false)
//	@JoinColumn(name="reimb_author", referencedColumnName="ers_users_id")
	private User employee;

	// FK to UserId of Manager resolver
	@ManyToOne(targetEntity = User.class, optional = false)
//	@JoinColumn(name="reimb_resolver", referencedColumnName="ers_users_id")
	private User managerId;

	public Reimbursement(int id, EReimbursementType type, EReimbursementStatus status, double reimbursementAmt,
			Timestamp timeSubmitted, Timestamp timeResolved, String description, User employee, User managerId) {
		super();
		this.id = id;
		this.type = type;
		this.status = status;
		this.reimbursementAmt = reimbursementAmt;
		this.timeSubmitted = timeSubmitted;
		this.timeResolved = timeResolved;
		this.description = description;
		this.employee = employee;
		this.managerId = managerId;
	}

	public Reimbursement(EReimbursementType type, EReimbursementStatus status, double reimbursementAmt,
			Timestamp timeSubmitted, Timestamp timeResolved, String description, User employee, User managerId) {
		super();
		this.type = type;
		this.status = status;
		this.reimbursementAmt = reimbursementAmt;
		this.timeSubmitted = timeSubmitted;
		this.timeResolved = timeResolved;
		this.description = description;
		this.employee = employee;
		this.managerId = managerId;
	}

	public Reimbursement() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, employee, id, managerId, reimbursementAmt, status, timeResolved, timeSubmitted,
				type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		return Objects.equals(description, other.description) && Objects.equals(employee, other.employee)
				&& id == other.id && Objects.equals(managerId, other.managerId)
				&& reimbursementAmt == other.reimbursementAmt && status == other.status
				&& Objects.equals(timeResolved, other.timeResolved)
				&& Objects.equals(timeSubmitted, other.timeSubmitted) && type == other.type;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", type=" + type + ", status=" + status + ", reimbursementAmt="
				+ reimbursementAmt + ", timeSubmitted=" + timeSubmitted + ", timeResolved=" + timeResolved
				+ ", description=" + description + ", employee=" + employee + ", managerId=" + managerId + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EReimbursementType getType() {
		return type;
	}

	public void setType(EReimbursementType type) {
		this.type = type;
	}

	public EReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(EReimbursementStatus status) {
		this.status = status;
	}

	public double getReimbursementAmt() {
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

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public User getManagerId() {
		return managerId;
	}

	public void setManagerId(User managerId) {
		this.managerId = managerId;
	}
	
	
}
