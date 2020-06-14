package org.com.asma.Restaurant.Entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Entity
@Table(name="meal")
@XmlRootElement
public class Meal {
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "meal_id", updatable = false, nullable = false)
	@Id 
private int mealId;
	@Column(name="quantity")
private int quantity; // 20 snacks ready for delivery
	@Column(name="meal_name")
private String mealName;// pizza with meat or pizza with shrooms or pizza with cheese...
	@Column(name="price")
private int unitPrice; // 2$ or 1$
	@Column(name="discount")
private int discount; // it can be percentage 10%
private String imagePath;
@CreationTimestamp
private LocalDateTime createDateTime;
@UpdateTimestamp
private LocalDateTime updateDateTime;
@ManyToOne
@JoinColumn(name="menuitem_id")
@JsonbTransient
@XmlTransient
private MenuItem menuItem;
@XmlTransient
@JsonbTransient
@OneToMany(mappedBy="meal" ,fetch = FetchType.EAGER)
private List<OrderItem> orderItems;
public Meal() {}

public Meal(int quantity, String mealName, int  unitPrice, int discount, MenuItem menuItem) {
	this.quantity = quantity;
	this.mealName = mealName;
	this.unitPrice =unitPrice;
	this.discount = discount;
	this.menuItem = menuItem;
}


@XmlTransient
public List<OrderItem> getOrderItems() {
	return orderItems;
}

public void setOrderItems(List<OrderItem> orderItems) {
	this.orderItems = orderItems;
}

public int getMealId() {
	return mealId;
}

public void setMealId(int mealId) {
	this.mealId = mealId;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public String getMealName() {
	return mealName;
}

public void setMealName(String mealName) {
	this.mealName = mealName;
}



public int getDiscount() {
	return discount;
}

public void setDiscount(int discount) {
	this.discount = discount;
}

public LocalDateTime getCreateDateTime() {
	return createDateTime;
}

public void setCreateDateTime(LocalDateTime createDateTime) {
	this.createDateTime = createDateTime;
}

public LocalDateTime getUpdateDateTime() {
	return updateDateTime;
}

public void setUpdateDateTime(LocalDateTime updateDateTime) {
	this.updateDateTime = updateDateTime;
}

public MenuItem getMenuItem() {
	return menuItem;
}

public void setMenuItem(MenuItem menuItem) {
	this.menuItem = menuItem;
}

public int getUnitPrice() {
	return unitPrice;
}

public void setUnitPrice(int unitPrice) {
	this.unitPrice = unitPrice;
}

public String getImagePath() {
	return imagePath;
}

public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
}

@Override
public String toString() {
	return "Meal [mealId=" + mealId + ", quantity=" + quantity + ", mealName=" + mealName + ", unitPrice=" + unitPrice
			+ ", discount=" + discount + ", imagePath=" + imagePath + ", createDateTime=" + createDateTime
			+ ", updateDateTime=" + updateDateTime + ", menuItem=" + menuItem + ", orderItems=" + orderItems + "]";
}

}





