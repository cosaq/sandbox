package se.cosaq.domain;

import se.cosaq.db.PartDB;

public class Part {
	
	private String name = null;
	private int id ;
	
	protected Part(PartDB apartDb){
		this.id = apartDb.getProductId();
		this.name = apartDb.getProductName();	
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


}
