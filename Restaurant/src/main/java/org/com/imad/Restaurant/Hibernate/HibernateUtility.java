package org.com.imad.Restaurant.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.com.imad.Restaurant.Entities.Account;
import org.com.imad.Restaurant.Entities.Meal;
import org.com.imad.Restaurant.Entities.Menu;
import org.com.imad.Restaurant.Entities.MenuItem;
import org.com.imad.Restaurant.Entities.Order;
import org.com.imad.Restaurant.Entities.OrderItem;
import org.com.imad.Restaurant.Entities.User;
public class HibernateUtility {
	private static StandardServiceRegistry standardRegistry;
	private static MetadataSources metaDataSources;
	private static Metadata metaData;
	private static SessionFactory sessionFactory;
	
	private HibernateUtility() {
    }
	 public static synchronized SessionFactory getSessionFactory() {

	        if (sessionFactory == null) {
	        	standardRegistry = 
	     		       new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	     	 metaDataSources = 
	     	        new MetadataSources(standardRegistry);
	     	metaDataSources.addAnnotatedClass(Menu.class);
	     	metaDataSources.addAnnotatedClass(MenuItem.class);
	     	metaDataSources.addAnnotatedClass(Meal.class);
	     	metaDataSources.addAnnotatedClass(User.class);
	     	metaDataSources.addAnnotatedClass(Account.class);
	     	metaDataSources.addAnnotatedClass(Order.class);
	     	metaDataSources.addAnnotatedClass(OrderItem.class);
	     	metaData=metaDataSources.getMetadataBuilder().build();
	     	sessionFactory=metaData.getSessionFactoryBuilder().build();
	        }
	        return sessionFactory;
	    }
	}
	
	
	

