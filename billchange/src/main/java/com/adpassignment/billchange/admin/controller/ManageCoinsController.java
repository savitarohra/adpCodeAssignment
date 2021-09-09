package com.adpassignment.billchange.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adpassignment.billchange.bean.AllCoins;
import com.adpassignment.billchange.helper.BillToCoinChangeHelper;

/**
 * REST API for admin to reset/change count of available coins
 * @author Savita Rohra
 *
 */
@RestController
public class ManageCoinsController {
	
	@Autowired
	BillToCoinChangeHelper billToCoinChangeHelper;
	
	@GetMapping(value="/adminChangeTotalCoins")
	public boolean changeTotalCoins(@RequestParam("quarter") int quarter,
			@RequestParam("tencents") int tencents,
			@RequestParam("fivecents") int fivecents,
			@RequestParam("onecents") int onecents) {
		
		AllCoins allCoins = new AllCoins();
		allCoins.setQuarterCount(quarter);
		allCoins.setTenCentsCount(tencents);
		allCoins.setFiveCentsCount(fivecents);
		allCoins.setOneCentsCount(onecents);
		
		return billToCoinChangeHelper.changeTotalCoins(allCoins);
		
	}

}
