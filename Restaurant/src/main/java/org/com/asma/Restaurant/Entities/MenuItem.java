package org.com.asma.Restaurant.Entities;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.UpdateTimestamp;
@XmlRootElement
@Entity
@Table(name="menuitem")
public class MenuItem {
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "menuitem_id", updatable = false, nullable = false)
	@Id
private int menuItemId;
	@Column(name = "description")
private String menuItemDescription;// if it is pizza description can be "best italian pizza"
	@Column(name = "meal_type")
private String mealType;//it can be pizza, tacos, sandwitch,burger
	@CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
@XmlTransient
@JsonbTransient
@ManyToOne
@JoinColumn(name="menu_id")
private Menu menu;
@OneToMany(mappedBy="menuItem" ,fetch = FetchType.EAGER)
private List<Meal> meals;

public MenuItem() {
	meals=new ArrayList<Meal>();
	
}
public MenuItem(int menuItemId, String description, String mealType, Menu menu) {
	this.menuItemId = menuItemId;
	description = description;
	this.mealType = mealType;
	this.menu = menu;
	meals=new ArrayList<Meal>();
}
public int getMenuItemId() {
	return menuItemId;
}
public void setMenuItemId(int menuItemId) {
	this.menuItemId = menuItemId;
}


public String getMenuItemDescription() {
	return menuItemDescription;
}
public void setMenuItemDescription(String menuItemDescription) {
	this.menuItemDescription = menuItemDescription;
}
public List<Meal> getMeals() {
	return meals;
}
public void setMeals(List<Meal> meals) {
	this.meals = meals;
}
public String getMealType() {
	return mealType;
}
public void setMealType(String mealType) {
	this.mealType = mealType;
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
public Menu getMenu() {
	return menu;
}
public void setMenu(Menu menu) {
	this.menu = menu;
}

}
