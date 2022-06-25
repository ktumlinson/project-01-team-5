package com.revature.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class ReimbursementImpl implements IReimbursementDao{

	@Override
	public int insert(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();
		tx.commit();
		return (int) ses.save(r);
	}

	@Override
	public List<Reimbursement> findAllReimbersements() {
		Session ses = HibernateUtil.getSession();
		return ses.createQuery("from Reimbursement", Reimbursement.class).list();
	}

	@Override
	public Reimbursement findReimbursementById(int id) {
		Optional<Reimbursement> returnReimbursement = findAllReimbersements().stream()
				.filter(r -> r.getId() == id)
				.findFirst();
		return (returnReimbursement.isPresent() ? returnReimbursement.get() : new Reimbursement());
	}

	@Override
	public boolean update(Reimbursement r) {
		if(r.getId() == 0) {
			// throw an exception here for an unfound user if we have time to make exceptions
			return false;
		}
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();
		ses.merge(r.getId());
		tx.commit();
		Reimbursement current = findReimbursementById(r.getId());
		
		// if the user and current have the same values then return true
		return (r.toString().equals(current.toString()) ? true : false);
	}

	@Override
	public boolean delete(Reimbursement r) {
		if(r.getId() == 0) {
			return false;
		}
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();
		ses.remove(r.getId());
		tx.commit();
		Reimbursement current = findReimbursementById(r.getId());
		
		return (current.getId() == 0 ? true : false);
	}

}
