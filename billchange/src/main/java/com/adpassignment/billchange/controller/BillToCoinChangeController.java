package com.adpassignment.billchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adpassignment.billchange.bean.BillChange;
import com.adpassignment.billchange.bean.BillChangeValidator;
import com.adpassignment.billchange.helper.BillToCoinChangeHelper;
import com.adpassignment.billchange.helper.ValidationHelper;

/**
 * REST API to provide change for given bill
 * @author Savita Rohra
 *
 */
@RestController
public class BillToCoinChangeController {

	@Autowired
	BillToCoinChangeHelper billToCoinChangeHelper;
	
	@Autowired
	ValidationHelper validationHelper;

	@GetMapping(value="/billChange")	
	public BillChange billChange(@RequestParam("billAmount") String billAmount) {
		
		/** Performing input validations **/
		BillChangeValidator billChangeValidator= validationHelper.validateInput(billAmount);
		
		if(billChangeValidator.isValid()) {
			return billToCoinChangeHelper.billChange(Integer.parseInt(billAmount));
		}else {
			BillChange billChange = new BillChange();
			billChange.setMessage(billChangeValidator.getMessage());
			return billChange;
		}

	}

	/*
	@GetMapping(value="/billChangeMostCoins")
	public BillChange billChangeMostCoins(@RequestParam("billAmount") String billAmount) {

		BillChangeValidator billChangeValidator= billToCoinChangeHelper.validateInput(billAmount);
		if(billChangeValidator.isValid()) {
			return billToCoinChangeHelper.billChangeMostCoins(Integer.parseInt(billAmount));
		}else {
			BillChange billChange = new BillChange();
			billChange.setMessage(billChangeValidator.getMessage());
			return billChange;
		}

	}*/



}
