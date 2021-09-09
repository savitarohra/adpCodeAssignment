package com.adpassignment.billchange.helper;

import org.springframework.stereotype.Component;

import com.adpassignment.billchange.bean.BillChangeValidator;

@Component
public class ValidationHelper {
	
	public BillChangeValidator validateInput(String billAmount) {
		int billAmountInt = 0;
		BillChangeValidator billChangeValidator = new BillChangeValidator();

		if(billAmount!=null) {
			if(!billAmount.isEmpty() && isNumber(billAmount)) {
				if(billAmount.length()>4) {
					billChangeValidator.setValid(false);
					billChangeValidator.setMessage("Please enter amount less than 4 digits");
					return billChangeValidator;
				}
				billAmountInt = Integer.parseInt(billAmount);
			}else {
				billChangeValidator.setValid(false);
				billChangeValidator.setMessage("Please enter valid bill amount. Example of valid Bill amounts are 1,2,5,10,20,50,100");
				return billChangeValidator;
			}
		}		

		if(billAmountInt <= 0) {
			billChangeValidator.setValid(false);
			billChangeValidator.setMessage("Please enter valid bill amount. Example of valid Bill amounts are 1,2,5,10,20,50,100");
			return billChangeValidator;
		}

		if(billAmount.contains(".")) {
			billChangeValidator.setValid(false);
			billChangeValidator.setMessage("Please enter whole number. Example of valid Bill amounts are 1,2,5,10,20,50,100");
			return billChangeValidator;
		}
		billChangeValidator.setValid(true);
		return billChangeValidator;
	}

	private boolean isNumber(String input) {
		for (int i = 0; i < input.length(); i++) {
			if (Character.isDigit(input.charAt(i)) == false)
				return false;
		}
		return true;
	}


}
