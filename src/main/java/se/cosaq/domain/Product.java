package se.cosaq.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.cosaq.db.DbFactory;
import se.cosaq.db.ProductDB;

public class Product {

	private String name = null;
	private int id ;

	private Product(ProductDB productDB){
		this.id = productDB.getProductId();
		this.name = productDB.getProductName();	
	}
	
	public static Product getProductById(int productId){
		Product product = null;
		ProductDB aProductDB = DbFactory.getProduct(productId);
		if (aProductDB != null) {
			product =  new Product(aProductDB);
		}
		return product;
	}

	public static List<Product> getAllProducts(){
		List<Product> allProducts = new ArrayList<Product>();
		List<ProductDB> allProductDB = DbFactory.getAllProducts();
		Iterator<ProductDB> dbIter = allProductDB.iterator();
		while (dbIter.hasNext()) {
			ProductDB pdb = dbIter.next();
			Product prd = new Product(pdb);
			allProducts.add(prd);
		}
		return allProducts;
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
