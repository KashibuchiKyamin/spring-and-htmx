package com.kashibuchikyamin.htmxdemo.presentation.requestStatusReference;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kashibuchikyamin.htmxdemo.common.ui.PageData;
import com.kashibuchikyamin.htmxdemo.database.entities.OrderRequestStatus;

import lombok.AllArgsConstructor;

/**
 * 案件依頼状況参照画面
 */
@Controller
@AllArgsConstructor
@RequestMapping("/requestStatusReference")
public class RequestStatusReferenceController {

	private RequestStatusReferenceService 案件依頼状況参照画面ビジネスロジック;

	@GetMapping
	public String get画面(Model model) {

		// Viewに設定する値はhtmxによる部品単位でセットする
		// - テーブルの内容
		// - それ以外
		model.addAttribute("listData", PageData.get未検索データ());
		return "requestStatusReferencePage";
	}

	@GetMapping("/table")
	public String get指定条件による一覧情報(Model model) {
		PageData<OrderRequestStatus> listData = 案件依頼状況参照画面ビジネスロジック.get指定条件によるデータ取得(1);// とりあえず1ページ目固定
		model.addAttribute("listData", listData);
		return "requestStatusReferencePage :: results";
	}
}
