package com.revature.models;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ers_reimbursement")
public class Reimbursement {

	@Id
	@Column(name = "reimb_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="reimb_type")
	@Enumerated(EnumType.STRING)
	private EReimbursementType type;
	
	@Column(name="reimb_status")
	@Enumerated(EnumType.STRING)
	private EReimbursementStatus status;

	@Column(name = "reimb_amount")
	private int reimbursementAmt;

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
}
