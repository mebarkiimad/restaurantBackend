package org.com.imad.Restaurant.Entities;

import java.time.LocalDateTime;
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
@Table(name="orders")
@XmlRootElement
public class Order {
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "order_id", updatable = false, nullable = false)
	@Id 
public int orderId;
	@Column(name = "total_amount")
public int totalAmount;
	@CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    @XmlTransient
    @JsonbTransient
	@ManyToOne
	@JoinColumn(name="user_name")
	private User user;
	@OneToMany(mappedBy="order" ,fetch = FetchType.EAGER)
	private List<OrderItem> orderItems;
public Order() {}
public int getOrderId() {
	return orderId;
}
public void setOrderId(int orderId) {
	this.orderId = orderId;
}
public int getTotalAmount() {
	return totalAmount;
}
public void setTotalAmount(int totalAmount) {
	this.totalAmount = totalAmount;
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
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public List<OrderItem> getOrderItems() {
	return orderItems;
}
public void setOrderItems(List<OrderItem> orderItems) {
	this.orderItems = orderItems;
}

}
