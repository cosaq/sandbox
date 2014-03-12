
package se.cosaq.domain.test;

import se.cosaq.domain.Product;

public class ProductTest {
	public static void main(String[] args) {

		for (int id = 1; id < 4; id++) {
			Product prd = Product.getProductById(id);
			if (prd != null)
				System.out.println("Got product [" + id + "]=" + prd.getName());
			else
				System.out.println("No product fir [" + id + "]");
		}
		
		
	}
}
