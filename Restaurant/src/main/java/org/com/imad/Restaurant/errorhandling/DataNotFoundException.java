package org.com.imad.Restaurant.errorhandling;

public class DataNotFoundException extends RuntimeException{
	
public DataNotFoundException(String message) {
super(message);
}
}