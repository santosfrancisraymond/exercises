package au.com.amaysim.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import au.com.amaysim.main.Product;
import au.com.amaysim.main.PromoCode;
import au.com.amaysim.main.ShoppingCart;
import au.com.amaysim.utilities.ProductFinderUtility;
import au.com.amaysim.utilities.PromoCodeFinderUtility;

/**
 * @author FSantos1
 * 
 *         Contains 4 test scenarios as per the Software Engineering Test.pdf.
 * 
 *         Reads the products / pricing rules "database" from products.csv.
 * 
 *         Reads the promos "database" from promos.csv
 * 
 *         Does assert tests based on Software Engineering Test.pdf.
 * 
 *         To run ShoppingCartTest.java
 * 
 *         -> add products.csv and promos.csv to the home folder -> -> Run As a
 *         JUnit Test
 * 
 * 
 */

public class ShoppingCartTestPSVM {

	// TODO if we had a server/database we can avoid hardcoding the DS
	private final static String FILENAME_PRODUCTS = "products.csv";

	// TODO if we had a server/database we can avoid hardcoding the DS
	private final static String FILENAME_PROMOS = "promos.csv";

	private final static String COMMA_SEPARATOR = ",";
	
	private final static String SUCCESS = "Test is successful\n";

	// TODO if we had a server/database we can avoid hardcoding the labels
	private final static String EXPECTED_CART_TOTAL = "Expected Cart Total : ";
	private final static String EXPECTED_CART_ITEMS = "Expected Cart Items :\n";

	private static List<Product> products;
	private static List<PromoCode> promoCodes;

