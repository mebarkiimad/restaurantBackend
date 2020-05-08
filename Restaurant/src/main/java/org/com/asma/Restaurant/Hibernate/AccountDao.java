package org.com.asma.Restaurant.Hibernate;
import java.util.List;

import org.com.asma.Restaurant.Entities.Account;
import org.com.asma.Restaurant.Entities.Menu;
import org.com.asma.Restaurant.Entities.Order;
import org.com.asma.Restaurant.Entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
public class AccountDao {
	
public Account getAccount(String username) {
	
	Session session=HibernateUtility.getSessionFactory().openSession();
	Account account =null;
	Transaction tx = null;
	try {
	   tx = session.beginTransaction();
	   account=(Account)session.get(Account.class,username);
	   
		tx.commit();
		session.close();
	}
	catch (Exception e) {
	   if (tx!=null) tx.rollback();
	   e.printStackTrace(); 
	}finally {
	   session.close();
	}
	

	 return account;
	
}
public List<Account> getAllAccounts() {
	List<Account> accounts=null;
	Session session=HibernateUtility.getSessionFactory().openSession();
	Account account =null;
	Transaction tx = null;
	try {
	   tx = session.beginTransaction();
	   Query query=session.createQuery("from Account");
	   accounts=(List<Account>)query.list();
		tx.commit();
		session.close();
	}
	catch (Exception e) {
	   if (tx!=null) tx.rollback();
	   e.printStackTrace(); 
	}finally {
	   session.close();
	}
	

	 return accounts;
	
}
public Account updateAccount(String username,Account account) {
	
	Account updateaccount=this.getAccount(username);
	updateaccount.setActivated(account.isActivated());
	updateaccount.setPassword(account.getPassword());
	Session session=HibernateUtility.getSessionFactory().openSession();
	Transaction tx = null;
	try {
	   tx = session.beginTransaction();
	   session.update(updateaccount);
		tx.commit();
		session.close();
	}
	catch (Exception e) {
	   if (tx!=null) tx.rollback();
	   e.printStackTrace(); 
	}finally {
	   session.close();
	}
	return updateaccount;
	
}

public Account saveAccount(Account account) {	
	User user=new User();
	user.setUsername(account.getUsername());
	user.setAccount(account);
	account.setUser(user);
	Session session=HibernateUtility.getSessionFactory().openSession();
	Transaction tx = null;
	try {
	   tx = session.beginTransaction();
	   session.save(user);
	   session.save(account);
		tx.commit();
		session.close();
	}
	catch (Exception e) {
	   if (tx!=null) tx.rollback();
	   e.printStackTrace(); 
	}finally {
	   session.close();
	}
	return account;
	
}

public void deleteAccount(String username) {
	Query query;
	Session session=HibernateUtility.getSessionFactory().openSession();
	Transaction tx = null;
	try {
	tx = session.beginTransaction();
	Account account=(Account)session.load(Account.class,username);
	if(account!=null) {
		session.delete(account);
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
UserDao userDao=new UserDao();
userDao.deleteUser(username);
	
	
	
	
}
public boolean existsUsername(String data) {
	Session session=HibernateUtility.getSessionFactory().openSession();
	Transaction tx = null;
	boolean exists = false;
	try {
	tx = session.beginTransaction();
	Query query = session.createQuery("from Account as account  where account.username=:data ");
	query.setMaxResults(1);
	
	 query.setParameter("data", data);
	 exists=query.uniqueResult() != null;
	tx.commit();
	session.close();
}
catch (Exception e) {
   if (tx!=null) tx.rollback();
   e.printStackTrace(); 
}finally {
   session.close();
}
	
	return exists;
}
public boolean existsEmail(String data) {
	Session session=HibernateUtility.getSessionFactory().openSession();
	Transaction tx = null;
	boolean exists = false;
	try {
	tx = session.beginTransaction();
	Query query = session.createQuery("from Account as account  where account.email=:data ");
	query.setMaxResults(1);
	
	 query.setParameter("data", data);
	 exists=query.uniqueResult() != null;
	tx.commit();
	session.close();
}
catch (Exception e) {
   if (tx!=null) tx.rollback();
   e.printStackTrace(); 
}finally {
   session.close();
}
	
	return exists;
}
public boolean existsPassword(String data) {
	Session session=HibernateUtility.getSessionFactory().openSession();
	Transaction tx = null;
	boolean exists = false;
	try {
	tx = session.beginTransaction();
	Query query = session.createQuery("from Account as account  where account.password=:data ");
	query.setMaxResults(1);
	
	 query.setParameter("data", data);
	 exists=query.uniqueResult() != null;
	tx.commit();
	session.close();
}
catch (Exception e) {
   if (tx!=null) tx.rollback();
   e.printStackTrace(); 
}finally {
   session.close();
}
	
	return exists;
}
}
