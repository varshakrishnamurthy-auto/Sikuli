package AuraPack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class SOBJourneyWise {
	
	static String path = System.getProperty("user.dir");
    static String projPath = "C:\\EclipseWorkspace\\Sikuli\\Data";
    public void SOBJourney(Screen s, String sku, String qty, String store, reportMismatch rm , String loc1)
		throws FindFailed, InterruptedException, JSchException, IOException, BiffException, WriteException {
                    jxlFunctions jf = new jxlFunctions();
                    // String hostname = "172.28.44.199";
                    // String username = "argosdev";
                    // String password = "m1ck3y";
                    String hostname = jf.getStoreInfoFromBackEnd("IP", store);
                    String username = jf.getStoreInfoFromBackEnd("Username", store);
                    String password = jf.getStoreInfoFromBackEnd("Password", store);
                    JSch jsch = new JSch(); // Connection
                    Session session = null;
                    s.type(sku);
                    s.type(Key.ENTER);
                    session = jsch.getSession(username, hostname, 22);
                    session.setConfig("StrictHostKeyChecking", "no");
                    session.setPassword(password);
                    session.connect();
                    ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
                    InputStream in = channelExec.getInputStream();
                    channelExec.setCommand("grep " + sku + " /usr/argos/data/STKFILE.DAT");
                    channelExec.connect();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line;
                    int exp = Integer.parseInt(qty);
                    while ((line = reader.readLine()) != null) {
                    	System.out.println( " Count:" + line.substring(152,155));
                    	int dd = Integer.parseInt(line.substring(152,155));
                    	System.out.println(dd);
                         String ss = Integer.toString(dd);
                    	session.disconnect();
                    	if ((ss.equals("0"))) {
                    		
                             s.type(Key.ESC);
                             s.type(Key.ESC);
                             Thread.sleep(500);
                             s.type(sku);
                             s.type(Key.ENTER);
                             s.type("5");
                             s.type(Key.ENTER);
                             s.type("1");
                             s.type(Key.ENTER);
                             Thread.sleep(500);
                             s.type(qty);
                             s.type(Key.ENTER);
                    
                      if (exp>50){
                    	     s.type("y");
                    	     s.type("+");
                    	     s.type(Key.ENTER);
                    	 
                    	     
                    	 }
                    	 else{
                    		 s.type("+");
                    		 s.type(Key.ENTER);
                    		
                    		 
                    	 }
                    	}
                      else if (!(ss.equals("0"))) {
                    	  
                    	  if(dd == exp){
                    		  System.out.println(dd);
                    	  }
                    	  else{
                    		  
                    		  if(dd>exp){
                    			  int z;
                    			  z = dd - exp;
                    			  System.out.println(z);
                    			  String n = Integer.toString(z);
                    			  Thread.sleep(100);
                                  s.type(Key.ESC);
                                  s.type(Key.ESC);
                                  Thread.sleep(500);
                                  s.type(sku);
                                  s.type(Key.ENTER);
                                  s.type("5");
                                  s.type(Key.ENTER);
                                  s.type("1");
                                  s.type(Key.ENTER);
                                  Thread.sleep(500);
                                
                                  if (exp>50){
                                	 s.type(n);
                                	 s.type(Key.ENTER);
                                	 s.type("y");
                              	     s.type("-");
                              	     s.type(Key.ENTER);
                              	     s.type(Key.F7);
                            	     s.type(sku);
                            	     s.type(Key.ENTER); 
                              	    
                                 
                             	     
                             	 }
                             	 else{
                             		 s.type(n);
                             		 s.type(Key.ENTER);
                             		 s.type("-");
                             		 s.type(Key.ENTER);
                             		 s.type(Key.F7);
                              	     s.type(sku);
                              	     s.type(Key.ENTER);
                    		  }
                    		 
                    		 
                    	  }
                    	  else{
                    	  int z;
                    	  z = exp - dd;
                    	  System.out.println(z);
            			  String n = Integer.toString(z);
            		
                          s.type(Key.ESC);
                          s.type(Key.ESC);
                          Thread.sleep(500);
                          s.type(sku);
                          Thread.sleep(100);
                          s.type(Key.ENTER);
                          s.type("5");
                          s.type(Key.ENTER);
                          s.type("1");
                          s.type(Key.ENTER);
                          Thread.sleep(500);
                        
                          if (exp>50){
                        	 s.type(n);
                        	 s.type(Key.ENTER);
                        	 s.type("y");
                      	     s.type("+");
                      	     s.type(Key.ENTER);
                      	     s.type(Key.F7);
                    	     s.type(sku);
                    	     s.type(Key.ENTER);
                         
                     	     
                     	 }
                     	 else{
                     		 s.type(n);
                     		 s.type(Key.ENTER);
                     		 s.type("+");
                     		 s.type(Key.ENTER);
                     		 s.type(Key.F7);
                     	     s.type(sku);
                     	     s.type(Key.ENTER);
            		  }
                    	  }
                    	  }  Thread.sleep(500);
                      }
                    }
                    Thread.sleep(500);
                    s.type(Key.ESC);
                    Thread.sleep(500);
                    s.type(Key.ESC);
                    Thread.sleep(500);
                    s.type(Key.ESC);
                    s.type(Key.ENTER);
                    s.type("5");
                    s.type("3");
                    s.type(Key.F7);
                    Thread.sleep(1000);
                   
}
}
                    	 


                          
                   
                                                 