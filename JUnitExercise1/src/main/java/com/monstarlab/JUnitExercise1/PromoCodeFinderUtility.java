package com.monstarlab.JUnitExercise1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Santos, Francis & Cruz, Jason Anthony
 * 
 *         A utility that simply gets a promo code from the list of promo codes
 */
public class PromoCodeFinderUtility {

	public static Promo getPromoCode(List<Promo> promoCodes, String promoCode) {
		for (Promo pc : promoCodes) {
			if (pc.getPromoCode().equals(promoCode)) {
				return pc;
			}
		}
		return null;
	}

	public static Promo getPromoCode(HashMap<String, Promo> promoCodes, String promoCode) {
		for (Map.Entry<String, Promo> entrySet : promoCodes.entrySet()) {
			String key = entrySet.getKey();
			Promo value = entrySet.getValue();

			if (key.equals(promoCode)) {
				return value;
			}


		}
		return null;
	}

}