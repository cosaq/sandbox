package se.cosaq.service;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import se.cosaq.domain.Part;
import se.cosaq.domain.Product;
import se.cosaq.schema.product.ObjectFactory;

@Path("product")
public class ProductResourceService {

	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("{id}")
	public void updateProduct(@PathParam("id") int id, se.cosaq.schema.product.Product prod) {
		if (prod!=null)
			System.out.println("Updating product [" + prod.getId() + "]=" + prod.getName());
		else 
			System.out.println("Updating product. prod is null.");
	}

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public se.cosaq.schema.product.Product createProduct(se.cosaq.schema.product.Product prod) {
		
		// TO-DO create a domain object and save it.
		
		int newId=999;
		prod.setId(newId);
		System.out.println("Creating product [" + prod.getId() + "]=" + prod.getName());
		
		return prod;

	}


	@GET
	@Produces({ MediaType.APPLICATION_XML })
	@Path("{id}")
	public JAXBElement<se.cosaq.schema.product.Product> getProductByIdXML(
			@PathParam("id") int id) {
		ObjectFactory objFact = new ObjectFactory();
		return objFact.createProduct(getProductById(id));
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{id}")
	public se.cosaq.schema.product.Product getProductById(
			@PathParam("id") int id) {
		Product prd = Product.getProductById(id);
		if (prd != null) {
			ObjectFactory objFact = new ObjectFactory();
			se.cosaq.schema.product.Product prdXml = objFact.createProduct();
			prdXml.setId(prd.getId());
			prdXml.setName(prd.getName());

			se.cosaq.schema.product.PartList partListXml = objFact
					.createPartList();
			List<se.cosaq.schema.product.Part> thePlist = partListXml.getPart();
			Iterator<Part> partIter = prd.getPartList().iterator();
			while (partIter.hasNext()) {
				Part part = partIter.next();
				se.cosaq.schema.product.Part partXml = objFact.createPart();
				partXml.setId(part.getId());
				partXml.setName(part.getName());
				thePlist.add(partXml);
			}
			prdXml.setPartList(partListXml);

			return prdXml;

		} else
			throw new WebApplicationException(Response.Status.NOT_FOUND);
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public JAXBElement<se.cosaq.schema.product.ProductList> getAllProductsXML() {
		ObjectFactory objFact = new ObjectFactory();
		return objFact.createProductList(getAllProducts());
	}

	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public se.cosaq.schema.product.ProductList getAllProducts() {

		ObjectFactory objFact = new ObjectFactory();
		se.cosaq.schema.product.ProductList prdListXml = objFact
				.createProductList();
		List<se.cosaq.schema.product.Product> lst = prdListXml.getProduct();

		List<Product> allPrd = Product.getAllProducts();
		Iterator<Product> iter = allPrd.iterator();
		while (iter.hasNext()) {
			Product prd = iter.next();
			se.cosaq.schema.product.Product prdXml = objFact.createProduct();
			prdXml.setId(prd.getId());
			prdXml.setName(prd.getName());

			se.cosaq.schema.product.PartList partListXml = objFact
					.createPartList();
			List<se.cosaq.schema.product.Part> thePlist = partListXml.getPart();
			Iterator<Part> partIter = prd.getPartList().iterator();
			while (partIter.hasNext()) {
				Part part = partIter.next();
				se.cosaq.schema.product.Part partXml = objFact.createPart();
				partXml.setId(part.getId());
				partXml.setName(part.getName());
				thePlist.add(partXml);
			}
			prdXml.setPartList(partListXml);

			lst.add(prdXml);
		}
		return prdListXml;
	}


}