	public static void main(String[] args) {

		initializeData();

		ShoppingCart cart1 = new ShoppingCart(products);
		cart1.add(ProductFinderUtility.getProduct(products, "ult_small"));
		cart1.add(ProductFinderUtility.getProduct(products, "ult_small"));
		cart1.add(ProductFinderUtility.getProduct(products, "ult_small"),
				PromoCodeFinderUtility.getPromoCode(promoCodes, "ult_small"));
		cart1.add(ProductFinderUtility.getProduct(products, "ult_large"));
		// System.out.println(cart1.promoCodes());
		System.out.println(EXPECTED_CART_TOTAL + cart1.total());
		System.out.println(EXPECTED_CART_ITEMS + cart1.items());
		// JUnit test
		BigDecimal bd1 = cart1.getTotal();
		BigDecimal bd2 = BigDecimal.valueOf(94.7);

		if (assertEquals(bd1, bd2)) {
			System.out.println(SUCCESS);

		}

		ShoppingCart cart2 = new ShoppingCart(products);
		cart2.add(ProductFinderUtility.getProduct(products, "ult_small"));
		cart2.add(ProductFinderUtility.getProduct(products, "ult_small"));
		cart2.add(ProductFinderUtility.getProduct(products, "ult_large"));
		cart2.add(ProductFinderUtility.getProduct(products, "ult_large"));
		cart2.add(ProductFinderUtility.getProduct(products, "ult_large"));
		cart2.add(ProductFinderUtility.getProduct(products, "ult_large"),
				PromoCodeFinderUtility.getPromoCode(promoCodes, "ult_large"));
		// System.out.println(cart2.promoCodes());
		System.out.println(EXPECTED_CART_TOTAL + cart2.total());
		System.out.println(EXPECTED_CART_ITEMS + cart2.items());
		// JUnit test
		bd1 = cart2.getTotal();
		bd2 = BigDecimal.valueOf(209.4);
		if (assertEquals(bd1, bd2)) {
			System.out.println(SUCCESS);

		}

		ShoppingCart cart3 = new ShoppingCart(products);
		cart3.add(ProductFinderUtility.getProduct(products, "ult_small"));
		cart3.add(ProductFinderUtility.getProduct(products, "ult_medium"),
				PromoCodeFinderUtility.getPromoCode(promoCodes, "ult_medium"));
		cart3.add(ProductFinderUtility.getProduct(products, "ult_medium"),
				PromoCodeFinderUtility.getPromoCode(promoCodes, "ult_medium"));
		// System.out.println(cart3.promoCodes());
		System.out.println(EXPECTED_CART_TOTAL + cart3.total());
		System.out.println(EXPECTED_CART_ITEMS + cart3.items());
		// JUnit test
		bd1 = cart3.getTotal();
		bd2 = BigDecimal.valueOf(84.7);
		if (assertEquals(bd1, bd2)) {
			System.out.println(SUCCESS);

		}
		ShoppingCart cart4 = new ShoppingCart(products);
		cart4.add(ProductFinderUtility.getProduct(products, "ult_small"));
		cart4.add(ProductFinderUtility.getProduct(products, "1gb"),
				PromoCodeFinderUtility.getPromoCode(promoCodes, "ult_global"));
		// System.out.println(cart4.promoCodes());
		System.out.println(EXPECTED_CART_TOTAL + cart4.total());
		System.out.println(EXPECTED_CART_ITEMS + cart4.items());
		// JUnit test
		bd1 = cart4.getTotal();
		bd2 = BigDecimal.valueOf(31.32);
		if (assertEquals(bd1, bd2)) {
			System.out.println(SUCCESS);

		}
		/*
		 * // Combination of 3 and 4 ShoppingCart cart5 = new
		 * ShoppingCart(products);
		 * cart5.add(ProductFinderUtility.getProduct(products, "ult_small"));
		 * cart5.add(ProductFinderUtility.getProduct(products, "1gb"),
		 * PromoCodeFinderUtility.getPromoCode(promoCodes, "ult_global"));
		 * cart5.add(ProductFinderUtility.getProduct(products, "ult_small"));
		 * cart5.add(ProductFinderUtility.getProduct(products, "ult_medium"),
		 * PromoCodeFinderUtility.getPromoCode(promoCodes, "ult_medium"));
		 * cart5.add(ProductFinderUtility.getProduct(products, "ult_medium"),
		 * PromoCodeFinderUtility.getPromoCode(promoCodes, "ult_medium")); //
		 * System.out.println(cart5.promoCodes());
		 * System.out.println(EXPECTED_CART_TOTAL + cart5.total());
		 * System.out.println(EXPECTED_CART_ITEMS + cart5.items()); // JUnit
		 * test bd1 = cart5.getTotal(); bd2 = BigDecimal.valueOf(107.55); if
		 * (assertEquals(bd1, bd2)) { System.out.println(SUCCESS);
		 * 
		 * } * // Combination of 1 and 2 ShoppingCart cart6 = new
		 * ShoppingCart(products);
		 * cart6.add(ProductFinderUtility.getProduct(products, "ult_small"));
		 * cart6.add(ProductFinderUtility.getProduct(products, "ult_small"));
		 * cart6.add(ProductFinderUtility.getProduct(products, "ult_small"),
		 * PromoCodeFinderUtility.getPromoCode(promoCodes, "ult_small"));
		 * cart6.add(ProductFinderUtility.getProduct(products, "ult_large"));
		 * cart6.add(ProductFinderUtility.getProduct(products, "ult_small"));
		 * cart6.add(ProductFinderUtility.getProduct(products, "ult_small"));
		 * cart6.add(ProductFinderUtility.getProduct(products, "ult_large"));
		 * cart6.add(ProductFinderUtility.getProduct(products, "ult_large"));
		 * cart6.add(ProductFinderUtility.getProduct(products, "ult_large"));
		 * cart6.add(ProductFinderUtility.getProduct(products, "ult_large"),
		 * PromoCodeFinderUtility.getPromoCode(promoCodes, "ult_large")); //
		 * System.out.println(cart6.promoCodes());
		 * System.out.println(EXPECTED_CART_TOTAL + cart6.total());
		 * System.out.println(EXPECTED_CART_ITEMS + cart6.items()); // JUnit
		 * test bd1 = cart6.getTotal(); bd2 = BigDecimal.valueOf(299.1); if
		 * (assertEquals(bd1, bd2)) { System.out.println(SUCCESS);
		 * 
		 * } // Combination of 2 and 3 ShoppingCart cart7 = new
		 * ShoppingCart(products);
		 * cart7.add(ProductFinderUtility.getProduct(products, "ult_small"));
		 * cart7.add(ProductFinderUtility.getProduct(products, "ult_small"));
		 * cart7.add(ProductFinderUtility.getProduct(products, "ult_large"));
		 * cart7.add(ProductFinderUtility.getProduct(products, "ult_large"));
		 * cart7.add(ProductFinderUtility.getProduct(products, "ult_large"));
		 * cart7.add(ProductFinderUtility.getProduct(products, "ult_large"),
		 * PromoCodeFinderUtility.getPromoCode(promoCodes, "ult_large"));
		 * 
		 * cart7.add(ProductFinderUtility.getProduct(products, "ult_small"),
		 * PromoCodeFinderUtility.getPromoCode(promoCodes, "ult_small"));
		 * cart7.add(ProductFinderUtility.getProduct(products, "ult_medium"),
		 * PromoCodeFinderUtility.getPromoCode(promoCodes, "ult_medium"));
		 * cart7.add(ProductFinderUtility.getProduct(products, "ult_medium"),
		 * PromoCodeFinderUtility.getPromoCode(promoCodes, "ult_medium")); //
		 * System.out.println(cart7.promoCodes());
		 * System.out.println(EXPECTED_CART_TOTAL + cart7.total());
		 * System.out.println(EXPECTED_CART_ITEMS + cart7.items()); // JUnit
		 * test bd1 = cart7.getTotal(); bd2 = BigDecimal.valueOf(269.2); if
		 * (assertEquals(bd1, bd2)) { System.out.println(SUCCESS);
		 * 
		 * }
		 */

	}

