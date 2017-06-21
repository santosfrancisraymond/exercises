package com.monstarlab.JUnitExercise1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartTestArrayList {

	private final String SEPARATOR = ",";

	private final String CSVFILE_PRODUCT = "products.csv";

	private final String CSVFILE_PROMO = "promos.csv";

	private static final String EXPECTED_CART_TOTAL = "Expected Cart Total : ";
	private static final String EXPECTED_CART_ITEMS = "Expected Cart Items :\n";

	ShoppingCart cart;

	ArrayList<Product> productsList;

	ArrayList<Promo> promosList;

	ArrayList<Integer> counterArray;

	public static void main(String[] args) {

		ShoppingCartTestArrayList shoppingCartTest = new ShoppingCartTestArrayList();

		shoppingCartTest.initializeData();

		Scanner sc = new Scanner(System.in);

		boolean done = false;

		int counter1 = 0;
		int counter2 = 0;
		int counter3 = 0;
//		int counter4 = 0;


		while (!done) {

			// if 5 / 5 = 1 then add the promo GLBRT_STARTER to the cart
			if ((counter1 != 0) && (counter1
					/ PromoCodeFinderUtility.getPromoCode(shoppingCartTest.getPromosList(), "GLBRT_STARTER")
							.getPromoMinimum().intValue() == 1)) {

				shoppingCartTest.getCart()
						.add(PromoCodeFinderUtility.getPromoCode(shoppingCartTest.getPromosList(), "GLBRT_STARTER"));
			}

			// if 3 / 3 = 1 then add the promo GLBRT_ENTHU to the cart
			if ((counter2 != 0) && (counter2 / PromoCodeFinderUtility
					.getPromoCode(shoppingCartTest.getPromosList(), "GLBRT_ENTHU").getPromoMinimum().intValue() == 1)) {

				shoppingCartTest.getCart()
						.add(PromoCodeFinderUtility.getPromoCode(shoppingCartTest.getPromosList(), "GLBRT_ENTHU"));
			}

			// if 2 / 2 = 1 then add the promo GLBRT_ENTHU to the cart
			if ((counter3 != 0)
					&& (counter3 / PromoCodeFinderUtility.getPromoCode(shoppingCartTest.getPromosList(), "GLBRT_ADDICT")
							.getPromoMinimum().intValue() == 1)) {

				shoppingCartTest.getCart()
						.add(PromoCodeFinderUtility.getPromoCode(shoppingCartTest.getPromosList(), "GLBRT_ADDICT"));
			}

			System.out.println("Add a product to add to the cart (1,2,3,4, EXIT): ");
			System.out.println("1. GLBRT_STARTER UNLI 5GB 1299");
			System.out.println("2. GLBRT_ENTHU UNLI 10GB 1599");
			System.out.println("3. GLBRT_ADDICT	UNLI 50GB 3299");
			System.out.println("4. GLBRT_ADDTL_DATA UNLI 5GB 199");
			String input = sc.nextLine();

			switch (input) {
			case "1":
				counter1++;
				shoppingCartTest.getCart().add(
						ProductFinderUtility.getProduct(shoppingCartTest.getCart().getPricingRules(), "GLBRT_STARTER"));
				break;
			case "2":
				counter2++;
				shoppingCartTest.getCart().add(
						ProductFinderUtility.getProduct(shoppingCartTest.getCart().getPricingRules(), "GLBRT_ENTHU"));
				break;
			case "3":
				counter3++;
				shoppingCartTest.getCart().add(
						ProductFinderUtility.getProduct(shoppingCartTest.getCart().getPricingRules(), "GLBRT_ADDICT"));
				break;
			case "4":
				// counter4++;
				shoppingCartTest.getCart().add(ProductFinderUtility
						.getProduct(shoppingCartTest.getCart().getPricingRules(), "GLBRT_ADDTL_DATA"));

				break;
			case "EXIT":
				done = true;
				break;
			default:
				System.out.println("Invalid input. Try again.");
				break;
			}
		}

		System.out.println(EXPECTED_CART_TOTAL + shoppingCartTest.getCart().total());
		System.out.println(EXPECTED_CART_ITEMS + shoppingCartTest.getCart().items());

	}

	public void initializeData() {

		this.productsList = new ArrayList<Product>();
		this.promosList = new ArrayList<Promo>();
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

				getPromosList().add(promoCode);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		cart = new ShoppingCart(getProductsList());

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

	public ArrayList<Promo> getPromosList() {
		return promosList;
	}

	public void setPromosList(ArrayList<Promo> promosList) {
		this.promosList = promosList;
	}
	
	public ArrayList<Integer> getCounterArray() {
		return counterArray;
	}

	public void setCounterArray(ArrayList<Integer> counterArray) {
		this.counterArray = counterArray;
	}
}
