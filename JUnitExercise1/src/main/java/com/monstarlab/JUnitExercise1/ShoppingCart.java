package com.monstarlab.JUnitExercise1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Santos, Francis & Cruz, Jason Anthony
 * 
 *         This is the main class that holds the Pricing Rules (List of
 *         Products), the Promo Codes, the items and final total.
 * 
 *         total - the cart's main attribute
 * 
 *         pricingRules - obtained from products.csv
 * 
 *         items - list of items added by the user
 * 
 *         promoCodes - list of promo codes added by the system obtained from
 *         promos.csv
 * 
 */
public class ShoppingCart {

	BigDecimal total;

	List<Product> pricingRules;

	List<Product> items;

	List<Promo> promoCodes;

	HashMap<String, Promo> promoMap;

	public ShoppingCart(BigDecimal total, List<Product> pricingRules, List<Product> items, List<Promo> promoCodes) {
		super();
		this.total = total;
		this.pricingRules = pricingRules;
		this.items = items;
		this.promoCodes = promoCodes;
	}

	public ShoppingCart(BigDecimal total, List<Product> pricingRules, List<Product> items,
			HashMap<String, Promo> promoMap, List<Promo> promoCodes) {
		super();
		this.total = total;
		this.pricingRules = pricingRules;
		this.items = items;
		this.promoMap = promoMap;
		this.promoCodes = promoCodes;
	}

	public List<Promo> getPromoCodes() {
		return promoCodes;
	}

	public void setPromoCodes(List<Promo> promoCodes) {
		this.promoCodes = promoCodes;
	}

	public HashMap<String, Promo> getPromoMap() {
		return promoMap;
	}

	public void setPromoMap(HashMap<String, Promo> promoMap) {
		this.promoMap = promoMap;
	}

	public ShoppingCart(List<Product> pricingRules) {
		super();
		this.total = new BigDecimal(0.0);
		this.pricingRules = pricingRules;
		this.items = new ArrayList<Product>();
		this.promoCodes = new ArrayList<Promo>();

	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<Product> getPricingRules() {
		return pricingRules;
	}

	public void setPricingRules(List<Product> pricingRules) {
		this.pricingRules = pricingRules;
	}

	public List<Product> getItems() {
		return items;
	}

	public void setItems(List<Product> items) {
		this.items = items;
	}

	public List<Promo> getPromos() {
		return promoCodes;
	}

	public void setPromos(List<Promo> promoCodes) {
		this.promoCodes = promoCodes;
	}

	// you can add a product
	public void add(Product product) {
		this.items.add(product);
	}

	// you can add a promo
	public void add(Promo promoCode) {
		this.promoCodes.add(promoCode);
	}

	// you can add a product with the specified promo code
	public void add(Product product, Promo promoCode) {
		this.items.add(product);
		this.promoCodes.add(promoCode);
	}

	public String toString() {
		String toString = "";
		for (Product product : this.items) {
			toString = toString + product.getProductName() + "\n";
		}
		return toString;
	}

	/*
	 * Note that you will only see the freebies of items() when you use the
	 * method total(). See how it's used in ShoppingCartTest.java
	 */
	public String items() {
		String items = "";
		for (Product product : this.items) {
			items = items + product.getProductName() + "\n";
		}
		return items;
	}

	public BigDecimal total() {

		executePromoAlgo();

		// Apply the necessary single discount, bulk and freebie promos
		PromoCodeImpl promoCalculator = new PromoCodeImpl(this);
		promoCalculator.processPromos();

		// Sum up all this cart's prices
		TotalCalculator totalCalculator = new TotalCalculator(this);
		totalCalculator.processTotal();

		// Finally, apply the necessary global promos of the cart
		promoCalculator.processGlobalPromo();

		setTotal(this.getTotal());

		return getTotal();

	}

	// just a method I used for verifying the cart has the promo codes.
	public String promoCodes() {
		String promoCodes = "";
		for (Promo pc : getPromos()) {
			promoCodes = promoCodes + pc.getPromoName() + "\n";
		}
		return promoCodes;
	}

	public void executePromoAlgo() {
		// for (Integer item : this.getCounterArray()) {

		HashMap<String, Integer> summary = new HashMap<String, Integer>();

		// populate with non null values first

		for (Product product : getItems()) {
			summary.put(product.getProductCode(), new Integer(0));
		}

		// System.out.println(summary);

		// summarize counts
		for (Product product : getItems()) {
			summary.put(product.getProductCode(), summary.get(product.getProductCode()) + 1);
		}

		for (Map.Entry<String, Integer> entrySet : summary.entrySet()) {
			String key = entrySet.getKey();
			Integer value = entrySet.getValue();

			// compute freebies 

			Integer minimum = PromoCodeFinderUtility.getPromoCode(getPromoMap(), key).getPromoMinimum();
			Integer freebieCount = value / minimum;

			// based on the freebie count, add the freebie promo
			for (int i = 0; i < freebieCount; i++) {
				this.add(PromoCodeFinderUtility.getPromoCode(getPromoMap(), key));
			}
		}

		// compute single discounts and add the promo

		// compute bulk discounts and add the promo

		// compute global discount and add the promo

		// }
	}
}