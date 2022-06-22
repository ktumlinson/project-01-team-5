package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ers_reimbursement_types")
public class ReimbursementType {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@OneToMany(mappedBy="ers_reimbursement_types")
	private int reimb_type_id;
	
	@Column(name="reimb_type")
	private String status;

	public int getReimb_type_id() {
		return reimb_type_id;
	}

	public void setReimb_type_id(int reimb_type_id) {
		this.reimb_type_id = reimb_type_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
