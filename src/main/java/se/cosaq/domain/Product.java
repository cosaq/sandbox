package se.cosaq.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.cosaq.db.DbFactory;
import se.cosaq.db.PartDB;
import se.cosaq.db.ProductDB;

public class Product {

	private String name = null;
	private int id ;
	private List<Part> partList = new ArrayList<Part>();

	private Product(ProductDB productDB){
		this.id = productDB.getProductId();
		this.name = productDB.getProductName();	
		
		Iterator<PartDB> dbIter = productDB.getPartDBList().iterator();
		while (dbIter.hasNext()) {
			PartDB pdb = dbIter.next();
			Part part = new Part(pdb);
			partList.add(part);
		}
		
	}
	
	public static Product getProductById(int productId){
		Product product = null;
		ProductDB aProductDB = DbFactory.getInstance().getProduct(productId);
		if (aProductDB != null) {
			product =  new Product(aProductDB);
		}
		return product;
	}

	public static List<Product> getAllProducts(){
		List<Product> allProducts = new ArrayList<Product>();
		List<ProductDB> allProductDB = DbFactory.getInstance().getAllProducts();
		Iterator<ProductDB> dbIter = allProductDB.iterator();
		while (dbIter.hasNext()) {
			ProductDB pdb = dbIter.next();
			Product prd = new Product(pdb);
			allProducts.add(prd);
		}
		return allProducts;
	}
	
	public List<Part> getPartList() {
		return partList;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
