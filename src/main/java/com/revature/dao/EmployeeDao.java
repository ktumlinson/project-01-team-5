package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDao {
	
	public int insert(Employee e) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		tx.commit();
		return (int) ses.save(e);
	}
	
	public List<Employee> findAll(){
		Session ses = HibernateUtil.getSession();
		List<Employee> employees = ses.createQuery("from Employee", Employee.class).list();
		return employees;
	}
	
	public boolean update() {
		return false;
	}
	
	public boolean delete(Employee e) {
		return false;
	}
}
