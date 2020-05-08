package org.com.asma.Restaurant.Model;

public class Cart {
private String username;
private int count;
private String imagePath;
private int mealId;
private String mealName;
private String mealType;
private String menuItemDescription;
private int price;
public Cart() {
	
	
}

public Cart(String username, int count, String imagePath, int mealId, String mealName, String mealType,
		String menuItemDescription, int price) {
	
	this.username = username;
	this.count = count;
	this.imagePath = imagePath;
	this.mealId = mealId;
	this.mealName = mealName;
	this.mealType = mealType;
	this.menuItemDescription = menuItemDescription;
	this.price = price;
}

public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public String getImagePath() {
	return imagePath;
}
public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
}
public int getMealId() {
	return mealId;
}
public void setMealId(int mealId) {
	this.mealId = mealId;
}
public String getMealName() {
	return mealName;
}
public void setMealName(String mealName) {
	this.mealName = mealName;
}
public String getMealType() {
	return mealType;
}
public void setMealType(String mealType) {
	this.mealType = mealType;
}
public String getMenuItemDescription() {
	return menuItemDescription;
}
public void setMenuItemDescription(String menuItemDescription) {
	this.menuItemDescription = menuItemDescription;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}

}
