package utils;

import org.apache.commons.lang3.StringUtils;

public class PriceUtil {

	private static final String PRICE_PATTERN = "-";

	public static Double getStart(String price) {
		if (StringUtils.isNotBlank(price)) {
			String[] pricePattern = price.split(PRICE_PATTERN);
			if (pricePattern.length > 0 && StringUtils.isNotBlank(pricePattern[0])) {
				return Double.valueOf(pricePattern[0]) * 1000;
			}
		}
		return null;
	}

	public static Double getEnd(String price) {
		if (StringUtils.isNotBlank(price)) {
			String[] pricePattern = price.split(PRICE_PATTERN);
			if (pricePattern.length == 2 && StringUtils.isNotBlank(pricePattern[1])) {
				return Double.valueOf(pricePattern[1]) * 1000;
			}
		}
		return null;
	}

}
