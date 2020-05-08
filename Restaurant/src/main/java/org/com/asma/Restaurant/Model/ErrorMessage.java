package org.com.asma.Restaurant.Model;
public class ErrorMessage {
private String Message;
private int statuscode;
private String documentation;


public ErrorMessage(String message, int statuscode, String documentation) {
	Message = message;
	this.statuscode = statuscode;
	this.documentation = documentation;
}
public String getMessage() {
	return Message;
}
public void setMessage(String message) {
	Message = message;
}
public int getStatuscode() {
	return statuscode;
}
public void setStatuscode(int statuscode) {
	this.statuscode = statuscode;
}
public String getDocumentation() {
	return documentation;
}
public void setDocumentation(String documentation) {
	this.documentation = documentation;
}

}