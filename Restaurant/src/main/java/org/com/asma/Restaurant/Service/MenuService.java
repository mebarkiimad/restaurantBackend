package org.com.asma.Restaurant.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.Status;
import org.hibernate.query.Query;
import org.com.asma.Restaurant.Entities.Menu;
import org.com.asma.Restaurant.Entities.MenuItem;
import org.com.asma.Restaurant.Hibernate.MenuDao;
import org.com.asma.Restaurant.Resources.MenuResource;
import org.com.asma.Restaurant.errorhandling.DataNotFoundException;

import javassist.NotFoundException;

public class MenuService {
	private MenuDao menuDao=new MenuDao();
	public Response getMenu(int id,UriInfo uriInfo){
		Menu menu = menuDao.get(id);
if(menu==null) {
	throw new DataNotFoundException("menu with id="+id+"not found");
}
		String msgID=String.valueOf(id);
		URI uri= uriInfo.getBaseUriBuilder().path(MenuResource.class).path(Integer.toString(id)).build();
		menu.addLink(getUriForSelf(id, uriInfo), "self");
		return  Response
		        .ok(menu)
		        .location(uri)
		        .build();
		
	}
	private String getUriForSelf(int id, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder().path(MenuResource.class).path(Integer.toString(id)).build().toString();
	}
	
	public Response getAllMenus(UriInfo uriInfo){
		List<Menu> menus=menuDao.getAll();
		  GenericEntity<List<Menu>> menu_entities = new GenericEntity<List<Menu>>(menus){};
		  URI uri=uriInfo.getAbsolutePathBuilder().build();
		 
		return Response
		        .ok(menu_entities)
		        .location(uri)
		        .build();
	}
	public Response addMenu(Menu menu,UriInfo uriInfo) {
		Menu m=menuDao.save(menu);
		
		URI uri=uriInfo.getAbsolutePathBuilder().path(Integer.toString(m.getMenuId())).build();
		return Response.created(uri).entity(m).build();	
	}
	public Response deleteMenu(int id) {
		menuDao.delete(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	public Response updateMenu(int id ,Menu menuUpdate) {
		Menu menu= menuDao.update(menuUpdate, id);
		return Response.status(Response.Status.ACCEPTED).entity(menu).build();
	}
}
