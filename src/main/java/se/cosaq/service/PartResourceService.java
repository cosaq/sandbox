package se.cosaq.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Path("part")
public class PartResourceService {

	@GET
	@Produces("application/xml")
	@Path("{id}")
	public String getPartAssXml(@PathParam("id") int id) {
		return "this is XML  <Part><PartId>1-123</PartId><PartName>aName</PartName></Part>";
	}

	@GET
	@Produces("application/json")
	@Path("{id}")
	public String getPartAsJson(@PathParam("id") int id) {
		return "	 {partid:1-123, PartName: aName}";
	}

	
}
