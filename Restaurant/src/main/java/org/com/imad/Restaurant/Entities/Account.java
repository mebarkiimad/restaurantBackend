package org.com.imad.Restaurant.Entities;

import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@XmlRootElement
@Entity
@Table(name="account")
public class Account {
	@Id
	@Column(name="user_name")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name="email")
	private String email;
	@Column(name = "status")
	private boolean activated;
	@CreationTimestamp
	private LocalDateTime createDateTime;
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	@XmlTransient
	@JsonbTransient
	@JoinColumn(name="user_name")
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private User user;
public Account() {}


public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public boolean isActivated() {
	return activated;
}

public void setActivated(boolean activated) {
	this.activated = activated;
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

}
