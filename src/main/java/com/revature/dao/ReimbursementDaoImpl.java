package com.revature.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;

import com.revature.models.UserEmp;
import com.revature.util.HibernateUtil;

public class ReimbursementDaoImpl implements IReimbursementDao{

	
	
	@Override
	public int insert(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();
		int result = (int) ses.save(r);
		return result;
	}

	@Override
	public List<Reimbursement> findAllReimbersements() {
		Session ses = HibernateUtil.getSession();
		return ses.createQuery("from Reimbursement", Reimbursement.class).list();
	}

	@Override
	public Reimbursement findReimbursementById(int id) {
		Session ses = HibernateUtil.getSession();
		return (Reimbursement)ses.createQuery("from Reimbursement where id=:id")
				.setParameter("id", id).getSingleResult();
	}

	@Override
	public boolean update(Reimbursement r) {
		if(r.getId() == 0) {
			// throw an exception here for an unfound user if we have time to make exceptions
			return false;
		}
		Session ses = HibernateUtil.getSession();
		Reimbursement current = new Reimbursement();
		current = (Reimbursement) ses.merge(r.getId());
		// if the user and current have the same values then return true
		return current.getId() != 0;
	}

	@Override
	public boolean delete(Reimbursement r) {
		if(r.getId() == 0) {
			return false;
		}
		Session ses = HibernateUtil.getSession();

		ses.remove(r);
		return true;
	}

}
