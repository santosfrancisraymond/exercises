package com.monstarlab.JUnitExercise1;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitExercise1Test {

	private final String SEPARATOR = ",";

	// TODO if we had a server/database we can avoid hardcoding the csv files

	private final String CSVFILE_PRODUCT = "products.csv";

	private final String CSVFILE_PROMO = "promos.csv";

	private final String EXPECTED_CART_TOTAL = "Expected Cart Total : ";
	private final String EXPECTED_CART_ITEMS = "Expected Cart Items :\n";

	private ShoppingCart cart;

	private ArrayList<Product> productsList;

	private ArrayList<Promo> promosList;

	@BeforeClass
	public static void beforeClass() {

	}

	@Before
	public void init() {

	}

	@Test
	public void test() {

		initializeData();

		System.out.println("===Test Promo 1===");
		ShoppingCart cart1 = new ShoppingCart(productsList);
		cart1.add(ProductFinderUtility.getProduct(productsList, "GLBRT_STARTER"));
		cart1.add(ProductFinderUtility.getProduct(productsList, "GLBRT_STARTER"));
		cart1.add(ProductFinderUtility.getProduct(productsList, "GLBRT_STARTER"));
		cart1.add(ProductFinderUtility.getProduct(productsList, "GLBRT_STARTER"));
		cart1.add(ProductFinderUtility.getProduct(productsList, "GLBRT_STARTER"),
				PromoCodeFinderUtility.getPromoCode(promosList, "GLBRT_STARTER"));
		System.out.println(cart1.promoCodes());
		System.out.println(EXPECTED_CART_TOTAL + cart1.total());
		System.out.println(EXPECTED_CART_ITEMS + cart1.items());

		// JUnit test #1
		BigDecimal bd1 = cart1.getTotal();
		BigDecimal bd2 = BigDecimal.valueOf(6495.00);
		assertEquals(bd1.intValue(), bd2.intValue(), 0);

		System.out.println("===Test Promo 2===");
		ShoppingCart cart2 = new ShoppingCart(productsList);
		cart2.add(ProductFinderUtility.getProduct(productsList, "GLBRT_ENTHU"));
		cart2.add(ProductFinderUtility.getProduct(productsList, "GLBRT_ENTHU"));
		cart2.add(ProductFinderUtility.getProduct(productsList, "GLBRT_ENTHU"),
				PromoCodeFinderUtility.getPromoCode(promosList, "GLBRT_ENTHU"));
		System.out.println(cart2.promoCodes());
		System.out.println(EXPECTED_CART_TOTAL + cart2.total());
		System.out.println(EXPECTED_CART_ITEMS + cart2.items());

		// JUnit test #2
		bd1 = cart2.getTotal();
		bd2 = BigDecimal.valueOf(3999.00);
		assertEquals(bd1.intValue(), bd2.intValue(), 0);

		System.out.println("===Test Promo 3===");
		ShoppingCart cart3 = new ShoppingCart(productsList);
		cart3.add(ProductFinderUtility.getProduct(productsList, "GLBRT_ADDICT"));
		cart3.add(ProductFinderUtility.getProduct(productsList, "GLBRT_ADDICT"),
				PromoCodeFinderUtility.getPromoCode(promosList, "GLBRT_ADDICT"));

		System.out.println(cart3.promoCodes());
		System.out.println(EXPECTED_CART_TOTAL + cart3.total());
		System.out.println(EXPECTED_CART_ITEMS + cart3.items());

		// JUnit test #3
		bd1 = cart3.getTotal();
		bd2 = BigDecimal.valueOf(6598.00);
		assertEquals(bd1.intValue(), bd2.intValue(), 0);

		System.out.println("===Test Promo 4.1===");
		ShoppingCart cart4_1 = new ShoppingCart(productsList);
		cart4_1.add(ProductFinderUtility.getProduct(productsList, "GLBRT_ENTHU"));
		cart4_1.add(ProductFinderUtility.getProduct(productsList, "GLBRT_ADDICT"),
				PromoCodeFinderUtility.getPromoCode(promosList, "GLBRT_ENTHU_ADDICT_STARTER"));

		System.out.println(cart4_1.promoCodes());
		System.out.println(EXPECTED_CART_TOTAL + cart4_1.total());
		System.out.println(EXPECTED_CART_ITEMS + cart4_1.items());

		// JUnit test #4.1
		bd1 = cart4_1.getTotal();
		bd2 = BigDecimal.valueOf(4898.00);
		assertEquals(bd1.intValue(), bd2.intValue(), 0);

		System.out.println("===Test Promo 4.2===");
		ShoppingCart cart4_2 = new ShoppingCart(productsList);
		cart4_2.add(ProductFinderUtility.getProduct(productsList, "GLBRT_ENTHU"));
		cart4_2.add(ProductFinderUtility.getProduct(productsList, "GLBRT_ADDICT"),
				PromoCodeFinderUtility.getPromoCode(promosList, "GLBRT_ENTHU_ADDICT_DATA"));

		System.out.println(cart4_2.promoCodes());
		System.out.println(EXPECTED_CART_TOTAL + cart4_2.total());
		System.out.println(EXPECTED_CART_ITEMS + cart4_2.items());

		// JUnit test #4.2
		bd1 = cart4_2.getTotal();
		bd2 = BigDecimal.valueOf(4898.00);
		assertEquals(bd1.intValue(), bd2.intValue(), 0);


		System.out.println("===Test Promo 5===");
		ShoppingCart cart5 = new ShoppingCart(productsList);
		cart5.add(ProductFinderUtility.getProduct(productsList, "GLBRT_ENTHU"));
		cart5.add(ProductFinderUtility.getProduct(productsList, "GLBRT_ADDICT"),
				PromoCodeFinderUtility.getPromoCode(promosList, "I_LOVE_GLOBART"));

		// System.out.println(cart5.promoCodes());
		System.out.println(EXPECTED_CART_TOTAL + cart5.total());
		System.out.println(EXPECTED_CART_ITEMS + cart5.items());

		// JUnit test #5
		bd1 = cart5.getTotal();
		bd2 = BigDecimal.valueOf(4163.3000);
		assertEquals(bd1.intValue(), bd2.intValue(), 0);

	}

	@AfterClass
	public static void afterClass() {
	}

	private void initializeData() {

		this.productsList = new ArrayList<Product>();
		this.promosList = new ArrayList<Promo>();

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
}
