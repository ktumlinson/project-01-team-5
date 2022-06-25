package com.revature.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.models.UserEmp;

import com.revature.util.HibernateUtil;

public class UserDaoImpl implements IUserDao{
	


	@Override
	public int insert(UserEmp u) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();
		int pk = (int) ses.save(u);
		tx.commit();
		return pk;
	}

	@Override
	public List<UserEmp> findAllUsers() {
		Session ses = HibernateUtil.getSession();
		return ses.createQuery("from User", UserEmp.class).list();
	}

	@Override
	public UserEmp findUserById(int id) {
		Session ses = HibernateUtil.getSession();
		return (UserEmp)ses.createQuery("from User where id=:id")
				.setParameter("id", id).getSingleResult();
	}

	@Override
	public UserEmp findUserByUsername(String username) {
		Optional<UserEmp> foundUser = findAllUsers().stream()
				.filter((u -> u.getUsername() == username))
				.findFirst();
		return (foundUser.isPresent() ? foundUser.get() : new UserEmp());
	}

	@Override
	// this will need to take in a user that already has a Id other than 0
	public boolean update(UserEmp u) { // this may or may not work
		if(u.getId() == 0) {
			// throw an exception here for an unfound user if we have time to make exceptions
			return false;
		}
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();
		UserEmp current = new UserEmp();
		current = (UserEmp) ses.merge(u.getId());
		// if the user and current have the same values then return true
		return current.getId() != 0;
	}

	@Override
	public boolean delete(UserEmp u) {
		if(u.getId() == 0) {
			return false;
		}
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();
		
		ses.remove(u);
		return true;
	}

}
