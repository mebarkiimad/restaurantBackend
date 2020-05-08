package org.com.asma.Restaurant.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.com.asma.Restaurant.Entities.Account;
import org.com.asma.Restaurant.Hibernate.AccountDao;
import org.com.asma.Restaurant.Model.Logged;


@Provider
@Logged
@Priority(Priorities.AUTHENTICATION)
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter{
	private static final String REALM="exemple";
	private static final String AUTHENTICATION_SCHEME = "Basic";
	@Override
	public void filter(ContainerRequestContext  reqContext, ContainerResponseContext resContext) throws IOException {   
  
	}
	@Override
	public void filter(ContainerRequestContext reqContext) throws IOException {
	     String authorizationHeader =reqContext.getHeaderString(HttpHeaders.AUTHORIZATION);
	     System.out.println(authorizationHeader);
	     if(!validateTokenBasedAuthentification(authorizationHeader)) {
        	 abortWithUnauthorized(reqContext);
        	 return;
         }
         String authToken=getAuthToken(authorizationHeader);

         byte[] decodedBytes = Base64.getDecoder().decode(authToken);
         String decodeAuthToken = new String(decodedBytes);
         StringTokenizer tokenizer=new StringTokenizer(decodeAuthToken,":");
         String username=tokenizer.nextToken();
         String password=tokenizer.nextToken();
      /*  */
         boolean exist=isUserValide(username,password);
         if(exist==false){
         	reqContext.abortWith(
 	                Response.status(Response.Status.UNAUTHORIZED)
 	                        .build());}
         
         CostumSecurityContext mySecurityContext=new CostumSecurityContext();
         mySecurityContext.setUsername(username);
         reqContext.setSecurityContext(mySecurityContext);  
	}
	
	   private void abortWithUnauthorized(ContainerRequestContext requestContext) {

	        // Abort the filter chain with a 401 status code response
	        // The WWW-Authenticate header is sent along with the response
	        requestContext.abortWith(
	                Response.status(Response.Status.UNAUTHORIZED)
	                        .header(HttpHeaders.WWW_AUTHENTICATE, 
	                                AUTHENTICATION_SCHEME + " realm=<" + REALM + ">")
	                        .build());
	    }
public boolean isUserValide(String uname,String pword) {
AccountDao accountDao=new AccountDao();
Account account=accountDao.getAccount(uname);
return account!=null && account.getPassword().equals(pword)?true:false;
}
	private String getAuthToken(String authorizationHeader) {
		return authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();
	}

	public boolean validateTokenBasedAuthentification(String  authToken) {
		if(authToken!=null && authToken.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase()+"")) {
			return true;
		}else {
		return false;}}}
	
