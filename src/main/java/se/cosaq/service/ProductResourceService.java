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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public JAXBElement<se.cosaq.schema.product.Product> getProductAsTextXMLById(
			@PathParam("id") int id) {
		// TO-DO
		Product prd = Product.getProductById(id);
		if (prd != null) {
			ObjectFactory objFact = new ObjectFactory();
			se.cosaq.schema.product.Product prdXml = objFact.createProduct();
			prdXml.setId(prd.getId());
			prdXml.setName(prd.getName());

			se.cosaq.schema.product.PartList partListXml = objFact.createPartList();
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
			
			return objFact.createProduct(prdXml);

		} else
			throw new WebApplicationException(Response.Status.NOT_FOUND);
	}

	// @GET
	// @Produces(MediaType.APPLICATION_XML)
	// @Path("{id}")
	// public JAXBElement<se.cosaq.schema.product.v1.Product>
	// getProductById(@PathParam("id") int id) {
	// // TO-DO
	// Product prd = Product.getProductById(id);
	// if (prd != null) {
	// ObjectFactory objFact = new ObjectFactory();
	// se.cosaq.schema.product.v1.Product prdXml = objFact.createProduct();
	// prdXml.setId(prd.getId());
	// prdXml.setName(prd.getName());
	//
	// return objFact.createProduct(prdXml);
	//
	// } else
	// throw new WebApplicationException(Response.Status.NOT_FOUND);
	// }

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public JAXBElement<se.cosaq.schema.product.ProductList> getAllProducts() {
		// TO-DO

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

			se.cosaq.schema.product.PartList partListXml = objFact.createPartList();
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
		return objFact.createProductList(prdListXml);
	}

	private JAXBContext test() throws JAXBException {
		JAXBContext jc = JAXBContext
				.newInstance(se.cosaq.schema.product.Product.class);

		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		return jc;
	}

}
