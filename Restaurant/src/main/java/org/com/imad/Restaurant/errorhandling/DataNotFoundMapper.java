package org.com.imad.Restaurant.errorhandling;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.com.imad.Restaurant.Model.ErrorMessage;
@Provider
public class DataNotFoundMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage error=new ErrorMessage(ex.getMessage(),404,"cloudcry4ever@gmail.com");
		return Response.status(404).entity(error).build();
	}

}
