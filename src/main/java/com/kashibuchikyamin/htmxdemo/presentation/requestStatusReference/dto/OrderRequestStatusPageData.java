package com.kashibuchikyamin.htmxdemo.presentation.requestStatusReference.dto;

import java.util.List;

import com.kashibuchikyamin.htmxdemo.database.entities.OrderRequestStatus;

public record OrderRequestStatusPageData(
		int total,
		int startRowNumber,
		int onePageLimit,
		int currentPage,
		int totalPage,
		List<OrderRequestStatus> list) {
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

	public static OrderRequestStatusPageData get未検索データ() {
		return new OrderRequestStatusPageData(0, 0, 0, 0, 0, null);
	}
}
