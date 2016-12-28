package au.com.amaysim.utilities;

import java.util.List;

import au.com.amaysim.main.PromoCode;

/**
 * @author FSantos1
 * 
 *         A utility that simply gets a promo code from the list of promo codes
 */
public class PromoCodeFinderUtility {

	public static PromoCode getPromoCode(List<PromoCode> promoCodes,
			String promoCode) {
		for (PromoCode pc : promoCodes) {
			if (pc.getPromoCode().equals(promoCode)) {
				return pc;
			}
		}
		return null;
	}

}
