package se.cosaq.service;

import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.cosaq.db.ProductDB;
import se.cosaq.domain.Product;

@Path("product")
public class ProductResourceService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{id}")
	public String getProductById(@PathParam("id") int id) {
		// TO-DO
		Product prd = Product.getProductById(id);
		if (prd!=null) {
			return "Got product [" + id + "]=" + prd.getName();
		}
		else 
			throw new WebApplicationException(Response.Status.NOT_FOUND);
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllProducts() {
		// TO-DO
		List<Product> allPrd = Product.getAllProducts();
		String s="";
		Iterator<Product> iter = allPrd.iterator();
		while (iter.hasNext()) {
			Product prd = iter.next();
			s= s + "\n" + "Got product [" + prd.getId() + "]=" + prd.getName();
		}
		return s;
	}
}
