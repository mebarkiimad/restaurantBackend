package org.com.asma.Restaurant.Resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.com.asma.Restaurant.Entities.MenuItem;
import org.com.asma.Restaurant.Service.MenuItemService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MenuItemRessource {
MenuItemService menu_item_Service=new MenuItemService();
@GET 
@Path("/{menuItemId}")
public  Response getMenuItem(@PathParam("menuId") int menuId,@PathParam("menuItemId") int menuItemId,@Context UriInfo uriInfo) {
	
	return menu_item_Service.getMenuItem(menuId, menuItemId,uriInfo);
}
@GET
public List<MenuItem> getAllMenuItems(@PathParam("menuId") int menuId){
	return menu_item_Service.getAllMenuItems(menuId);
}
@POST 
public MenuItem addMenuItem(@PathParam("menuId") int menuId, MenuItem menuItem) {
	return menu_item_Service.addMenuItem(menuId, menuItem);
}
@PUT
@Path("/{menuItemId}")
public MenuItem updateMenuItem(@PathParam("menuItemId") int menuItemId, MenuItem menuItem) {
	
	return menu_item_Service.updateMenuItem(menuItemId, menuItem);
}

@DELETE
@Path("/{menuItemId}")
public void deleteaddMenuItem(@PathParam("menuItemId") int menuItemId) {
	menu_item_Service.deleteMenuItem(menuItemId);
}

@Path("/{menuItemId}/meals")
public  MealRessource getMenuItemRessource() {
	return new MealRessource();
}
}
