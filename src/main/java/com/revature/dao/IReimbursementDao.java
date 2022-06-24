package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface IReimbursementDao {
	// CRUD
	
	// create
	public int insert(Reimbursement r);
	
	// read
	public List<Reimbursement> findAllReimbersements();
	public Reimbursement findReimbursementById(int id);
	
	// update
	public boolean update(Reimbursement r);
	
	// delete
	public boolean delete(Reimbursement r);
}