package com.revature.models;

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

	// FK to reim_type_status
	@ManyToOne(targetEntity = ReimbursementStatus.class, optional = false)
	@Enumerated(EnumType.STRING)
//	@JoinColumn(name="reimb_status_id", referencedColumnName="reimb_status_id")
	private ReimbursementStatus status;

	// FK to reim_type_id
	@ManyToOne(targetEntity = ReimbursementType.class, optional = false)
	@Enumerated(EnumType.STRING)
//	@JoinColumn(name="reimb_type_id", referencedColumnName="reimb_type_id")
	private ReimbursementType reimbursementType;


	
}
