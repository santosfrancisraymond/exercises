package au.com.amaysim.main;

import java.math.BigDecimal;

/**
 * @author FSantos1
 * 
 *         This class persists the promo codes. The promo codes should be a
 *         database of some sort.
 * 
 *         The spreadsheet promos.csv is the DS or data source in this exercise.
 * 
 *         Using promos.csv:
 * 
 *         - Users should be able to add more promos that they want on specific
 *         products as long as it has a corresponding product code in
 *         products.csv
 * 
 *         1st column: Product code to apply the promo
 * 
 *         2nd column: Name of the promo
 * 
 *         3rd column: Target promo amount. Used for promos like bulk promos.
 * 
 *         4th column: Amount of discount in percent format. Users should be
 *         able to modify discount percents so this shouldn't be hardcoded
 *         inside the promo code implementation. Used for promos like global
 *         promos.
 * 
 *         5th column: Minumum number of sims the customer should buy to avail
 *         the promo. Not used in this version yet. DEVs should eventually use
 *         this field in the next upgrades to perform validation if the MINIMUM
 *         number is satisfied before users can use the promo . FUTURE
 *         ENHANCEMENT.
 * 
 *         6th column: Promo Type as documented in PromoCodeImpl.java
 * 
 *         7th column: Amount of discount in BigDecimal format. Users should be
 *         able to modify the discount amounts so shouldn't be hardcoded inside
 *         the application. Used for promos like single discount promos.
 * 
 *         8th column: Target FREEBIE item. If the promo is a FREEBIE then
 *         specify the freebie by using the target product code. If the promo is
 *         not a freebie (it's a discount) then the user can just use the same
 *         value of column 1 for specifying the value of column 8.
 * 
 */
public class PromoCode {
	String promoCode;
	String promoName;
	BigDecimal productPrice;
	BigDecimal promoPercent;
	Integer promoMinimum;
	String promoType;
	BigDecimal promoDiscountAmount;
	String promoTarget;

	public PromoCode() {
		super();
	}

	public PromoCode(String promoCode, String promoName,
			BigDecimal productPrice, BigDecimal promoPercent,
			Integer promoMinimum, String promoType,
			BigDecimal promoDiscountAmount, String promoTarget) {
		super();
		this.promoCode = promoCode;
		this.promoName = promoName;
		this.productPrice = productPrice;
		this.promoPercent = promoPercent;
		this.promoMinimum = promoMinimum;
		this.promoType = promoType;
		this.promoDiscountAmount = promoDiscountAmount;
		this.promoTarget = promoTarget;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getPromoName() {
		return promoName;
	}

	public void setPromoName(String promoName) {
		this.promoName = promoName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public BigDecimal getPromoPercent() {
		return promoPercent;
	}

	public void setPromoPercent(BigDecimal promoPercent) {
		this.promoPercent = promoPercent;
	}

	public Integer getPromoMinimum() {
		return promoMinimum;
	}

	public void setPromoMinimum(Integer promoMinimum) {
		this.promoMinimum = promoMinimum;
	}

	public String getPromoType() {
		return promoType;
	}

	public void setPromoType(String promoType) {
		this.promoType = promoType;
	}

	public BigDecimal getPromoDiscountAmount() {
		return promoDiscountAmount;
	}

	public void setPromoDiscountAmount(BigDecimal promoDiscountAmount) {
		this.promoDiscountAmount = promoDiscountAmount;
	}

	public String getPromoTarget() {
		return promoTarget;
	}

	public void setPromoTarget(String promoTarget) {
		this.promoTarget = promoTarget;
	}

	public String toString() {
		return getPromoCode() + ":" + getPromoName() + ":" + getProductPrice();
	}

}
