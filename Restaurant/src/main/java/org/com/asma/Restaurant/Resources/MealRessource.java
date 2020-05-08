package org.com.asma.Restaurant.Resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.com.asma.Restaurant.Entities.Meal;
import org.com.asma.Restaurant.Service.MealService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MealRessource {
	MealService meal_service=new MealService();
	@GET 
	@Path("/{mealId}")
public Meal getMeal(@PathParam("menuItemId") int menuItemId,@PathParam("mealId") int mealId) {
	return meal_service.getMeal(menuItemId, mealId);
}
@GET
public List<Meal> getAllMeals(@PathParam("menuItemId") int menuItemId){
	return meal_service.getAllMeals(menuItemId);
}
	
	@POST
public Meal addMeal(@PathParam("menuItemId") int menuItemId, Meal meal) {
	return meal_service.addMeal(menuItemId, meal);
}
	@PUT
	@Path("/{mealId}")
public Meal updateMeal(@PathParam("mealId") int mealId,Meal meal) {
		return meal_service.updateMeal(mealId, meal);
	}
	@DELETE
	@Path("/{mealId}")
public void deleteMeal(@PathParam("mealId") int mealId) {
		meal_service.deleteMeal(mealId);
	}
}
