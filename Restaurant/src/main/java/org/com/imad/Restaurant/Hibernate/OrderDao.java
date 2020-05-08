package org.com.imad.Restaurant.Hibernate;

import org.com.imad.Restaurant.Entities.Order;
import org.com.imad.Restaurant.Entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderDao {
	public int saveOrder(Order order) {
		Session session=HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(order);
		   
	
			tx.commit();
			session.close();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return order.orderId;
	}
	public Order updateOrder(Order order,int id) {
		Session session=HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			   tx = session.beginTransaction();
			   session.update(order);
				tx.commit();
				session.close();
			}
			catch (Exception e) {
			   if (tx!=null) tx.rollback();
			   e.printStackTrace(); 
			}finally {
			   session.close();
			}
		return order;
			
	}

}
