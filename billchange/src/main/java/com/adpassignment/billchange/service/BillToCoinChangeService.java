package com.adpassignment.billchange.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.adpassignment.billchange.bean.AllCoins;
import com.adpassignment.billchange.bean.BillChange;

/**
 * Purpose of this class to perform business logic
 * @author Savita Rohra
 *
 */
@Service
public class BillToCoinChangeService {

	// Assuming 100 coins of each type. However this can be changed by admin at run time. Refer ManageCoinsController.changeTotalCoins
	private static int quarterCount = 100;
	private static int tenCentsCount = 100;
	private static int fiveCentsCount = 100;
	private static int oneCentsCount = 100;

	//TODO Move these constants to common ENUM file	
	private float QUARTER = 0.25f;
	private float TENCENTS = 0.10f;
	private float FIVECENTS = 0.05f;
	private float ONECENT = 0.01f;

	private final String QUARTER_CONST = "Quarters";
	private final String TENCENTS_CONST = "Ten cents";
	private final String FIVECENTS_CONST = "Five cents";
	private final String ONECENTS_CONT = "One cents";
	private final String NO_COINS_LEFT_MSG = "Sorry! Not enough coins available for change.";
	private final String COINS_CHANGE_SUCCESS_MSG = "Bill change done successfully";

	/**
	 * This method provides change for the given bill
	 * @param billAmount
	 * @return BillChange object. It contains number of coins with types and success/failure message
	 */
	public BillChange billChange(int billAmount) {

		Map<String, Integer> coinNeededMap = new HashMap<String, Integer>();
		resetCoinMap(coinNeededMap); //TODO

		BillChange billChange = new BillChange();
		boolean changeAvailable = false;

		if(quarterCount !=0){
			float numberOfQuarterCoinsNeeded = billAmount/ QUARTER;
			changeAvailable = checkQuarter(coinNeededMap,numberOfQuarterCoinsNeeded);
		}		
		else if(quarterCount == 0) {
			/*** If Quarters are finished, check with ten cents **/
			float numberOfTenCentCoinsNeeded = billAmount/TENCENTS;
			changeAvailable = checkTenCents(coinNeededMap,numberOfTenCentCoinsNeeded);
		}else if(quarterCount == 0 && tenCentsCount == 0) {
			/*** If Quarters and ten cents are finished, check with five cents **/
			float numberOfFiveCentCoinsNeeded = billAmount/FIVECENTS;
			changeAvailable = checkFiveCents(coinNeededMap, numberOfFiveCentCoinsNeeded);
		}else if(quarterCount == 0 && tenCentsCount == 0 && fiveCentsCount ==0) {
			/*** If Quarters, ten cents and five cents are finished, check one cents**/
			float numberOfOneCentCoinsNeeded = billAmount/FIVECENTS;
			changeAvailable = checkOneCents(coinNeededMap, numberOfOneCentCoinsNeeded);
		}else {
			/*** All coins are finished ***/
			System.out.println("Sorry no more coins left");
			changeAvailable = false;
		}

		System.out.println("Final coinNeededMap after traversing through all coin checks: "+coinNeededMap);
		if(changeAvailable) {
			billChange.setMessage(COINS_CHANGE_SUCCESS_MSG);
		}else {
			resetCoinMap(coinNeededMap); // We can reset the count, if we don't want to send how many coins are left //TODO
			billChange.setMessage(NO_COINS_LEFT_MSG);
		}
		billChange.setBillChangeMap(coinNeededMap);

		return billChange;
	}

	private void resetCoinMap(Map<String, Integer> coinNeededMap) {
		coinNeededMap.put(QUARTER_CONST,0);
		coinNeededMap.put(TENCENTS_CONST,0);
		coinNeededMap.put(FIVECENTS_CONST,0);
		coinNeededMap.put(ONECENTS_CONT,0);

	}

