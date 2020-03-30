package org.com.imad.Restaurant.Hibernate;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.com.imad.Restaurant.Entities.Meal;
import org.com.imad.Restaurant.Entities.Menu;
import org.com.imad.Restaurant.Entities.MenuItem;

public class MealDao implements Dao<Meal> {

	@Override
	public Meal get(int... id) {
		Session session=HibernateUtility.getSessionFactory().openSession();
		Meal meal =null;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   int menuItemid=id[0];
		   int mealid=id[1];
		   Query query=session.createQuery("select meal from Meal as meal inner join meal.menuItem as menuitem where meal.mealId=:mealid and menuitem.menuItemId=:menuItemid");
		   query.setParameter("menuItemid", menuItemid);
		   query.setParameter("mealid", mealid);
		   meal=(Meal)query.uniqueResult();
			tx.commit();
			session.close();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		 return meal;
		
	}

	@Override
	public List<Meal> getAll(int... id) {
		List<Meal> meals=null;
		Session session=HibernateUtility.getSessionFactory().openSession();	
		Transaction tx = null;
		try {
		tx = session.beginTransaction();
		int menuItemid=id[0];
		Query query=session.createQuery(" select meal from Meal as meal inner join meal.menuItem as mi where mi. menuItemId=:menuItemid");
		query.setParameter("menuItemid", menuItemid);
		meals=(List<Meal>)query.list();
		tx.commit();
		session.close();
	}
	catch (Exception e) {
	   if (tx!=null) tx.rollback();
	   e.printStackTrace(); 
	}finally {
	   session.close();
	}
		return meals;
		
	}

	@Override
	public Meal save(Meal t,int... id) {
		Meal meal=new Meal();
		meal.setDiscount(t.getDiscount());
		meal.setMealName(t.getMealName());
		meal.setUnitPrice(t.getUnitPrice());
		meal.setQuantity(t.getQuantity());
		meal.setOrderItems(t.getOrderItems());
		meal.setImagePath(t.getImagePath());

		MenuItem menuItem=null;
		Session session=HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   Query query=session.createQuery("from MenuItem as mi where mi.menuItemId=:menuitemid" );
		   query.setParameter("menuitemid", id[0]);
		   menuItem=(MenuItem)query.uniqueResult();
		   menuItem.getMeals().add(meal);
		   meal.setMenuItem(menuItem);
		   
		   session.update(menuItem);
		   session.save(meal);
			tx.commit();
			session.close();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return meal;
	}

	@Override
	public Meal update(Meal t, int id) {
		 Meal meal=null;
			Session session=HibernateUtility.getSessionFactory().openSession();
			Transaction tx = null;
			try {
			   tx = session.beginTransaction();
			   meal=(Meal)session.get(Meal.class, id);
			   meal.setDiscount(t.getDiscount());
				meal.setMealName(t.getMealName());
				meal.setUnitPrice(t.getUnitPrice());
				meal.setQuantity(t.getQuantity());
				meal.setOrderItems(t.getOrderItems());
				meal.setImagePath(t.getImagePath());
				
			   session.update(meal);
				tx.commit();
				session.close();
			}
			catch (Exception e) {
			   if (tx!=null) tx.rollback();
			   e.printStackTrace(); 
			}finally {
			   session.close();
			}
		
		return meal;
	}

	@Override
	public void delete(int id) {
		Session session=HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		tx = session.beginTransaction();
		Meal meal=(Meal)session.load(Meal.class, id);
		if(meal!=null) {
			session.delete(meal);
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
