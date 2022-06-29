package com.revature.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.models.EUserRole;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class UserRoleDao implements IUserRoleDao {

	@Override
	public int insert(EUserRole r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();
		int pk = (int)ses.save(r);
		tx.commit();
		return pk;
	}

	@Override
	public List<EUserRole> findAllRoles() {
		Session ses = HibernateUtil.getSession();
		List<EUserRole> users = ses.createQuery("from EUserRole", EUserRole.class).list();
		
		return users;
	}

	@Override
	public EUserRole findRoleById(int id) {
		Session ses = HibernateUtil.getSession();
		
		EUserRole user = (EUserRole)ses.createQuery("from EUserRole where id=:id").setParameter("id", id).getSingleResult();

		return user;
	}

	@Override
	public boolean update(EUserRole r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.update(r);
		
		tx.commit();
		return true;
	}

	@Override
	public boolean delete(EUserRole r) {
		Session ses = HibernateUtil.getSession();
		// begin a tx
		Transaction tx = ses.beginTransaction();
		
		Query query = ses.createQuery("delete from EUserRole where id=:id").setParameter("id", r.getId());
		int result = query.executeUpdate();
		
		tx.commit();
		return result==1;
	}

	

	

}
