package com.monstarlab.JUnitExercise1;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Santos, Francis & Cruz, Jason Anthony
 * 
 * 
 *         This just sums up all the items of the cart. No discounts or promos
 *         are applied when using this. There's a separate PromoCodeImpl.java
 *         for applying the promos.
 * 
 *         To use, just pass a ShoppingCart and the processTotal() will total
 *         the cart's item's prices.
 */
public class TotalCalculator {

	BigDecimal total;

	ShoppingCart cart;

	public TotalCalculator() {
		super();

	}

	public TotalCalculator(BigDecimal total, ShoppingCart cart) {
		super();
		this.total = total;
		this.cart = cart;

	}

	public TotalCalculator(ShoppingCart cart) {
		super();
		this.total = new BigDecimal(0.0);
		this.cart = cart;

	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	public void processTotal() {

		setTotal(new BigDecimal(0.0));

		List<Product> items = getCart().getItems();
		for (Product product : items) {

			BigDecimal productPrice = product.getPrice();

			getCart().setTotal(getCart().getTotal().add(productPrice));

		}

		setTotal(getCart().getTotal());

	}

}