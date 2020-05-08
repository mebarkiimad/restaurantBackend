package org.com.asma.Restaurant.Model;

public class CredentialExist {
boolean usernameExist;
boolean passwordExist;
boolean emailExist;

public boolean isPasswordExist() {
	return passwordExist;
}
public void setPasswordExist(boolean passwordExist) {
	this.passwordExist = passwordExist;
}

public CredentialExist(boolean usernameExist, boolean passwordExist, boolean emailExist) {
	
	this.usernameExist = usernameExist;
	this.passwordExist = passwordExist;
	this.emailExist = emailExist;
}
public CredentialExist() {
	this.emailExist=false;
	this.passwordExist=passwordExist;
	this.usernameExist=false;
}
public boolean isUsernameExist() {
	return usernameExist;
}
public void setUsernameExist(boolean usernameExist) {
	this.usernameExist = usernameExist;
}

public boolean isEmailExist() {
	return emailExist;
}
public void setEmailExist(boolean emailExist) {
	this.emailExist = emailExist;
}



}
