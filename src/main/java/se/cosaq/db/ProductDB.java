package se.cosaq.db;

/**
 * 
 * @author anders
 *
 */
public class ProductDB {
	private String productName;
	private int productId;
 
	public ProductDB(int anId, String aName){
		productId=anId;
		productName=aName;
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
