package org.com.asma.Restaurant.Hibernate;
import java.util.List;
import java.util.Optional;

import org.com.asma.Restaurant.Entities.Menu;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
public class MenuDao implements Dao<Menu> {
	
	@Override
	public Menu get(int... id) {
			Session session=HibernateUtility.getSessionFactory().openSession();
		Menu menu =null;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   menu=(Menu)session.get(Menu.class,id[0]);
			tx.commit();
			session.close();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		
	
		 return menu;
	}

	@Override
	public List<Menu> getAll(int... id) {
		List<Menu> menus=null;
		Session session=HibernateUtility.getSessionFactory().openSession();	Transaction tx = null;
		try {
		tx = session.beginTransaction();
		Query query=session.createQuery("from Menu");
	    menus=(List<Menu>)query.list();
	
		tx.commit();
		session.close();
	}
	catch (Exception e) {
	   if (tx!=null) tx.rollback();
	   e.printStackTrace(); 
	}finally {
	   session.close();
	}
		return menus;
	}

	@Override
	public Menu save(Menu t,int... id) {
		Menu menu =new Menu();
		menu.setDescription(t.getDescription());
		menu.setMenuName(t.getMenuName());
		menu.setMenuItems(null);
		Session session=HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(menu);
		  
			tx.commit();
			session.close();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return menu;
	}

	@Override
	public Menu update(Menu t, int id) {
		Menu m=this.get(id);
		m.setDescription(t.getDescription());
		m.setMenuName(t.getMenuName());
		Session session=HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.update(m);
			tx.commit();
			session.close();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return m;
	}

	@Override
	public void delete(int id) {
		Session session=HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		tx = session.beginTransaction();
		Menu menu=(Menu)session.load(Menu.class, id);
		if(menu!=null) {
			session.delete(menu);
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
