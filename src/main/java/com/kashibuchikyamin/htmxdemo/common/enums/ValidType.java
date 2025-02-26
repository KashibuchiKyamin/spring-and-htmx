package com.kashibuchikyamin.htmxdemo.common.enums;

/**
 * 有効/無効区分
 */
public enum ValidType {
	有効, 無効;
	
	
	public boolean is有効() {
		return this == 有効;
	}

	public boolean is無効() {
		return this == 無効;
	}
}
