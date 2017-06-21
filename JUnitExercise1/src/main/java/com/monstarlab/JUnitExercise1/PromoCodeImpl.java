package com.monstarlab.JUnitExercise1;

import java.math.BigDecimal;
import java.util.List;



/**
 * @author Santos, Francis & Cruz, Jason Anthony
 * 
 *         This processes all the promos of the shopping cart. In this
 *         implementation, the target goal is to have a more maintainable list
 *         of promo codes and promo types. Avoid hardcoding the logic and
 *         amounts for promos. The promos should be a database or csv so that we can
 *         easily change the single discount amounts, freebies, bulk discounts,
 *         global discounts without having to recompile java codes (for the
 *         promo code logic) every time a promo code or even a product needs to
 *         be modified. Users should be able to freely add, subtract, modify
 *         promos depending on Globart's Promotions Department's likings.
 *         Eventually, the users should be able to maintain the products and
 *         promos via a front end of some sort using the products and promos
 *         data sources:
 * 
 *         - promos.csv (the database of promos) has a 6th column which is the
 *         promo type. The promo calculator relies on the promo code and product
 *         code plus the promo type.
 * 
 *         So far, based on the promo codes of this application we assume there
 *         are only 4 general types of promos namely:
 * 
 *         - DISCOUNTS - the discount amount depends on the amount specified in
 *         promos.csv (7th column)
 * 
 *         - FREEBIES - the freebie depends on the target product code in
 *         promos.csv (last column)
 * 
 *         - BULK - if the minimum number (5th column) of items are added to the
 *         cart, then all items' (with BULK promo type) prices are reduced to
 *         the amount in the 3rd column of promos.csv
 * 
 *         - GLOBAL - depends on the amount in percent specified in promos.csv
 *         (4th column)
 * 
 *         To use the promo code calculator, just pass a ShoppingCart and the
 *         processTotal() will total the cart's item's prices.
 * 
 * 
 */
public class PromoCodeImpl {

	ShoppingCart cart;

	/*
	 * Future Enhancement. In this system, the assumption is there can only be
	 * one global discount code applied per cart. I haven't seen online shopping
	 * sites where we can apply multiple global discount voucher codes, but if
	 * there's such a scenario then we can enhance the logic such that the
	 * global discount codes are in a list of global discount codes aka
	 * List<GlobalDiscountCode>.
	 */
	String globalDiscountCode;

	BigDecimal finalTotal;

	private final String FREEBIE = "FREEBIE";
	private final String DISCOUNT = "DISCOUNT";
	private final String BULK = "BULK";
	private final String GLOBAL = "GLOBAL";

	public PromoCodeImpl(ShoppingCart cart, BigDecimal finalTotal) {
		super();
		setCart(cart);
		setFinalTotal(finalTotal);

	}

	public PromoCodeImpl(ShoppingCart cart) {
		super();
		setCart(cart);
		setFinalTotal(cart.getTotal());

	}

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	public String getGlobalDiscountCode() {
		return globalDiscountCode;
	}

	public void setGlobalDiscountCode(String globalDiscountCode) {
		this.globalDiscountCode = globalDiscountCode;
	}

	public BigDecimal getFinalTotal() {
		return finalTotal;
	}

	public void setFinalTotal(BigDecimal finalTotal) {
		this.finalTotal = finalTotal;
	}

	public void processPromos() {

		String promoCode = new String("");
		String promoName = new String("");
		BigDecimal productPrice = new BigDecimal(0.0);
		BigDecimal promoPercent = new BigDecimal(0.0);
		Integer promoMinimum = new Integer(0);
		String promoType = new String("");
		BigDecimal promoDiscountAmount = new BigDecimal(0.0);
		String promoTarget = new String("");

		setFinalTotal(getCart().getTotal());

		setGlobalDiscountCode("");

		List<Promo> promoCodes = getCart().getPromos();

		for (Promo pc : promoCodes) {

			promoCode = pc.getPromoCode();
			promoName = pc.getPromoName();
			productPrice = pc.getProductPrice();
			promoPercent = pc.getPromoPercent();
			promoMinimum = pc.getPromoMinimum();
			promoType = pc.getPromoType();
			promoDiscountAmount = pc.getPromoDiscountAmount();
			promoTarget = pc.getPromoTarget();

			// process freebies
			if (promoType.equals(FREEBIE)) {

				/*
				 * add the promoTarget (freebie) to the cart, the freebie item
				 * should be acquired from the database and its price should
				 * always be zero.
				 * 
				 */
				Product freebie = new Product(promoTarget, ProductFinderUtility
						.getProduct(getCart().getPricingRules(), promoTarget)
						.getProductName(), new BigDecimal(0.0));

				getCart().add(freebie);
			}

			// process single discounts
			if (promoType.equals(DISCOUNT)) {

				/*
				 * Simply subtract the discount amount from the database
				 * 
				 */
				BigDecimal total = (getCart().getTotal())
						.subtract(promoDiscountAmount);

				getCart().setTotal(total);

				setFinalTotal(getCart().getTotal());
			}

			// process bulk discounts
			if (promoType.equals(BULK)) {

				/*
				 * If the promo is of BULK type, then set all the cart's items
				 * of the same promo code as the current promo type being
				 * processed -> reduce the items' amounts to the promo price.
				 * 
				 */
				for (Product item : getCart().getItems()) {

					if (item.getProductCode().equals(promoCode)) {

						item.setPrice(PromoCodeFinderUtility.getPromoCode(
								promoCodes, promoCode).getProductPrice());
					}
				}
			}

			/*
			 * Process global discounts if any
			 * 
			 * Future enhancement is we can add multiple global discount codes
			 * if requested by Globart's Promotions Department
			 * 
			 * Example: Adding the promo code 'I_LOVE_GLOBART' will apply a 15%
			 * discount across the board.
			 */
			if (promoType.equals(GLOBAL)) {

				setGlobalDiscountCode(promoTarget);
			}

		}

	}

	public void processGlobalPromo() {
		/*
		 * Apply only the global discount at the end of it all. This logic
		 * should change if the Promotions Department of Globart approve of
		 * multiple global discount codes.
		 * 
		 * Future Enhancement: Process a list of global discounts instead of
		 * applying only 1 global discount to the final total.
		 */

		if (!(getGlobalDiscountCode().equals(""))) {
			List<Promo> promoCodes = getCart().getPromos();
			BigDecimal total = new BigDecimal(0.0);

			total = getCart().getTotal();
			total = total.subtract((total).multiply((PromoCodeFinderUtility
					.getPromoCode(promoCodes, this.globalDiscountCode)
					.getPromoPercent())));

			getCart().setTotal(total);

		}

		setFinalTotal(getCart().getTotal());

	}
}