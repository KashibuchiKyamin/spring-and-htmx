package com.kashibuchikyamin.htmxdemo.common.ui;

import java.util.List;

public record PageData<T>(
		int total,
		int startRowNumber,
		int onePageLimit,
		int currentPage,
		int totalPage,
		List<T> list) {
	/**
	 * 未検索であるか
	 * @return
	 */
	public boolean isUnsearched() {
		if (list == null) {
			return true;
		}
		return false;
	}

	public static PageData<Object> get未検索データ() {
		return new PageData<Object>(0, 0, 0, 0, 0, null);
	}
}
