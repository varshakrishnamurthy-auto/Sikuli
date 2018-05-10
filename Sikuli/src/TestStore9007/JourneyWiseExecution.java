package TestStore9007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class JourneyWiseExecution {
                static String path = System.getProperty("user.dir");
                static String projPath = "C:\\EclipseWorkspace\\Sikuli\\Img\\Aura\\";
public void H2HJourney(Screen s, String sku, String qty, String store, reportMismatch rm , String loc)
			throws FindFailed, InterruptedException, JSchException, IOException, BiffException, WriteException {
                                jxlFunctions jf = new jxlFunctions();
                                // String hostname = "172.28.44.199";
                                // String username = "argosdev";
                                // String password = "m1ck3y";
                                String hostname = jf.getStoreInfoFromBackEnd("IP", store);
                                String username = jf.getStoreInfoFromBackEnd("Username", store);
                                String password = jf.getStoreInfoFromBackEnd("Password", store);
                                JSch jsch = new JSch();
                                Session session = null;
                             
                                if (loc.startsWith(".*")){ 
                                s.type(sku);
                                s.type(Key.ENTER);
                                s.type("1");
                                s.type(Key.ENTER);
                                s.type("1");
                                s.type(Key.ENTER);
                                s.type(qty);
                                s.type(Key.ENTER);
                                s.type("+");
                                s.type(Key.ENTER);
                                Thread.sleep(500);
                                s.type(loc);
                                Thread.sleep(500);
                                s.type(Key.ENTER);
                                s.type(Key.ESC);
                                s.type(Key.ESC);
                                s.type(Key.ESC);
                                }
                                else if (loc.startsWith("SOB")){
                                	s.type(sku);
                                    s.type(Key.ENTER);
                                    s.type("5");
                                    s.type(Key.ENTER);
                                    s.type("1");
                                    s.type(Key.ENTER);
                                    s.type(qty);
                                    s.type(Key.ENTER);
                                    s.type("+");
                                    Thread.sleep(500);
                                    s.type(Key.ENTER);
                                    Thread.sleep(500);
                                    s.type(Key.ESC);
                                    s.type(Key.ESC);
                                    s.type(Key.ESC);
                                }
                                else if (loc.startsWith("DIS")){
                                	s.type(sku);
                                    s.type(Key.ENTER);
                                    s.type("3");
                                    s.type(Key.ENTER);
                                    s.type("1");
                                    s.type(Key.ENTER);
                                    s.type(qty);
                                    s.type(Key.ENTER);
                                    s.type("+");
                                    Thread.sleep(500);
                                    s.type(Key.ENTER);
                                    Thread.sleep(500);
                                    s.type(Key.ESC);
                                    s.type(Key.ESC);
                                    s.type(Key.ESC);
                                }
                                else if(loc.startsWith("Multi")){
                                	 s.type(sku);
                                     s.type(Key.ENTER);
                                     s.type("1");
                                     s.type(Key.ENTER);
                                     s.type("1");
                                     s.type(Key.ENTER);
                                     s.type(qty);
                                     s.type(Key.ENTER);
                                     s.type("+");
                                     Thread.sleep(500);
                                     s.type(Key.ENTER);
                                     s.type("01AA");
                                     Thread.sleep(500);
                                     s.type(Key.ENTER);
                                     Thread.sleep(500);
                                     s.type(sku);
                                     s.type(Key.ENTER);
                                     s.type("1");
                                     s.type(Key.ENTER);
                                     s.type("1");
                                     s.type(Key.ENTER);
                                     s.type(qty);
                                     s.type(Key.ENTER);
                                     s.type("+");
                                     Thread.sleep(100);
                                     s.type(Key.ENTER);
                                     Thread.sleep(500);
                                     s.type("01AB");
                                     Thread.sleep(500);
                                     s.type(Key.ENTER);
                                     Thread.sleep(500);
                                     s.type(Key.ESC);
                                     s.type(Key.ESC);
                                     s.type(Key.ESC);
                                }
}
}

	
