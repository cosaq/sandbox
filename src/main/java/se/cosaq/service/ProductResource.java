package se.cosaq.service;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ProductResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt();

}

// mvn archetype:generate -DarchetypeArtifactId=jersey-quickstart-webapp
// -DarchetypeGroupId=org.glassfish.jersey.archetypes -DinteractiveMode=false
// -DgroupId=se.cosaq -DartifactId=simple-product-webapp -Dpackage=se.cosaq
// -DarchetypeVersion=2.6