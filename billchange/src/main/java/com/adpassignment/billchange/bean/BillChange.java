package com.adpassignment.billchange.bean;

import java.util.Map;

public class BillChange {
	
	private String message;
	private Map<String,Integer> billChangeMap;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Integer> getBillChangeMap() {
		return billChangeMap;
	}
	public void setBillChangeMap(Map<String, Integer> billChangeMap) {
		this.billChangeMap = billChangeMap;
	}
	@Override
	public String toString() {
		return "BillChange [message=" + message + ", billChangeMap=" + billChangeMap + "]";
	}
	
	

}
