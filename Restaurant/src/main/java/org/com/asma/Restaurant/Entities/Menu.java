package org.com.asma.Restaurant.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.com.asma.Restaurant.Model.Link;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.UpdateTimestamp;

@XmlRootElement
@Entity
@Table(name="menu")

public class Menu {
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "menu_id", updatable = false, nullable = false)
	@Id 
	private int menuId;
	@Column(name = "menu_name")
private String menuName;
	@Column(name = "description")
private String Description;// fast food delicious snacks :)
	@CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

@OneToMany(mappedBy="menu" ,fetch = FetchType.EAGER)
private List<MenuItem> menuItems;
@Transient
private List<Link> links=new ArrayList<Link>();
public Menu() {
	menuItems=new ArrayList<MenuItem>();
}
public Menu(String description,String menuName, List<MenuItem> menuItems) {
	
	Description = description;
	
	menuItems=new ArrayList<MenuItem>();
	this.menuItems = menuItems;
	this.menuName=menuName;
}

public int getMenuId() {
	return menuId;
}
public void setMenuId(int menuId) {
	this.menuId = menuId;
}
public String getMenuName() {
	return menuName;
}
public void setMenuName(String menuName) {
	this.menuName = menuName;
}
public String getDescription() {
	return Description;
}
public void setDescription(String description) {
	Description = description;
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
public List<MenuItem> getMenuItems() {
	return menuItems;
}
public void setMenuItems(List<MenuItem> menuItems) {
	this.menuItems = menuItems;
}
@Transient
public List<Link> getLinks() {
	return links;
}
public void setLinks(List<Link> links) {
	this.links = links;
}

public void addLink(String url,String rel) {
	Link link=new Link();
	link.setLink(url);
	link.setRel(rel);
	this.getLinks().add(link);
	
}
@Override
public String toString() {
	return "Menu [menuId=" + menuId + ", menuName=" + menuName + ", Description=" + Description + ", createDateTime="
			+ createDateTime + ", updateDateTime=" + updateDateTime + ", menuItems=" + menuItems + "]";
}




}