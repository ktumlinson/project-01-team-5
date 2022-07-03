package com.revature.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class ReimbursementImpl implements IReimbursementDao{

	// tested and works
	@Override
	public int insert(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();
		tx.begin();
		int pk = (int)ses.save(r);
		tx.commit();
		return pk;
	}

	// tested and works
	@Override
	public List<Reimbursement> findAllReimbersements() {
		Session ses = HibernateUtil.getSession();
		return ses.createQuery("from Reimbursement", Reimbursement.class).list();
	}

	// tested and works
	@Override
	public Reimbursement findReimbursementById(int id) throws NoResultException{
		Session ses = HibernateUtil.getSession();
		
		Reimbursement reimb = (Reimbursement)ses.createQuery("from Reimbursement where id=:id").setParameter("id", id).getSingleResult();

		return reimb;
	}

	
	// tested and works
	@Override
	public boolean update(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.update(r);
		
//		tx.commit();
		return true;
	}

	// tested and works
	@Override
	public boolean delete(Reimbursement r) throws NoResultException{
		Session ses = HibernateUtil.getSession();
		// begin a tx
		Transaction tx = ses.beginTransaction();
		
		Query query = ses.createQuery("delete from Reimbursement where id=:id").setParameter("id", r.getId());
		int result = query.executeUpdate();
		
		tx.commit();
		return result==1;
	}

}
