package org.com.imad.Restaurant.Resources;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.com.imad.Restaurant.Entities.Account;
import org.com.imad.Restaurant.Service.AccountService;

@Path("/accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountRessource {
	AccountService accountService=new AccountService();
	@GET
	public Response getAllAccounts(@Context UriInfo uriInfo){
		
		return accountService.getAllAccounts(uriInfo);
	}
	@GET
	@Path("/register")
	public Response register(@QueryParam("username") String username,
			@QueryParam("password") String password,@QueryParam("email") String email) {
		return accountService.register(username, password,email);
	}
	@GET
	@Path("/login")
	public Response login(@QueryParam("username") String username,
			@QueryParam("password") String password) {
		return accountService.login(username, password);
	}
	
	@GET
	@Path("/{username}")
	public Response getAccount(@PathParam("username") String username,@Context UriInfo uriInfo) {
		return accountService.getAccount(username, uriInfo);
		
	}
	@POST
	public Response addAccount(Account account,@Context UriInfo uriInfo) {
		return accountService.saveAccount(account, uriInfo);
	}
	@PUT
	@Path("/{username}")
	public Response updateAccount(@PathParam("username") String username,Account account) {
		return accountService.updateAccount(username, account);
	}
	@DELETE
	@Path("/{username}")
	public Response deleteAccount(@PathParam("username") String username) {
		return  accountService.deleteAccount(username);
	}
			
}
