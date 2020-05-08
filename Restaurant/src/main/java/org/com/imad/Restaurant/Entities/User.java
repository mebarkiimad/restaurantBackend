package org.com.imad.Restaurant.Entities;

import java.math.BigInteger;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@XmlRootElement
@Entity
@Table(name="user")
public class User implements Principal{
	@Id
	@Column(name="user_name")
	private String username;
	@Column(name="first_name")
	private String firstName;
		@Column(name="last_name")
	private String lastName;
		@Column(name="phone")
	private String phone;
		@Column(name="street")
	private String street;
		@Column(name="city")
	private String city;
		@CreationTimestamp
	    private LocalDateTime createDateTime;
	    @UpdateTimestamp
	    private LocalDateTime updateDateTime;
		@OneToOne(mappedBy = "user",fetch = FetchType.EAGER,orphanRemoval = true, cascade = CascadeType.REMOVE)
	private Account account;
		@OneToMany(mappedBy="user" ,fetch = FetchType.EAGER,orphanRemoval = true, cascade = CascadeType.REMOVE)
	private List<Order> orders;
		public User() {
			orders=new ArrayList<Order>();
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		
	
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
	
		public Account getAccount() {
			return account;
		}
		public void setAccount(Account account) {
			this.account = account;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
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
		public List<Order> getOrders() {
			return orders;
		}
		public void setOrders(List<Order> orders) {
			this.orders = orders;
		}
		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return this.getUsername();
		}
		@Override
		public String toString() {
			return "User [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", phone="
					+ phone + ", street=" + street + ", city=" + city + ", createDateTime=" + createDateTime
					+ ", updateDateTime=" + updateDateTime + ", account=" + account + ", orders=" + orders + "]";
		}
	
	
		
	
	
}
