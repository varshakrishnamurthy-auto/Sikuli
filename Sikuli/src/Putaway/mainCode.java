package Putaway;

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
                //static String path = System.getProperty("user.dir");
                static String projPath = "C:/EclipseWorkspace/Sikuli/" ;


public static void main(String[] args) throws BiffException, IOException, FindFailed, InterruptedException,
                                                ClassNotFoundException, SQLException, JSchException, WriteException, AWTException {
                                ////////////////////////////////////////////////////////////////////
                                //////////////////////////////////// Initialization////////////////////
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
                                ArrayList<String> DCQTY = new ArrayList<String>();
                                ArrayList<String> SPOKE = new ArrayList<String>();
                                ArrayList<String> HUBQTY = new ArrayList<String>();
                                ArrayList<String> SPOKEQTY = new ArrayList<String>();

                                ArrayList<String> JOURNEYWISE = new ArrayList<String>();
                                ArrayList<String> LOCATION = new ArrayList<String>();
                                ArrayList<String> DATE = new ArrayList<String>();
                                ENV = jxlFunctions.getcolumnValuesFromIventorySheet("ENVIRONMENT");
                                JOURNEY = jxlFunctions.getcolumnValuesFromIventorySheet("Journey");
                                if (JOURNEY.contains("ALL")) {
                        			
                		} else {
                        			SKU = jxlFunctions.getcolumnValuesFromIventorySheet("SKU");
                        			HUB = jxlFunctions.getcolumnValuesFromIventorySheet("HUB STORE");
                        			DC = jxlFunctions.getcolumnValuesFromIventorySheet("DC");
                        			//DCQTY = jxlFunctions.getcolumnValuesFromIventorySheet("DC Quantity");
                        			SPOKE = jxlFunctions.getcolumnValuesFromIventorySheet("SPOKE STORE");
                        			System.out.println("SPOKESIZE" + SPOKE.size() );
                        			HUBQTY = jxlFunctions.getcolumnValuesFromIventorySheet("HUB Quantity");
                        			SPOKEQTY = jxlFunctions.getcolumnValuesFromIventorySheet("SPOKE Quantity");
                        			JOURNEYWISE = jxlFunctions.getcolumnValuesFromIventorySheet("LOAD PRODUCTS JOURNEY WISE");
                        			LOCATION = jxlFunctions.getcolumnValuesFromIventorySheet("LOCATION");
                        			DATE = jxlFunctions.getcolumnValuesFromIventorySheet("DATE");
                        		}
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
                                System.out.println(uniqueSpoke1.size());
                                int l, m;
                                for (l = 0; l < uniqueSpoke1.size(); l++) {
                                                // Login from Retail and enter the credentials to Aura
                                	methodcollection.loginToStore(s, uniqueSpoke1.get(l));
                                
                                                for (m = 0; m < SPOKE.size(); m++) {
                                                                // At Managers Menu
                                                               Thread.sleep(2000);
                                                               System.out.println("Test");
                                                      if (SPOKE.get(m).equals(uniqueSpoke1.get(l))) {
                                                    	  	System.out.println("test1");
                                                                  if (JOURNEY.get(m).equals("H2S")) 
															{
                                                                	  methodcollection.managersMenuTOChangeTransferIn(s);
																     jwe.H2HJourney(s, SKU.get(m), SPOKEQTY.get(m), SPOKE.get(m), rm ,DATE.get(m));
                                                                        methodcollection.MenuToMoveBack(s); 
                                                                        System.out.println("Test2");
															}
                                                                  
                                                                
                                                                                
                                            /*    s.type(Key.ALT);
              
                                                s.type("f");
                                                s.type("x");
                                                s.type(Key.ENTER);
                                                s.click(projPath + "Img/Aura/Login/AuraOp.PNG");
                                                Thread.sleep(1000);
                                                s.click(projPath + "Img/Aura/Login/AuraAnita.PNG");*/

                 
                 }
                                                }
                                }
                }
}