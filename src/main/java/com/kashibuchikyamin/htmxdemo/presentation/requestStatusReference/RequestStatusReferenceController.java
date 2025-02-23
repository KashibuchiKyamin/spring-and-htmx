package com.kashibuchikyamin.htmxdemo.presentation.requestStatusReference;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/requestStatusReference")
public class RequestStatusReferenceController {
	@GetMapping
	public String 画面を表示する() {

		// Viewに設定する値はhtmxによる部品単位でセットする
		// - テーブルの内容
		// - それ以外
		return "RequestStatusReferencePage";
	}

	@GetMapping("/table")
	public String 指定条件で一覧を表示する() {
		return "RequestStatusReferencePage :: results";
	}
}