	/**
	 * Method to check available quarters and add corresponding count to the map accordingly
	 * @param coinNeededMap
	 * @param numberOfQuarterCoinsNeeded
	 * @return boolean if sufficient change is available or not
	 */
	private boolean checkQuarter(Map<String, Integer> coinNeededMap,float numberOfQuarterCoinsNeeded) {
		boolean changeAvailable = false;

		if(numberOfQuarterCoinsNeeded > quarterCount) { //Example Suppose quarterCount =6

			/**  Don't have enough quarters because numberOfQuarterCoinsNeeded is more than what we have in quarterCount **/
			System.out.println("numberOfQuarterCoinsNeeded: "+ numberOfQuarterCoinsNeeded);

			//Calculate how many more quarter is needed
			float moreQuarterCoinsNeeded = numberOfQuarterCoinsNeeded - quarterCount; // Example 8-6 = 2. So need 2 more coins of .25 quarter. Since we don't have quarter coints, we will try to get it from ten cents

			// Add available quarter coin count to the map
			coinNeededMap.put(QUARTER_CONST, quarterCount);		

			// Check how much amount (not coins) is more needed
			float moreAmountNeeded_NoQ = moreQuarterCoinsNeeded*QUARTER; //Example 2*.25 = Need .50 cents more

			// Check how many more 10 cents needed
			float numberOfTenCentCoinsNeeded = moreAmountNeeded_NoQ/TENCENTS; //Example .50/.10 =5. So need 5 coins of 10 cents

			// Now check of we have sufficient ten cents available or not
			changeAvailable = checkTenCents(coinNeededMap,numberOfTenCentCoinsNeeded);

			//Finally if we could get complete change successfully and we have reached till here, means all quarters are utilized
			if(changeAvailable) {
				quarterCount = 0; // Used all of quarters
			}

		}else {
			/** We have sufficient Quarters **/
			float quarterCoinsLeft = quarterCount - numberOfQuarterCoinsNeeded; // 100-8 = 92

			coinNeededMap.put(QUARTER_CONST,(int)numberOfQuarterCoinsNeeded);
			quarterCount = (int)quarterCoinsLeft;
			System.out.println("We have sufficient quarters!");
			System.out.println("Quarter coins needed: "+numberOfQuarterCoinsNeeded);
			System.out.println("Quarter coins remaning: "+quarterCount);
			changeAvailable = true;

		}
		return changeAvailable;

	}

	/**
	 * Method to check available ten cents and add them to map accordingly
	 * @param coinNeededMap
	 * @param numberOfTenCentCoinsNeeded
	 * @return boolean if sufficient change is available or not
	 */
	private boolean checkTenCents(Map<String, Integer> coinNeededMap, float numberOfTenCentCoinsNeeded) {
		boolean changeAvailable = false;

		if(numberOfTenCentCoinsNeeded > tenCentsCount) { //Example Suppose tenCentsCount =2

			/**  Don't have enough ten cents because numberOfTenCentCoinsNeeded is more than what we have in tenCentsCount **/
			System.out.println("numberOfTenCentCoinsNeeded: "+numberOfTenCentCoinsNeeded);

			//Don't have enough 10 cents
			float moreTenCentsCoinNeeded = numberOfTenCentCoinsNeeded - tenCentsCount; //Example 5 -2 = 3. SO need 3 coins of 10 cents

			// Add available ten cents coin count to the map
			coinNeededMap.put(TENCENTS_CONST, tenCentsCount);	

			// Check how much AMOUNT (not coins) is more needed
			float moreAmountNeeded_NoQ_NoT = moreTenCentsCoinNeeded*TENCENTS;	//Example 3*.10 = Need .30 cents more // This will give doller amout more needed

			//Check how many 5 cents needed
			float numberOfFiveCentCoinsNeeded = moreAmountNeeded_NoQ_NoT/FIVECENTS;  //Example 0.30/0.5 . SO need 6 coins of 0.5 cents

			// Now check of we have sufficient 5 cents available or not
			changeAvailable = checkFiveCents(coinNeededMap, numberOfFiveCentCoinsNeeded);

			if(changeAvailable) {
				tenCentsCount = 0; //Used all of ten cents
			}

		}else {
			/** We have sufficient 10 cents **/
			float tenCentCoinsLeft = tenCentsCount - numberOfTenCentCoinsNeeded;

			// Add available ten cents coin count to the map
			coinNeededMap.put(TENCENTS_CONST,(int)numberOfTenCentCoinsNeeded);
			tenCentsCount = (int)tenCentCoinsLeft;

			changeAvailable = true;
			System.out.println("We have sufficient Ten Cents");
		}
		return changeAvailable;

	}

