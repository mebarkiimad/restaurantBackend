package org.com.imad.Restaurant.Hibernate;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

	T get(int... id);
    
    List<T> getAll(int... id);
     
    T save(T t,int...id);
     
    T update(T t, int id);
     
    void delete(int id);	
	
	
	
}
