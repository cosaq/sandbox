package se.cosaq.service;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Product")
public class ProductResourceService implements ProductResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		//TO-DO
		return "Got PRODUCT! ";
	}

}
