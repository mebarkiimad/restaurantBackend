package org.com.imad.Restaurant.Service;

import java.net.URI;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import org.com.imad.Restaurant.Entities.Account;
import org.com.imad.Restaurant.Entities.User;
import org.com.imad.Restaurant.Hibernate.UserDao;
import org.com.imad.Restaurant.Model.CredentialExist;
import org.com.imad.Restaurant.errorhandling.DataNotFoundException;

public class UserService {
UserDao userDao=new UserDao();
public Response getUser(String username,UriInfo uriInfo) {
	User user= userDao.getUser(username);
	if(user==null) {
		throw new DataNotFoundException("the user "+user+"not found");
	}
	URI uri=uriInfo.getAbsolutePathBuilder().build();
	return Response
	        .ok(user)
	        .location(uri)
	        .build();
}
public Response deleteUser(String username) {
	userDao.deleteUser(username);
	return Response.status(Response.Status.NO_CONTENT).build();
}
public Response saveUser(User user,UriInfo uriInfo) {
	URI uri=uriInfo.getAbsolutePathBuilder().path(user.getUsername()).build();
	
	List<User> users= userDao.getAllUsers();

	

	User createdUser=userDao.saveUser(user);


return Response.created(uri).entity(createdUser).build();
}
public Response updateUser(String username,User user) {
	User updatedUser= userDao.updateAccount(username,user);
	return Response.status(Response.Status.ACCEPTED).entity(updatedUser).build();
}

public Response getAllUsers(UriInfo uriInfo){
	
	List<User> users= userDao.getAllUsers();
	GenericEntity<List<User>> users_entities = new GenericEntity<List<User>>(users){};
	URI uri=uriInfo.getAbsolutePathBuilder().build();
	return Response
	        .ok(users_entities)
	        .location(uri)
	        .build();
}


}
