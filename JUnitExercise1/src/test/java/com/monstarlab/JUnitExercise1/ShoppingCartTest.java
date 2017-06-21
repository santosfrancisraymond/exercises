package com.monstarlab.JUnitExercise1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ShoppingCartTest {

	private final String SEPARATOR = ",";

	private final String CSVFILE_PRODUCT = "products.csv";

	private final String CSVFILE_PROMO = "promos.csv";

	private static final String EXPECTED_CART_TOTAL = "Expected Cart Total : ";
	private static final String EXPECTED_CART_ITEMS = "Expected Cart Items :\n";

	ShoppingCart cart;

	ArrayList<Product> productsList;

	HashMap<String, Promo> promosList;

	ArrayList<Integer> counterArray;

	public static void main(String[] args) {

		ShoppingCartTest shoppingCartTest = new ShoppingCartTest();

		shoppingCartTest.initializeData();

		Scanner sc = new Scanner(System.in);

		boolean done = false;

		while (!done) {

			System.out.println(
					"Add a product using the Product Code (case sensitive). Use EXIT keyword to go to totals: ");

			// Print the menu
			for (Product product : shoppingCartTest.getProductsList()) {
				System.out.println(product.toString());
			}

			String input = sc.nextLine();

			if (input.equals("EXIT")) {
				done = true;
				continue;
			}

			// Check input is a valid Product Code.
			if (shoppingCartTest.getPromosList().containsKey(input)) {

				shoppingCartTest.getCart()
						.add(ProductFinderUtility.getProduct(shoppingCartTest.getCart().getPricingRules(), input));
			}

			else {
				System.out.println("Invalid input. Try again.");
				done = false;
			}
		}

		System.out.println(EXPECTED_CART_TOTAL + shoppingCartTest.getCart().total());
		System.out.println(EXPECTED_CART_ITEMS + shoppingCartTest.getCart().items());

	}

	public void initializeData() {

		this.productsList = new ArrayList<Product>();
		this.promosList = new HashMap<String, Promo>();
		this.counterArray = new ArrayList<Integer>();

		InputStream inputStreamProduct = this.getClass().getClassLoader().getResourceAsStream(CSVFILE_PRODUCT);

		InputStream inputStreamPromo = this.getClass().getClassLoader().getResourceAsStream(CSVFILE_PROMO);

		String line = new String("");

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(inputStreamProduct, StandardCharsets.UTF_8))) {

			while ((line = br.readLine()) != null) {

				String[] currentProduct = line.split(SEPARATOR);

				String productCode = currentProduct[0];
				String productName = currentProduct[1];
				String price = currentProduct[2];

				Product product = new Product();
				product.setProductCode(productCode);
				product.setProductName(productName);
				product.setPrice(new BigDecimal(price));

				getProductsList().add(product);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStreamPromo, StandardCharsets.UTF_8))) {

			while ((line = br.readLine()) != null) {

				String[] currentPromo = line.split(SEPARATOR);

				Promo promoCode = new Promo();
				promoCode.setPromoCode(currentPromo[0]);
				promoCode.setPromoName(currentPromo[1]);
				promoCode.setProductPrice(new BigDecimal(currentPromo[2]));
				promoCode.setPromoPercent(new BigDecimal(currentPromo[3]));
				promoCode.setPromoMinimum(Integer.parseInt(currentPromo[4]));
				promoCode.setPromoType(currentPromo[5]);
				promoCode.setPromoDiscountAmount(new BigDecimal(currentPromo[6]));
				promoCode.setPromoTarget(currentPromo[7]);

				getPromosList().put(currentPromo[0], promoCode);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		cart = new ShoppingCart(new BigDecimal(0.0), getProductsList(), new ArrayList<Product>(), getPromosList(), new ArrayList<Promo>());

		// System.out.println(cart.getPricingRules());
		// System.out.println(cart.getPromosList());

	}

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	public ArrayList<Product> getProductsList() {
		return productsList;
	}

	public void setProductsList(ArrayList<Product> productsList) {
		this.productsList = productsList;
	}

	public HashMap<String, Promo> getPromosList() {
		return promosList;
	}

	public void setPromosList(HashMap<String, Promo> promosList) {
		this.promosList = promosList;
	}

	public ArrayList<Integer> getCounterArray() {
		return counterArray;
	}

	public void setCounterArray(ArrayList<Integer> counterArray) {
		this.counterArray = counterArray;
	}
}
