package org.com.asma.Restaurant.Resources;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
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
import org.com.asma.Restaurant.Model.CustomOrder;
import org.com.asma.Restaurant.Model.DailyStats;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderRessource {

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
	@GET
	public Response getOrders() {
		List<CustomOrder> customorders=new ArrayList<CustomOrder>();
		String stmt = "SELECT t1.user_name,t1.street , t2.total_amount,t3.quantity,t4.createDateTime , t4.meal_name  from user as t1 JOIN orders as t2 on t1.user_name = t2.user_name JOIN orderitems t3 on t3.order_id = t2.order_id join meal as t4 where t4.meal_id = t3.meal_id";
		Session session=HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		 tx = session.beginTransaction();
		 SQLQuery query = session.createSQLQuery(stmt);
		 List<Object[]> rows = query.list();
		 for(Object[] row : rows){
			CustomOrder custom_order = new CustomOrder();
			String userName=((String) row[0]);
			String street =((String) row[1]);
			int totalAmount =((Integer) row[2]);
			int quantity = ((Integer) row[3]);
			Timestamp orderDate = ((Timestamp) row[4]);
			String mealName = ((String) row[5]);
			custom_order.setUsername(userName);
			custom_order.setStreet(street);
			custom_order.setMealName(mealName);
			custom_order.setTotalAmount(totalAmount);
			custom_order.setOrderDate(orderDate);
			custom_order.setQuantity(quantity);
			customorders.add(custom_order);
		 }
		}catch (Exception e) {
			   if (tx!=null) tx.rollback();
			   e.printStackTrace(); 
			}finally {
			   session.close();
			}
		
		
		
		 GenericEntity<List<CustomOrder>> customorders_entities = new GenericEntity<List<CustomOrder>>(customorders){};
		 return Response
			        .ok(customorders_entities)
			        .build();
		
		
	}
}
