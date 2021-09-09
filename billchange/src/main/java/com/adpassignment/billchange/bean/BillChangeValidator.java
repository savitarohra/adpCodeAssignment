package com.adpassignment.billchange.bean;

public class BillChangeValidator {
	
	private String message;
	private boolean isValid;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	@Override
	public String toString() {
		return "BillChangeValidator [message=" + message + ", isValid=" + isValid + "]";
	}
	
	

}
