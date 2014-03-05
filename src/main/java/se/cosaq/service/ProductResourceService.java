package se.cosaq.service;


import javax.ws.rs.Path;

@Path("/Product")
public class ProductResourceService implements ProductResource {

	public String getIt() {
		//TO-DO
		return "Got PRODUCT! Nu utan annotations. Nästan";
	}

}
