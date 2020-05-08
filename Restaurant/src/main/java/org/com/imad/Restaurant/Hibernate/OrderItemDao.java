package org.com.imad.Restaurant.Hibernate;

import org.com.imad.Restaurant.Entities.Order;
import org.com.imad.Restaurant.Entities.OrderItem;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderItemDao {
	public OrderItem saveOrderItem(OrderItem orderItem) {
		Session session=HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(orderItem);
	
			tx.commit();
			session.close();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return orderItem;
	}
}
