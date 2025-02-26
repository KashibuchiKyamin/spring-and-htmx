package com.kashibuchikyamin.htmxdemo.presentation.requestStatusReference;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kashibuchikyamin.htmxdemo.common.ui.PageData;
import com.kashibuchikyamin.htmxdemo.database.entities.OrderRequestStatus;
import com.kashibuchikyamin.htmxdemo.database.mapper.OrderRequestStatusMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequestStatusReferenceService {

	private static int _1ページ分に表示する上限値 = 10;
	private OrderRequestStatusMapper 手配依頼状況テーブル;

	public PageData<OrderRequestStatus> get指定条件によるデータ取得(int pageNum) {

		int count = 手配依頼状況テーブル.get対象データ全件数();
		if (count == 0) {
			return new PageData<>(count, 0, _1ページ分に表示する上限値, 0, 0, new ArrayList<OrderRequestStatus>());
		}

		int startRowNumber = get開始行数(pageNum);
		List<OrderRequestStatus> data = 手配依頼状況テーブル.get1ページ分のデータ(startRowNumber, _1ページ分に表示する上限値);
		return new PageData<>(count, startRowNumber, _1ページ分に表示する上限値, get全ページ数(pageNum), count, data);

	}

	/**
	 * 開始行番号を取得
	 * @param pageNum
	 * @return
	 */
	private int get開始行数(int pageNum) {
		if (pageNum <= 1) {
			return 1;
		}

		return _1ページ分に表示する上限値 * (pageNum - 1) + 1;
	}

	/**
	 * get全ページ数を取得
	 * @param pageNum
	 * @return
	 */
	private int get全ページ数(int totalCount) {
		if (totalCount <= 0) {
			return 0;
		}

		int totalPages = totalCount / _1ページ分に表示する上限値;
		int additionalPage = totalCount % _1ページ分に表示する上限値 >= 1 ? 1 : 0;
		return totalPages + additionalPage;
	}
}
