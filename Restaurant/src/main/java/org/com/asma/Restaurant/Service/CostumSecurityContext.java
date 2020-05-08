package org.com.asma.Restaurant.Service;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

public class CostumSecurityContext implements SecurityContext{
private String username;

	public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

	@Override
	public String getAuthenticationScheme() {
		// TODO Auto-generated method stub
		return "Basic";
	}

	@Override
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return new Principal() {
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return username;
			}
		};
	}

	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return this.isSecure();
	}

	@Override
	public boolean isUserInRole(String arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
