package org.com.imad.Restaurant.Resources;
import java.security.Principal;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import org.com.imad.Restaurant.Entities.Account;
import org.com.imad.Restaurant.Entities.User;
import org.com.imad.Restaurant.Model.Logged;
import org.com.imad.Restaurant.Service.AccountService;
import org.com.imad.Restaurant.Service.UserService;
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRessource {
		UserService userService=new UserService();
		@GET
		public Response getAllUsers(@Context UriInfo uriInfo) {
			
			return userService.getAllUsers(uriInfo);
		}
		@GET
		@Logged
		@Path("/{username}")
		public Response getUser(@PathParam("username") String username,@Context UriInfo uriInfo,@Context SecurityContext securityContext) {
			 Principal principal = securityContext.getUserPrincipal();
		      String userName= principal.getName();
			return userService.getUser(userName, uriInfo);
			
		}
		@POST
		public Response addUser(User user,@Context UriInfo uriInfo) {
			return userService.saveUser(user, uriInfo);
		}
		@PUT
		@Logged
		@Path("/{username}")
		public Response updateUser(@PathParam("username") String username,User user,@Context SecurityContext securityContext) {
			 Principal principal = securityContext.getUserPrincipal();
		      String userName= principal.getName();
		
			return userService.updateUser(userName, user);
		}
		@DELETE
		@Logged
		@Path("/{username}")
		public Response deleteUser(@PathParam("username") String username,@Context SecurityContext securityContext) {
			Principal principal = securityContext.getUserPrincipal();
		      String userName= principal.getName();
			
			return  userService.deleteUser(userName);
		}
	
}
