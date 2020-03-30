package org.com.imad.Restaurant.Service;

import java.util.List;
import java.util.Optional;

import org.com.imad.Restaurant.Entities.Meal;
import org.com.imad.Restaurant.Entities.MenuItem;
import org.com.imad.Restaurant.Hibernate.MealDao;

public class MealService {
MealDao meal_dao=new MealDao();
public Meal getMeal(int menuItemId ,int mealId) {
	
	Meal meal=meal_dao.get(menuItemId,mealId);
	return meal;
}
public List<Meal> getAllMeals(int menuItemId){
	return meal_dao.getAll(menuItemId);
			
}
public Meal addMeal(int menuItemId, Meal meal) {
	
	return meal_dao.save(meal, menuItemId);
}
public Meal updateMeal(int mealId,Meal meal) {
	return meal_dao.update(meal, mealId);
}
public void deleteMeal(int mealId) {
	meal_dao.delete(mealId);
	
}

}
