package com.adpassignment.billchange.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adpassignment.billchange.bean.AllCoins;
import com.adpassignment.billchange.bean.BillChange;
import com.adpassignment.billchange.service.BillToCoinChangeService;

/**
 * Purpose of this class is to perform any operations/input data manipulations that are required before calling service class.
 * @author Savita Rohra
 *
 */
@Component
public class BillToCoinChangeHelper {

	@Autowired
	BillToCoinChangeService billToCoinChangeService;

	public BillChange billChange(int billAmount) {
		return billToCoinChangeService.billChange(billAmount);
	}

	public boolean changeTotalCoins(AllCoins allCoins) {
		return billToCoinChangeService.changeTotalCoins(allCoins);
	}
	
	/*public BillChange billChangeMostCoins(int billAmount) {
		return billToCoinChangeService.billChangeWithMostCoins(billAmount);
	}*/

}
