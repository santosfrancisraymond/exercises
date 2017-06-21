package com.monstarlab.JUnitExercise1;

import java.util.List;

/**
 * @author Santos, Francis & Cruz, Jason Anthony
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