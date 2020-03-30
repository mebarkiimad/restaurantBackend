 package org.com.imad.Restaurant.Service;

import java.net.URI;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


import org.com.imad.Restaurant.Entities.Account;
import org.com.imad.Restaurant.Entities.Menu;
import org.com.imad.Restaurant.Hibernate.AccountDao;
import org.com.imad.Restaurant.Model.CredentialExist;



public class AccountService {
AccountDao accountDao=new AccountDao();
public Response getAccount(String username,UriInfo uriInfo) {
	Account account= accountDao.getAccount(username);
	URI uri=uriInfo.getAbsolutePathBuilder().build();
	return Response
	        .ok(account)
	        .location(uri)
	        .build();
}
public Response deleteAccount(String username) {
	accountDao.deleteAccount(username);
	return Response.status(Response.Status.NO_CONTENT).build();
}
public Response saveAccount(Account account,UriInfo uriInfo){
	
	Account createdAccount=null;
	URI uri=null;
	createdAccount= accountDao.saveAccount(account);
	uri=uriInfo.getAbsolutePathBuilder().path(account.getUsername()).build();
	return Response.created(uri).entity(createdAccount).build();	
}
public Response updateAccount(String username,Account account) {
	Account accountEntity= accountDao.updateAccount(username,account);
	
	return Response.status(Response.Status.ACCEPTED).entity(accountEntity).build();
}

public Response getAllAccounts(UriInfo uriInfo){
	
	
	List<Account> accounts= accountDao.getAllAccounts();
	GenericEntity<List<Account>> accounts_entities = new GenericEntity<List<Account>>(accounts){};
	URI uri=uriInfo.getAbsolutePathBuilder().build();
	return Response
	        .ok(accounts_entities)
	        .location(uri)
	        .build();
}
public Response register(String username,String password,String email) {
	CredentialExist credentialExist=new CredentialExist();
	credentialExist.setUsernameExist(accountDao.existsUsername(username));
	credentialExist.setPasswordExist(accountDao.existsPassword(password));
	credentialExist.setEmailExist(accountDao.existsEmail(email));
	return Response.status(Response.Status.OK).entity(credentialExist).build();
}
public Response login(String username ,String password) {
	CredentialExist credentialExist=new CredentialExist();
	if(accountDao.existsUsername(username)) {
		credentialExist.setUsernameExist(true);
		Account account=accountDao.getAccount(username);
		if(account.getPassword().equals(password)) {
			credentialExist.setPasswordExist(true);
		}
	}
		
		return Response.status(Response.Status.OK).entity(credentialExist).build();
	
}

}