	private static boolean assertEquals(BigDecimal bd1, BigDecimal bd2) {

		if (bd1.equals(bd2)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * TODO Change the implementation of the csv files to a server/database when
	 * I have more time and resources.
	 */
	private static void initializeData() {

		products = new ArrayList<Product>();
		promoCodes = new ArrayList<PromoCode>();

		FileReader frProducts = null;
		FileReader frPromos = null;

		// read the products / pricing rules database
		try {
			frProducts = new FileReader(FILENAME_PRODUCTS);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			frPromos = new FileReader(FILENAME_PROMOS);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedReader brProducts = new BufferedReader(frProducts);
		BufferedReader brPromos = new BufferedReader(frPromos);

		// initialize products from the "database"
		String line = null;
		String[] strings = null;

		try {

			/*
			 * TODO no validation yet for each field being read
			 * 
			 * create logic for avoiding exceptions etc
			 */
			while ((line = brProducts.readLine()) != null) {
				strings = line.split(COMMA_SEPARATOR);

				Product product = new Product();
				product.setProductCode(strings[0]);
				product.setProductName(strings[1]);
				product.setPrice(new BigDecimal(strings[2]));
				products.add(product);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// initialize promotions from the "database"
		line = null;
		strings = null;

		try {

			/*
			 * TODO no validation yet for each field being read
			 * 
			 * create logic for avoiding exceptions etc
			 */

			while ((line = brPromos.readLine()) != null) {

				strings = line.split(COMMA_SEPARATOR);

				PromoCode promoCode = new PromoCode();
				promoCode.setPromoCode(strings[0]);
				promoCode.setPromoName(strings[1]);
				promoCode.setProductPrice(new BigDecimal(strings[2]));
				promoCode.setPromoPercent(new BigDecimal(strings[3]));
				promoCode.setPromoMinimum(Integer.parseInt(strings[4]));
				promoCode.setPromoType(strings[5]);
				promoCode.setPromoDiscountAmount(new BigDecimal(strings[6]));
				promoCode.setPromoTarget(strings[7]);

				promoCodes.add(promoCode);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			frProducts.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			frPromos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<PromoCode> getPromoCodes() {
		return promoCodes;
	}

	public void setPromoCodes(List<PromoCode> promoCodes) {
		this.promoCodes = promoCodes;
	}

}
