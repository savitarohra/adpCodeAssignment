package com.adpassignment.billchange.bean;

public class AllCoins {
	
	private int quarterCount;
	private int tenCentsCount;
	private int fiveCentsCount;
	private int oneCentsCount;
	
	public int getQuarterCount() {
		return quarterCount;
	}
	public void setQuarterCount(int quarterCount) {
		this.quarterCount = quarterCount;
	}
	public int getTenCentsCount() {
		return tenCentsCount;
	}
	public void setTenCentsCount(int tenCentsCount) {
		this.tenCentsCount = tenCentsCount;
	}
	public int getFiveCentsCount() {
		return fiveCentsCount;
	}
	public void setFiveCentsCount(int fiveCentsCount) {
		this.fiveCentsCount = fiveCentsCount;
	}
	public int getOneCentsCount() {
		return oneCentsCount;
	}
	public void setOneCentsCount(int oneCentsCount) {
		this.oneCentsCount = oneCentsCount;
	}
	
	
	
	// MOst amount means, suggest user that he can take change for only that much max amount //TODO

}
