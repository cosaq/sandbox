package se.cosaq.db;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author anders
 *
 */
public class ProductDB {
	private String productName;
	private int productId;
	private List<PartDB> partDBList = new ArrayList<PartDB>();
 

	public ProductDB(int anId, String aName){
		productId=anId;
		productName=aName;
	}

	public void setPartDBList(List<PartDB> partDBList) {
		this.partDBList = partDBList;
	}

	public List<PartDB> getPartDBList() {
		return partDBList;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	

}
