package org.com.asma.Restaurant.Model;

public class PieStats {
private String mealType;
private int mealCount;
public PieStats() {
	// TODO Auto-generated constructor stub
}
public PieStats(String mealType, int mealCount) {

	this.mealType = mealType;
	this.mealCount = mealCount;
}
public String getMealType() {
	return mealType;
}
public void setMealType(String mealType) {
	this.mealType = mealType;
}
public int getMealCount() {
	return mealCount;
}
public void setMealCount(int mealCount) {
	this.mealCount = mealCount;
}

}
