package com.revature.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class UserImpl implements IUserDao{

	// tested and works
	@Override
	public int insert(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();
		int pk = (int)ses.save(u);
		return pk;
	}

	// tesetd and works
	@Override
	public List<User> findAllUsers() {
		Session ses = HibernateUtil.getSession();
		List<User> users = ses.createQuery("from User", User.class).list();
		
		return users;
	}

	// tested and works
	@Override
	public User findUserById(int id) {	
		Session ses = HibernateUtil.getSession();
		
		User user = (User)ses.createQuery("from User where id=:id").setParameter("id", id).getSingleResult();

		return user;
		
	}

	// tested and works
	@Override
	public User findUserByUsername(String username) {
		Session ses = HibernateUtil.getSession();
		
		User user = (User)ses.createQuery("from User where username=:username").setParameter("username", username).getSingleResult();

		return user;
	}

	// tested and works
	@Override
	// this will need to take in a user that already has a Id other than 0
	public boolean update(User u) { // this may or may not work
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.update(u);
		
		tx.commit();
		return true;
	}

	// tested and works
	@Override
	public boolean delete(User u) {
		Session ses = HibernateUtil.getSession();
		// begin a tx
		Transaction tx = ses.beginTransaction();
		
		Query query = ses.createQuery("delete from User where id=:id").setParameter("id", u.getId());
		int result = query.executeUpdate();
		
		tx.commit();
		return result==1;
	}

}
