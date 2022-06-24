package com.revature.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ers_reimbursement_types")
public class ReimbursementType {

	
	@Id @Column(name="reimb_type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="reimb_type")
	private ReimbursementTypeEnum status;

	
	
	public ReimbursementType() {
		super();
	}

	public ReimbursementType(ReimbursementTypeEnum status) {
		super();
		this.status = status;
	}

	public ReimbursementType(int id, ReimbursementTypeEnum status) {
		super();
		this.id = id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int reimb_type_id) {
		this.id = reimb_type_id;
	}

	public ReimbursementTypeEnum getStatus() {
		return status;
	}

	public void setStatus(ReimbursementTypeEnum status) {
		this.status = status;
	}
	
	
	

}
