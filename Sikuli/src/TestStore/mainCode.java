package TestStore;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.jcraft.jsch.JSchException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class mainCode {
	//static String path = System.getProperty("user.dir");9099
	
	static String projPath = "C:/EclipseWorkspace/Sikuli/";

	public static void main(String[] args) throws BiffException, IOException, FindFailed, InterruptedException,
			ClassNotFoundException, SQLException, JSchException, WriteException, AWTException {
		////////////////////////////////////////////////////////////////////
	
		////////////////////////////// Initialization////////////////////
	
		jxlFunctions jf = new jxlFunctions();
		reportMismatch rm = new reportMismatch();
		Screen s = new Screen();
		methodcollection mc = new methodcollection();
		JourneyWiseExecution jwe = new JourneyWiseExecution();	
		
		
		ArrayList<String> ENV = new ArrayList<String>();
		
		ArrayList<String> JOURNEY = new ArrayList<String>();
		
		ArrayList<String> SKU = new ArrayList<String>();
		ArrayList<String> HUB = new ArrayList<String>();
		ArrayList<String> DC = new ArrayList<String>();
		//ArrayList<String> DCQTY = new ArrayList<String>();
		ArrayList<String> SPOKE = new ArrayList<String>();
		ArrayList<String> HUBQTY = new ArrayList<String>();
		ArrayList<String> SPOKEQTY = new ArrayList<String>();
		ArrayList<String> JOURNEYWISE = new ArrayList<String>();
		ArrayList<String> LOCATION = new ArrayList<String>();
		ArrayList<String> LOCATION1 = new ArrayList<String>();
		ArrayList<String> LOCATION2 = new ArrayList<String>();
		ArrayList<String> LOCATION3 = new ArrayList<String>();
		
		ENV = jxlFunctions.getcolumnValuesFromIventorySheet("ENVIRONMENT");
		JOURNEY = jxlFunctions.getcolumnValuesFromIventorySheet("Journey");

		/////////////////// ALL SKUS///////////////////
		if (JOURNEY.contains("ALL")) {
			
		} else {
			SKU = jxlFunctions.getcolumnValuesFromIventorySheet("SKU");
			HUB = jxlFunctions.getcolumnValuesFromIventorySheet("HUB STORE");
			DC = jxlFunctions.getcolumnValuesFromIventorySheet("DC");
			//DCQTY = jxlFunctions.getcolumnValuesFromIventorySheet("DC Quantity");
			SPOKE = jxlFunctions.getcolumnValuesFromIventorySheet("SPOKE STORE");
			HUBQTY = jxlFunctions.getcolumnValuesFromIventorySheet("HUB Quantity");
			SPOKEQTY = jxlFunctions.getcolumnValuesFromIventorySheet("SPOKE Quantity");
			JOURNEYWISE = jxlFunctions.getcolumnValuesFromIventorySheet("LOAD PRODUCTS JOURNEY WISE");
			LOCATION = jxlFunctions.getcolumnValuesFromIventorySheet("LOCATION");
			
			LOCATION1 = jxlFunctions.getcolumnValuesFromIventorySheet("LOCATION1");
			//INPUT1 = jxlFunctions.getcolumnValuesFromIventorySheet("INPUT1");
			LOCATION2 = jxlFunctions.getcolumnValuesFromIventorySheet("LOCATION2");
			
			//INPUT2 = jxlFunctions.getcolumnValuesFromIventorySheet("INPUT2");
			LOCATION3 = jxlFunctions.getcolumnValuesFromIventorySheet("LOCATION3");
			//INPUT3 = jxlFunctions.getcolumnValuesFromIventorySheet("INPUT3");
		}

		// skulist = jf.getcolumnValuesFromProductSheet("H2S");

		// ArrayList<String> temp = new ArrayList<String>();
		ArrayList<String> uniqueHub1 = new ArrayList<String>();
		ArrayList<String> uniqueSpoke1 = new ArrayList<String>();
		ArrayList<String> uniqueRfc1 = new ArrayList<String>();
		Set<String> uniqueHub = new HashSet<String>(HUB);
		Set<String> uniqueSpoke = new HashSet<String>(SPOKE);
		Set<String> uniqueRfc = new HashSet<String>(DC);
		uniqueHub1.addAll(uniqueHub);
		uniqueSpoke1.addAll(uniqueSpoke);
		uniqueRfc1.addAll(uniqueRfc);
		// Open the Retail J Application
		methodcollection.OpenNewRetailJApplication(s);
		///////////////////////////////////////////////////// initial///////////////////////////////////
		System.out.println(uniqueHub1.size());
		int j, k;
		
			
		//String temph;
		for (j = 0; j < uniqueHub1.size(); j++) {
			// Login from Retail and enter the credentials to Aura
			methodcollection.loginToStore(s, uniqueHub1.get(j));
			for (k = 0; k < HUB.size(); k++) {

				// At Managers Menu
				Thread.sleep(2000);
				if (HUB.get(k).equals(uniqueHub1.get(j))) {
					if (JOURNEY.get(k).equals("1ManH2H")) {
						methodcollection.LoadItems(s, SKU.get(k));
						Thread.sleep(10000);
						methodcollection.managersMenuTOChangeStockQuantities(s);
						methodcollection.ChangeStockQuantitiesTOStockEnquiry(s);
						//jwe.H2HJourney(s, SKU.get(k), HUBQTY.get(k), HUB.get(k), rm);
					}else if (JOURNEY.get(k).equals("Instore")) {
						methodcollection.LoadItems(s, SKU.get(k));
						Thread.sleep(2000);
						methodcollection.managersMenuTOChangeStockQuantities(s);
						jwe.H2HJourney(s, SKU.get(k), HUBQTY.get(k), HUB.get(k), rm ,LOCATION.get(k));
					}else if (JOURNEY.get(k).equals("Instock - Low")) {
						methodcollection.LoadItems(s, SKU.get(k));
						Thread.sleep(2000);
					methodcollection.managersMenuTOChangeStockQuantities(s);
						methodcollection.ChangeStockQuantitiesTOStockEnquiry(s);
						//jwe.H2HJourney(s, SKU.get(k), HUBQTY.get(k), HUB.get(k), rm);
					} else if (JOURNEY.get(k).equals("OOS")) {
						methodcollection.LoadItems(s, SKU.get(k));
						Thread.sleep(2000);
						methodcollection.managersMenuTOChangeStockQuantities(s);
					//jwe.H2HJourney(s, SKU.get(k), HUBQTY.get(k), HUB.get(k), rm);
					} else if (JOURNEY.get(k).equals("H2S")) {
						methodcollection.LoadItems(s, SKU.get(k));
						Thread.sleep(3000);
						methodcollection.managersMenuTOChangeStockQuantities(s);
						jwe.H2HJourney(s, SKU.get(k), HUBQTY.get(k), HUB.get(k), rm ,LOCATION.get(k));
					
					} else if (JOURNEY.get(k).equals("CSO")) {
						methodcollection.LoadItems(s, SKU.get(k));
						Thread.sleep(2000);
						methodcollection.managersMenuTOChangeStockQuantities(s);
						methodcollection.ChangeStockQuantitiesTOStockEnquiry(s);
						//jwe.H2HJourney(s, SKU.get(k), HUBQTY.get(k), HUB.get(k), rm);
					} else if (JOURNEY.get(k).equals("RFC")) {
						methodcollection.LoadItems(s, SKU.get(k));
						Thread.sleep(2000);
						methodcollection.managersMenuTOChangeStockQuantities(s);
						methodcollection.ChangeStockQuantitiesTOStockEnquiry(s);
						//jwe.H2HJourney(s, SKU.get(k), HUBQTY.get(k), HUB.get(k), rm);
					} else if (JOURNEY.get(k).equals("Conveniance")) {
					} else if (JOURNEY.get(k).equals("PreOrder")) {
						methodcollection.LoadItems(s, SKU.get(k));
						Thread.sleep(2000);
						methodcollection.managersMenuTOChangeStockQuantities(s);
						methodcollection.ChangeStockQuantitiesTOStockEnquiry(s);
						//jwe.H2HJourney(s, SKU.get(k), HUBQTY.get(k), HUB.get(k), rm);
					}
				}
			}

			s.type(Key.ALT);
			s.type("f");
			s.type("x");
			s.type(Key.ENTER);
			s.click(projPath + "Img/Aura/Login/AuraOp.PNG");
			Thread.sleep(1000);
			s.click(projPath + "Img/Aura/Login/AuraAnita.PNG");

		}	

			int l, m ;
		// String temph;
		for (l = 0; l<uniqueSpoke1.size(); l++) {
			// Login from Retail and enter the credentials to Aura
			methodcollection.loginToStore(s, uniqueSpoke1.get(l));
			for (m = 0; m < SPOKE.size(); m++) {

				// At Managers Menu
				Thread.sleep(2000);
				if (SPOKE.get(m).equals(uniqueSpoke1.get(l))) {
					if (JOURNEY.get(m).equals("1ManH2H")) {

					} else if (JOURNEY.get(m).equals("Instore")) {
						methodcollection.LoadItems(s, SKU.get(m));
						Thread.sleep(2000);
						methodcollection.managersMenuTOChangeStockQuantities(s);
						jwe.H2HJourney(s, SKU.get(m), SPOKEQTY.get(m), SPOKE.get(m), rm ,LOCATION.get(m));
					} else if (JOURNEY.get(m).equals("Instock - Low")) {
						methodcollection.LoadItems(s, SKU.get(m));
						Thread.sleep(2000);
						methodcollection.managersMenuTOChangeStockQuantities(s);
						methodcollection.ChangeStockQuantitiesTOStockEnquiry(s);
						//jwe.H2HJourney(s, SKU.get(m), SPOKEQTY.get(m), SPOKE.get(m), rm);
					} else if (JOURNEY.get(m).equals("OOS")) {
						methodcollection.LoadItems(s, SKU.get(m));
						Thread.sleep(2000);
						methodcollection.managersMenuTOChangeStockQuantities(s);
						methodcollection.ChangeStockQuantitiesTOStockEnquiry(s);
						//jwe.H2HJourney(s, SKU.get(m), SPOKEQTY.get(m), SPOKE.get(m), rm);
					} else if (JOURNEY.get(m).equals("H2S")) {
						methodcollection.LoadItems(s, SKU.get(m));
						Thread.sleep(2000);
						methodcollection.managersMenuTOChangeStockQuantities(s);
						methodcollection.ChangeStockQuantitiesTOStockEnquiry(s);
					} else if (JOURNEY.get(m).equals("CSO")) {
						methodcollection.LoadItems(s, SKU.get(m));
						Thread.sleep(2000);
						methodcollection.managersMenuTOChangeStockQuantities(s);
						methodcollection.ChangeStockQuantitiesTOStockEnquiry(s);
						//jwe.H2HJourney(s, SKU.get(m), SPOKEQTY.get(m), SPOKE.get(m), rm);
					} else if (JOURNEY.get(m).equals("RFC")) {
						methodcollection.LoadItems(s, SKU.get(m));
						Thread.sleep(2000);
						methodcollection.managersMenuTOChangeStockQuantities(s);
						methodcollection.ChangeStockQuantitiesTOStockEnquiry(s);
						//jwe.H2HJourney(s, SKU.get(m), SPOKEQTY.get(m), SPOKE.get(m), rm);
					} else if (JOURNEY.get(m).equals("Conveniance")) {
						methodcollection.LoadItems(s, SKU.get(m));
						Thread.sleep(2000);
						methodcollection.managersMenuTOChangeStockQuantities(s);
						methodcollection.ChangeStockQuantitiesTOStockEnquiry(s);
						//jwe.H2HJourney(s, SKU.get(m), SPOKEQTY.get(m), SPOKE.get(m), rm);
					} else if (JOURNEY.get(m).equals("PreOrder")) {
						methodcollection.LoadItems(s, SKU.get(m));
						Thread.sleep(2000);
						methodcollection.managersMenuTOChangeStockQuantities(s);
						methodcollection.ChangeStockQuantitiesTOStockEnquiry(s);
						//jwe.H2HJourney(s, SKU.get(m), SPOKEQTY.get(m), SPOKE.get(m), rm);
					}
				}
			}

			s.type(Key.ALT);
			s.type("f");
			s.type("x");
			s.type(Key.ENTER);
			s.click(projPath + "Img/Aura/Login/AuraOp.PNG");
			Thread.sleep(1000);
			s.click(projPath + "Img/Aura/Login/AuraAnita.PNG");

		}
/*
		int d, e;
		//String temp;

		for (d = 0; d < uniqueRfc1.size(); d++) {
			// Login from Retail and enter the credentials to Aura
			methodcollection.loginToStore(s, uniqueHub1.get(d));
			for (e = 0; e < DC.size(); e++) {

				// At Managers Menu
				Thread.sleep(2000);
				if (DC.get(e).equals(uniqueRfc1.get(d))) {
					if (JOURNEY.get(e).equals("RFC")) {
						System.out.println(SKU.get(e));
						methodcollection.LoadItems(s, SKU.get(e));
						// Thread.sleep(10000);
						methodcollection.managersMenuTOChangeStockQuantities(s);
						methodcollection.ChangeStockQuantitiesTOStockEnquiry(s);
						//jwe.H2HJourney(s, SKU.get(e), HUBQTY.get(e), DC.get(e), rm);
					}
				}
			}
			s.type(Key.ALT);
			s.type("f");
			s.type("x");
			s.type(Key.ENTER);
			s.click(projPath + "Img/Aura/Login/AuraOp.PNG");
			Thread.sleep(1000);
			s.click(projPath + "Img/Aura/Login/AuraAnita.PNG");

		}*/

		reportMismatch.closeConnection();
	}
	
}

