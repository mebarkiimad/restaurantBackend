package org.com.asma.Restaurant.errorhandling;

public class DataNotFoundException extends RuntimeException{
	
public DataNotFoundException(String message) {
super(message);
}
}