	/**
	 * Method to check available five cents and add them to map accordingly
	 * @param coinNeededMap
	 * @param numberOfFiveCentCoinsNeeded
	 * @return boolean if sufficient change is available or not
	 */
	private boolean checkFiveCents(Map<String, Integer> coinNeededMap, float numberOfFiveCentCoinsNeeded) {

		boolean changeAvailable = false;

		if(numberOfFiveCentCoinsNeeded > fiveCentsCount) {//Example- Suppose fiveCentsCount = 4

			/**  Don't have enough five cents because numberOfFiveCentCoinsNeeded is more than what we have in fiveCentsCount **/
			float moreFiveCentsCoinNeeded = numberOfFiveCentCoinsNeeded - fiveCentsCount; //Example 6-4 = 2. So need 2 more coins of 0.5 cents

			// Add available five cents coin count to the map
			coinNeededMap.put(FIVECENTS_CONST, fiveCentsCount);		

			// Check how much AMOUNT (not coins) is more needed
			float moreAmountNeeded_NoQ_NoT_NoF =  moreFiveCentsCoinNeeded*FIVECENTS;

			// Check how many 1 cents needed
			float numberOfOneCentCoinsNeeded = moreAmountNeeded_NoQ_NoT_NoF /ONECENT;

			// Now check of we have sufficient One cents available or not
			changeAvailable = checkOneCents(coinNeededMap,numberOfOneCentCoinsNeeded);

			if(changeAvailable) {
				fiveCentsCount = 0; //Used all of 5 cents
			}

		}else {
			/** We have sufficient 5 cents **/

			float fiveCentCoinsLeft = fiveCentsCount - numberOfFiveCentCoinsNeeded;
			
			// Add available five cents coin count to the map
			coinNeededMap.put(FIVECENTS_CONST,(int)numberOfFiveCentCoinsNeeded);
			fiveCentsCount = (int)fiveCentCoinsLeft;
			
			System.out.println("We have sufficient Five Cents");
			changeAvailable = true;
		}

		return changeAvailable;

	}

	/**
	 * Method to check available one cents and add them to map accordingly
	 * @param coinNeededMap
	 * @param numberOfOneCentCoinsNeeded
	 * @return boolean if sufficient change is available or not
	 */
	private boolean checkOneCents(Map<String, Integer> coinNeededMap,float numberOfOneCentCoinsNeeded ) {
		boolean changeAvailable = false;
		
		if(numberOfOneCentCoinsNeeded > oneCentsCount) {
			
			//TODO -We can also throw exception and handle accordingly
			float moreOneCentCoinNeeded = numberOfOneCentCoinsNeeded - oneCentsCount;
			System.out.println("SORRY!! Dont have enough coins. Need more one cent coins: "+moreOneCentCoinNeeded);
			
			// Add available one cents coin count to the map. It is actually not needed because we are throwing error anyways. But maintaining the count to meet another possible requirement
			coinNeededMap.put(ONECENTS_CONT, oneCentsCount);
			changeAvailable = false;
			//oneCentsCount = 0;

		}else {
			// Add available one cents coin count to the map
			coinNeededMap.put(ONECENTS_CONT, oneCentsCount);
			oneCentsCount = 0;//Used all of 1 cents
			changeAvailable = true;
		}
		return changeAvailable;

	}

	/**
	 * Only for an admin to perform. To be able to reset/change total number coins available
	 * @param allCoins
	 * @return boolean if coin change was successful or not
	 */
	public boolean changeTotalCoins(AllCoins allCoins) {
		
		quarterCount = allCoins.getQuarterCount();
		tenCentsCount = allCoins.getTenCentsCount();
		fiveCentsCount = allCoins.getFiveCentsCount();
		oneCentsCount = allCoins.getOneCentsCount();

		System.out.println("IMP: Admin changed master coin count successfully");

		return true;
	}
	

	/*public BillChange billChangeWithMostCoins(int billAmount) {
		QUARTER = 0.01f;
		TENCENTS = 0.05f;
		FIVECENTS = 0.10f;
		ONECENT = 0.01f;

		Map<String, Integer> mostCoinNeededMap = new HashMap<String, Integer>();

		BillChange billChange = billChange(billAmount);

		Set<Entry<String, Integer>> mapEntry = billChange.getBillChangeMap().entrySet();
		for (Entry<String, Integer> entry : mapEntry) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			if(key.equals(QUARTER_CONST)) {
				mostCoinNeededMap.put(ONECENTS_CONT, value);
			}else if(key.equals(TENCENTS_CONST)) {
				mostCoinNeededMap.put(FIVECENTS_CONST, value);
			}else if(key.equals(FIVECENTS_CONST)) {
				mostCoinNeededMap.put(TENCENTS_CONST, value);
			}else if(key.equals(ONECENTS_CONT)) {
				mostCoinNeededMap.put(QUARTER_CONST, value);
			}
		}

		BillChange billChangeWithMostCoins = new BillChange();
		billChangeWithMostCoins.setBillChangeMap(mostCoinNeededMap);
		billChangeWithMostCoins.setMessage(billChange.getMessage());
		return billChangeWithMostCoins;

	}*/

}
