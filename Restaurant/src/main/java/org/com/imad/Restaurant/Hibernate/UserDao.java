package org.com.imad.Restaurant.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.com.imad.Restaurant.Entities.Account;
import org.com.imad.Restaurant.Entities.User;

public class UserDao {
	
	public List<User> getAllUsers(){
		List<User> users=null;
		Session session=HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   Query query=session.createQuery("from User");
		   users=(List<User>)query.list();
			tx.commit();
			session.close();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		 return users;
	}
		
	public User getUser(String username) {
		
		Session session=HibernateUtility.getSessionFactory().openSession();
		User user =null;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   user=(User)session.get(User.class,username);
			tx.commit();
			session.close();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		 return user;
		
	}
	public User saveUser(User user) {
		Session session=HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(user);
	
			tx.commit();
			session.close();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return user;
	}
	public User updateAccount(String username,User user) {
		User updateUser=this.getUser(username);
		updateUser.setFirstName(user.getFirstName());
		updateUser.setLastName(user.getLastName());
		updateUser.setCity(user.getCity());
		updateUser.setPhone(user.getPhone());
	
		updateUser.setStreet(user.getStreet());
	
		Session session=HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.update(updateUser);
			tx.commit();
			session.close();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return   updateUser;
		
	}
	public void deleteUser(String username) {
		Session session=HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		tx = session.beginTransaction();
		User user=(User)session.load(User.class,username);
		if(user!=null) {
			session.delete(user);
		}
		tx.commit();
		session.close();
	}
	catch (Exception e) {
	   if (tx!=null) tx.rollback();
	   e.printStackTrace(); 
	}finally {
	   session.close();
	}

	}
	}
	
	
	
	
	
	
	
	
	



