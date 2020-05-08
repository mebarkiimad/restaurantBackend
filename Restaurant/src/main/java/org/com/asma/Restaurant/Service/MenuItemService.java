package org.com.asma.Restaurant.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.com.asma.Restaurant.Entities.Menu;
import org.com.asma.Restaurant.Entities.MenuItem;
import org.com.asma.Restaurant.Hibernate.MenuItemDao;
import org.com.asma.Restaurant.Resources.MenuItemRessource;
import org.com.asma.Restaurant.Resources.MenuResource;

public class MenuItemService {
	
	MenuItemDao menu_item_dao=new MenuItemDao();
	
	
	public Response getMenuItem(int menuId ,int menuItemId,UriInfo uriInfo) {
		URI uri= uriInfo.getBaseUriBuilder().path(MenuResource.class)
				.path(Integer.toString(menuId))
				.path(MenuItemRessource.class)
				.path(Integer.toString(menuItemId)).build();
		MenuItem menuitem= menu_item_dao.get(menuId,menuItemId);
		
		return  Response
		        .ok(menuitem)
		        .location(uri)
		        .build();
	}
	
	public List<MenuItem> getAllMenuItems(int menuId) {
		return menu_item_dao.getAll(menuId);
	}
	
	public MenuItem addMenuItem(int menuId, MenuItem menuItem) {
		return menu_item_dao.save(menuItem,menuId);
		
	}
	public MenuItem updateMenuItem(int menuItemId, MenuItem menuItem) {
		return menu_item_dao.update(menuItem,  menuItemId);
	}
	public void deleteMenuItem(int id) {
		
		menu_item_dao.delete(id);
	}
	
	
}
