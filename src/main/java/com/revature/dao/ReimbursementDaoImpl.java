package com.revature.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class ReimbursementDaoImpl implements IReimbursementDao{

	public static HashMap<ReimbursementStatus, Integer> reimbStatusLookup = new HashMap<>();
	public static HashMap<ReimbursementType, Integer> reimbTypeLookup = new HashMap<>();
	{
		reimbStatusLookup.put(ReimbursementStatus.APPROVED, 0);
		reimbStatusLookup.put(ReimbursementStatus.REJECTED, 1);
		reimbStatusLookup.put(ReimbursementStatus.PENDING, 2);
		reimbTypeLookup.put(ReimbursementType.FOOD, 0);
		reimbTypeLookup.put(ReimbursementType.LODGING, 1);
		reimbTypeLookup.put(ReimbursementType.TRAVEL, 2);
		reimbTypeLookup.put(ReimbursementType.OTHER, 3);
		
	}
	
	@Override
	public int insert(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();
		int result = (int) ses.save(r);
		tx.commit();
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
		Transaction tx = ses.getTransaction();
		Reimbursement current = new Reimbursement();
		current = (Reimbursement) ses.merge(r.getId());
		tx.commit();
		// if the user and current have the same values then return true
		return current.getId() != 0;
	}

	@Override
	public boolean delete(Reimbursement r) {
		if(r.getId() == 0) {
			return false;
		}
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();

		ses.remove(r);
		tx.commit();
		return true;
	}

}
