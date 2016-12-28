package au.com.amaysim.utilities;

import java.util.List;

import au.com.amaysim.main.Product;

/**
 * @author FSantos1
 * 
 *         A utility that simply gets a product from the list of products
 */
public class ProductFinderUtility {

	public static Product getProduct(List<Product> products, String productCode) {
		for (Product product : products) {
			if (product.getProductCode().equals(productCode)) {

				return product;
			}
		}
		return null;
	}

}
