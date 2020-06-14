package org.com.asma.Restaurant.Model;

import java.sql.Timestamp;

public class CustomOrder {
private String username;
private String street;
private String mealName;
private int  totalAmount;
private int quantity;
private Timestamp orderDate;
 public CustomOrder() {
	// TODO Auto-generated constructor stub
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}
public String getMealName() {
	return mealName;
}
public void setMealName(String mealName) {
	this.mealName = mealName;
}
public int getTotalAmount() {
	return totalAmount;
}
public void setTotalAmount(int totalAmount) {
	this.totalAmount = totalAmount;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public Timestamp getOrderDate() {
	return orderDate;
}
public void setOrderDate(Timestamp orderDate) {
	this.orderDate = orderDate;
}


 

}
