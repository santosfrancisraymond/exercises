package au.com.amaysim.main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author FSantos1
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

	List<PromoCode> promoCodes;

	public ShoppingCart(BigDecimal total, List<Product> pricingRules,
			List<Product> items, List<PromoCode> promoCodes) {
		super();
		this.total = total;
		this.pricingRules = pricingRules;
		this.items = items;
		this.promoCodes = promoCodes;
	}

	public ShoppingCart(List<Product> pricingRules) {
		super();
		this.total = new BigDecimal(0.0);
		this.pricingRules = pricingRules;
		this.items = new ArrayList<Product>();
		this.promoCodes = new ArrayList<PromoCode>();

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

	public List<PromoCode> getPromoCodes() {
		return promoCodes;
	}

	public void setPromoCodes(List<PromoCode> promoCodes) {
		this.promoCodes = promoCodes;
	}

	// you can add a product
	public void add(Product product) {
		this.items.add(product);

	}

	// you can add a product with the specified promo code
	public void add(Product product, PromoCode promoCode) {
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
		for (PromoCode pc : getPromoCodes()) {
			promoCodes = promoCodes + pc.getPromoName() + "\n";
		}
		return promoCodes;
	}

}
