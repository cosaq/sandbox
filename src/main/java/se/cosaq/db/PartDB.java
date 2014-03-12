package se.cosaq.db;

public class PartDB {
	private String partName;
	private int partId;
 
	public PartDB(int anId, String aName){
		partId=anId;
		partName=aName;
	}
	
	public int getProductId() {
		return partId;
	}

	public void setProductId(int productId) {
		this.partId = productId;
	}

	public String getProductName() {
		return partName;
	}

	public void setProductName(String productName) {
		this.partName = productName;
	}

}
