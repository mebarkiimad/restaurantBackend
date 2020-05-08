package org.com.asma.Restaurant.Resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.com.asma.Restaurant.Entities.Account;
import org.com.asma.Restaurant.Entities.Meal;
import org.com.asma.Restaurant.Entities.Order;
import org.com.asma.Restaurant.Entities.OrderItem;
import org.com.asma.Restaurant.Entities.User;
import org.com.asma.Restaurant.Hibernate.AccountDao;
import org.com.asma.Restaurant.Hibernate.HibernateUtility;
import org.com.asma.Restaurant.Hibernate.MealDao;
import org.com.asma.Restaurant.Hibernate.OrderDao;
import org.com.asma.Restaurant.Hibernate.OrderItemDao;
import org.com.asma.Restaurant.Hibernate.UserDao;
import org.com.asma.Restaurant.Model.Cart;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderRessource {
	@GET
	public Order imad(){
		
		UserDao userDao=new UserDao();
		User user= userDao.getUser("coco");
		Order order=new Order(110,user,null);
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
			List<Order> orders=new ArrayList<Order>();
			orders.add(order);
		user.setOrders(orders);
		userDao.updateAccount("coco",user);
		
		Cart cart=new Cart("coco", 100, "blabla", 1, "pizaa", "burger", "description", 200);
	return order;
	}
	@POST
	public ArrayList<Cart> addOrder(ArrayList<Cart> carts) {
		UserDao userDao=new UserDao();
		MealDao mealdao=new MealDao();
		OrderDao orderDao=new OrderDao();
		OrderItemDao orderitemdao=new OrderItemDao();
		int total=0;
		
		User user=userDao.getUser(carts.get(0).getUsername());
		Order order =new Order();
		order.setUser(null);
		order.setOrderItems(null);
		int orderId=orderDao.saveOrder(order);
		
	 List<Meal> meals=new ArrayList<Meal>();
	List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for(Cart cart : carts) {
			total=cart.getPrice()+total;
			OrderItem orderitem=new OrderItem();
			orderitem.setOrder(order);
			orderitem.setQuantity(cart.getCount());
			orderitem.setUnitPrice(cart.getPrice());
			Meal meal=mealdao.getMealForOrder(cart.getMealId());
			int mealQuantity=meal.getQuantity()-cart.getCount()<0 ? 0 :meal.getQuantity()-cart.getCount();
			meal.setQuantity(mealQuantity);
			orderitem.setMeal(meal);
			orderItems.add(orderitem);
			meal.getOrderItems().add(orderitem);
			meals.add(meal);
		}
		order.setTotalAmount(total);
		user.getOrders().add(order);
	
		order.setOrderItems(orderItems);
		order.setUser(user);
		orderDao.updateOrder(order, orderId);
		for(OrderItem oi: orderItems) {
			oi.setOrder(order);
			orderitemdao.saveOrderItem(oi);
		}
     
		for(Meal m:meals) {
			mealdao.update(m, m.getMealId());
		}
		userDao.updateAccount(user.getUsername(), user);
     	
		
		
		return carts;
	}
}
