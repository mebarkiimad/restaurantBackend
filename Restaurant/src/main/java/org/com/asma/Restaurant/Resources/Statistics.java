package org.com.asma.Restaurant.Resources;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.com.asma.Restaurant.Entities.Menu;
import org.com.asma.Restaurant.Entities.MenuItem;
import org.com.asma.Restaurant.Entities.Order;
import org.com.asma.Restaurant.Hibernate.HibernateUtility;
import org.com.asma.Restaurant.Model.DailyStats;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Path("/statistics")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Statistics {
@GET
@Path("/{id}")
public Long getCount(@PathParam("id") int id) {
	Long  count=(long) 0;

	Session session=HibernateUtility.getSessionFactory().openSession();
	Transaction tx = null;
	switch(id) 
	{
	case 1://count total number of meals 
		
		
		try {
			 tx = session.beginTransaction();
			   Query query=session.createQuery("select count(m) from Meal as m" );
			  count=(Long) query.uniqueResult();
			
		}catch (Exception e) {
			   if (tx!=null) tx.rollback();
			   e.printStackTrace(); 
			}finally {
			   session.close();
			}
		
		
		break;
	case 2://count total number of orders
		 session=HibernateUtility.getSessionFactory().openSession();
		 tx = null;
		try {
			 tx = session.beginTransaction();
			   Query query=session.createQuery("select count(o) from Order as o" );
			   count=(Long) query.uniqueResult();
			
		}catch (Exception e) {
			   if (tx!=null) tx.rollback();
			   e.printStackTrace(); 
			}finally {
			   session.close();
			}
	
		break;
	case 3://count total numbers of users
		 session=HibernateUtility.getSessionFactory().openSession();
		 tx = null;
		try {
			 tx = session.beginTransaction();
			   Query query=session.createQuery("select count(u) from User as u" );
			   count=(Long) query.uniqueResult();
			
		}catch (Exception e) {
			   if (tx!=null) tx.rollback();
			   e.printStackTrace(); 
			}finally {
			   session.close();
			}
				break;
	case 4://calculate average income $
		session=HibernateUtility.getSessionFactory().openSession();
		 tx = null;
		try {
			 tx = session.beginTransaction();
			   Query query=session.createQuery("select avg(o.totalAmount) from Order as o" );
			   count=new Double((double) query.uniqueResult()).longValue();
			
		}catch (Exception e) {
			   if (tx!=null) tx.rollback();
			   e.printStackTrace(); 
			}finally {
			   session.close();
			}
		
		break;
	}
	return count;
}
@GET
public Response getStatistics(@QueryParam("queryparam") String queryparam){
	if(queryparam.equals("lineChart")) {
String stmt="SELECT createDateTime,avg(total_amount) as average from orders GROUP BY createDateTime";

	List<DailyStats> dailystats=new ArrayList<DailyStats>();
	Session session=HibernateUtility.getSessionFactory().openSession();
	Transaction tx = null;
	try {
	 tx = session.beginTransaction();
	 SQLQuery query = session.createSQLQuery(stmt);
	 List<Object[]> rows = query.list();
	 for(Object[] row : rows){
	DailyStats dailyStats=new DailyStats();
	 Timestamp ts=(Timestamp) row[0]; 
     Date date=new Date(ts.getTime());
     Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
     cal.setTime(date);
     SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
     String formatted = format1.format(cal.getTime());
	dailyStats.setToday(formatted);
	dailyStats.setTodayAvgIncome(Double.parseDouble(row[1].toString()));
	dailystats.add(dailyStats);
	 }
	}catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	 GenericEntity<List<DailyStats>> stats_entities = new GenericEntity<List<DailyStats>>(dailystats){};
	 return Response
		        .ok(stats_entities)
		        .build();
	}else if(queryparam.equals("pieChart")){
		Session session=HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		List<Object[]> rows = null;
		String stmt="SELECT PT.meal_type, COUNT(CT.menuitem_id) Child_Count FROM menuitem PT JOIN meal CT ON PT.menuitem_id = CT.menuitem_id GROUP BY PT.meal_type";
		try {
			 tx = session.beginTransaction();
			 SQLQuery query = session.createSQLQuery(stmt);
			  rows = query.list();
		}catch (Exception e) {
			   if (tx!=null) tx.rollback();
			   e.printStackTrace(); 
			}finally {
			   session.close();
			}
		 return Response
			        .ok(rows)
			        .build();
}
	return null;
}

}
