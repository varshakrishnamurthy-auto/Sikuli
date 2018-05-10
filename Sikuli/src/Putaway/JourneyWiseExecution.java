package Putaway;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class JourneyWiseExecution {
               	private static final String F10 = null;
				static String path = System.getProperty("user.dir");
                static String projPath = "C:\\EclipseWorkspace\\Sikuli\\Img\\Aura\\";
public void H2HJourney(Screen s, String sku, String qty, String store, reportMismatch rm ,String DATE)
			throws FindFailed, InterruptedException, JSchException, IOException, BiffException, WriteException, AWTException {
                               /* jxlFunctions jf = new jxlFunctions();*/
                                 String hostname = "172.28.98.199";
                                 String username = "argosdev";
                                 String password = "m1ck3y";
                               /* String hostname = jf.getStoreInfoFromBackEnd("IP", store);
                                String username = jf.getStoreInfoFromBackEnd("Username", store);
                                String password = jf.getStoreInfoFromBackEnd("Password", store);*/
                                s.type("1");
                                System.out.print(sku);
                                System.out.print(DATE);
                                JSch jsch = new JSch();
                                Session session = null;
                                session = jsch.getSession(username, hostname, 22);
                                session.setConfig("StrictHostKeyChecking", "no");
                                session.setPassword(password);
                                session.connect();
                                ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
                                InputStream in = channelExec.getInputStream();
                                channelExec.setCommand("cat /var/spool/tp/work/tpmon.aud."+ DATE + "| grep VP18.*"+ sku );
                                /*channelExec.setCommand("grep " + sku + " /usr/argos/data/STVARLOC.DAT");*/
                                channelExec.connect();
                                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                                String line;
                                System.out.println("b");
                                while ((line = reader.readLine()) != null) {
                                    System.out.println("TransferNo  : " + (line.substring(86,92)) ); 
                                    int dd = Integer.parseInt(line.substring(86, 92));
                                  	String ss = Integer.toString(dd);
                                  	System.out.println("ss value is :" +ss);
                                  	for ( int i = 0 ; i < 20 ; i ++)
                                {
                                	 List<String> TransferNumbers =  getList( s);
                                	System.out.print(i);
                                    if(TransferNumbers.contains(ss))  {
                                	System.out.println("Index of 'ss': "+TransferNumbers.indexOf(ss));
                                	int index = TransferNumbers.indexOf(ss);
                                	methodcollection.SelectTransfer( s ,  index);
                                	methodcollection.SendTransfer(s);
                                	for ( int j = 0 ; j < 5 ; j++)
                                	{
                                		 TransferNumbers = getList(s);
                                		 System.out.println(j);
                                		 System.out.println("SS value is" +ss);
                                		if ( TransferNumbers.contains(ss))
                                		{
                                			System.out.println("Index of 'ss': "+TransferNumbers.indexOf(ss));
                                        	int index1 = TransferNumbers.indexOf(ss);
                                        	methodcollection.SelectTransfer( s ,  index1);
                                        	
                                		
                                		}
                                		else 
                                		{
                                			System.out.println("c");
                                			s.type(Key.PAGE_DOWN);
                                			
                                		}
                                	}
                                	
                                	  
                                	
                                }
                                else{
                                	
                                	System.out.println("b");
                                	s.type(Key.PAGE_DOWN);
                                }
                                    session.disconnect();
                                }
}}

public List<String> getList( Screen s) throws FindFailed

{
	
	 Region x1 = null;
	
     if (s.exists(projPath + "Login\\Transfer2.PNG") != null) {
     	
              x1 = s.find(projPath + "Login\\Transfer2.PNG").below(300);
             
                                    }
     String x = x1.text() ;
     if (x.equals("")) {
         x = "0000";
     	}

     x = x.replace(" ", "");
     System.out.println("Got the Transfer Number " + x); 
   List<String> TransferNumbers = new ArrayList<String>(Arrays.asList(x.split("\n"))); 
     System.out.println(TransferNumbers);
	
		return TransferNumbers;
}

 
}
                                    		
