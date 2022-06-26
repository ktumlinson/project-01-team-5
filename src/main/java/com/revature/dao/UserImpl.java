package com.revature.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class UserImpl implements IUserDao{

	@Override
	public int insert(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();
		tx.commit();
		return (int) ses.save(u);
	}

	@Override
	public List<User> findAllUsers() {
		Session ses = HibernateUtil.getSession();
		return ses.createQuery("from User", User.class).list();
	}

	@Override
	public User findUserById(int id) {
		Optional<User> foundUser = findAllUsers().stream()
				.filter((u -> u.getId() == id))
				.findFirst();
		return (foundUser.isPresent() ? foundUser.get() : new User());
	}

	@Override
	public User findUserByUsername(String username) {
		Optional<User> foundUser = findAllUsers().stream()
				.filter((u -> u.getUsername() == username))
				.findFirst();
		return (foundUser.isPresent() ? foundUser.get() : new User());
	}

	@Override
	// this will need to take in a user that already has a Id other than 0
	public boolean update(User u) { // this may or may not work
		if(u.getId() == 0) {
			// throw an exception here for an unfound user if we have time to make exceptions
			return false;
		}
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();
		ses.merge(u.getId());
		User current = findUserById(u.getId());
		tx.commit();
		
		// if the user and current have the same values then return true
		return (u.toString().equals(current.toString()) ? true : false);
	}

	@Override
	public boolean delete(User u) {
		if(u.getId() == 0) {
			return false;
		}
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();
		ses.remove(u.getId());
		tx.commit();
		User current = findUserById(u.getId());
		
		return (current.getId() == 0 ? true : false);
	}

}