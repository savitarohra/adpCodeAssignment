package com.adpassignment.billchange;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.adpassignment.billchange.bean.BillChange;
import com.adpassignment.billchange.helper.BillToCoinChangeHelper;


@SpringBootTest
class BillchangeApplicationTests {

	//TODO Move these constants to common ENUM and make them final (similar to service class)
	private String QUARTER_CONST = "Quarters";
	private String TENCENTS_CONST = "Ten cents";
	private String FIVECENTS_CONST = "Five cents";
	private String ONECENTS_CONT = "One cents";

	private final String NO_COINS_LEFT_MSG = "Sorry! Not enough coins available for change.";
	
	@Autowired
	BillToCoinChangeHelper billToCoinChangeHelper;

	@Test
	@Order(1) 
	public void testBillChange1_25() throws Exception{

		System.out.println("Testing testBillChange25");

		BillChange billChange = billToCoinChangeHelper.billChange(25); 
		Map<String,Integer> actual = billChange.getBillChangeMap();

		Map<String, Integer> expected = new HashMap<String, Integer>();
		expected.put(QUARTER_CONST,100 );
		expected.put(TENCENTS_CONST, 0);
		expected.put(FIVECENTS_CONST, 0); 
		expected.put(ONECENTS_CONT, 0);

		Assert.assertEquals(expected, actual);

	}

	@Test
	@Order(2) 
	public void testBillChange2_10() throws Exception{

		BillChange billChange = billToCoinChangeHelper.billChange(10);
		Map<String,Integer> actual = billChange.getBillChangeMap();

		Map<String, Integer> expected = new HashMap<String, Integer>();
		expected.put(QUARTER_CONST, 0);
		expected.put(TENCENTS_CONST, 100);
		expected.put(FIVECENTS_CONST, 0); 
		expected.put(ONECENTS_CONT, 0);

		Assert.assertEquals(expected, actual);

	}

	@Test
	@Order(3) 
	public void testBillChange3_10() throws Exception{

		BillChange billChange = billToCoinChangeHelper.billChange(10); 
		String actualMsg = billChange.getMessage();
		String expectedMsg = NO_COINS_LEFT_MSG;

		Assert.assertEquals(expectedMsg, actualMsg);

	}

	/* 
	 * @Test void contextLoads() { }
	 */

}
