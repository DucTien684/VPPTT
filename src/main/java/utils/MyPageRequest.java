package utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class MyPageRequest {
	public static final int PAGE_SIZE = 12;
	private static final String ORDER_PATTERN = ",";

	public static PageRequest of(int page) {
		return PageRequest.of(page, PAGE_SIZE);
	}

	public static PageRequest of(int page, int size) {
		return PageRequest.of(page, size);
	}

	public static PageRequest of(int page, int size, Sort sort) {
		return PageRequest.of(page, size, sort);
	}

	public static PageRequest of(int page, Sort sort) {
		return PageRequest.of(page, PAGE_SIZE, sort);
	}

	public static PageRequest of(int page, String orderBy) {
		if (StringUtils.isNotBlank(orderBy)) {
			String[] orders = orderBy.split(ORDER_PATTERN);
			if ("asc".equalsIgnoreCase(orders[1])) {
				return PageRequest.of(page, PAGE_SIZE, Sort.by(orders[0]).ascending());
			} else if ("desc".equalsIgnoreCase(orders[1])) {
				return PageRequest.of(page, PAGE_SIZE, Sort.by(orders[0]).descending());
			}
		}
		return PageRequest.of(page, PAGE_SIZE);
	}
}
