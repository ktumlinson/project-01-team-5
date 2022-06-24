package com.revature.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.util.HibernateUtil;

public class UserDaoImpl implements IUserDao{
	
	public static HashMap<UserRole, Integer> map = new HashMap<>();
	{
		map.put(UserRole.EMPLOYEE, 0);
		map.put(UserRole.MANAGER, 1);
	}

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
		Session ses = HibernateUtil.getSession();
		return (User)ses.createQuery("from User where id=:id")
				.setParameter("id", id).getSingleResult();
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
		User current = new User();
		current = (User) ses.merge(u.getId());
		tx.commit();
		// if the user and current have the same values then return true
		return current.getId() != 0;
	}

	@Override
	public boolean delete(User u) {
		if(u.getId() == 0) {
			return false;
		}
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();
		
		ses.remove(u);
		tx.commit();
		return true;
	}

}
