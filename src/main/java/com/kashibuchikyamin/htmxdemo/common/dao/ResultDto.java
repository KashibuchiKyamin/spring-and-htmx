package com.kashibuchikyamin.htmxdemo.common.dao;

import com.kashibuchikyamin.htmxdemo.common.enums.Result;

public class ResultDto<T> {
	private Result result;
	private String errorCode;
	private String errorMessage;
	private T data;
}
