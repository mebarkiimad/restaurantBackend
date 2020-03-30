package org.com.imad.Restaurant.Resources;
import java.util.List;
import javax.ws.rs.BeanParam;
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
import org.com.imad.Restaurant.Entities.Menu;
import org.com.imad.Restaurant.Service.MenuService;
import org.com.imad.Restaurant.errorhandling.DataNotFoundException;
@Path("/menus")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MenuResource {
	MenuService menuService=new MenuService();
@GET
public Response getMenus(@Context UriInfo uriInfo) {
	
	return menuService.getAllMenus(uriInfo);
	
}
@GET 
@Path("/{id}")
public Response getMenu(@PathParam("id") int id,@Context UriInfo uriInfo) {

	return menuService.getMenu(id,uriInfo);
}
@POST
 public Response addMenu(Menu menu,@Context UriInfo uriInfo) {
	return menuService.addMenu(menu,uriInfo);
}
@PUT
@Path("/{id}")
public Response updateMenu(@PathParam("id") int id, Menu menu) {
	return menuService.updateMenu(id, menu);
}

@DELETE
@Path("/{id}")
public Response deleteMenu(@PathParam("id") int id) {
	return menuService.deleteMenu(id);
}

@Path("/{menuId}/menuitems")
public  MenuItemRessource getMenuItemRessource() {
	return new MenuItemRessource();
}
}
