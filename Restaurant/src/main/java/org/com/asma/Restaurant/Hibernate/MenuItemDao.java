package org.com.asma.Restaurant.Hibernate;
import java.util.List;
import java.util.Optional;

import org.com.asma.Restaurant.Entities.Menu;
import org.com.asma.Restaurant.Entities.MenuItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
public class MenuItemDao implements Dao<MenuItem> {

	@Override
	public MenuItem get(int... id) {
		Session session=HibernateUtility.getSessionFactory().openSession();
		MenuItem menuItem = null;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   int menuid=id[0];
		   int menuItemid=id[1];
		   Query query=session.createQuery("select mi from MenuItem as mi inner join mi.menu as m where mi.menuItemId=:menuItemid and m.menuId=:menuid");
		   query.setParameter("menuid", menuid);
		   query.setParameter("menuItemid", menuItemid);
		   menuItem=(MenuItem)query.uniqueResult();
			tx.commit();
			session.close();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		 return menuItem;
	}

	@Override
	public List<MenuItem> getAll(int... id) {
		List<MenuItem> menuItems=null;
		Session session=HibernateUtility.getSessionFactory().openSession();	Transaction tx = null;
		try {
		tx = session.beginTransaction();
		int menuid=id[0];
		Query query=session.createQuery(" select mi from MenuItem as mi inner join mi.menu as m where m.menuId=:menuid");
		query.setParameter("menuid", menuid);
		menuItems=(List<MenuItem>)query.list();
		tx.commit();
		session.close();
	}
	catch (Exception e) {
	   if (tx!=null) tx.rollback();
	   e.printStackTrace(); 
	}finally {
	   session.close();
	}
		return menuItems;
	}

	@Override
	public MenuItem save(MenuItem t,int... id) {
	MenuItem menuItem=new MenuItem();
	
	menuItem.setMenuItemDescription(t.getMenuItemDescription());
	menuItem.setMealType(t.getMealType());
System.out.println(t.getMenuItemDescription()+t.getMealType());
	Menu menu=null;
	Session session=HibernateUtility.getSessionFactory().openSession();
	Transaction tx = null;
	try {
	   tx = session.beginTransaction();
	   Query query=session.createQuery("from Menu as m where m.menuId=:menuid" );
	   query.setParameter("menuid", id[0]);
	   menu=(Menu)query.uniqueResult();
	   menu.getMenuItems().add(menuItem);
	   menuItem.setMenu(menu);
	   
	   session.update(menu);
	   session.save(menuItem);
		tx.commit();
		session.close();
	}
	catch (Exception e) {
	   if (tx!=null) tx.rollback();
	   e.printStackTrace(); 
	}finally {
	   session.close();
	}

	return menuItem;
	}

	@Override
	public MenuItem update(MenuItem t, int id) {
		 MenuItem menuItem=null;
		Session session=HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   menuItem=(MenuItem)session.get(MenuItem.class, id);
		   menuItem.setMenuItemDescription(t.getMenuItemDescription());
			menuItem.setMealType(t.getMealType());
		   session.update(menuItem);
			tx.commit();
			session.close();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	
		return menuItem;
	}

	@Override
	public void delete(int id) {
		
		Session session=HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		tx = session.beginTransaction();
		MenuItem menuItem=(MenuItem)session.load(MenuItem.class, id);
		if(menuItem!=null) {
			session.delete(menuItem);
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
