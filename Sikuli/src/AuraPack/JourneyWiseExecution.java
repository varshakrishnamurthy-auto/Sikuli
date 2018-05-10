package AuraPack;

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
public void H2HJourney(Screen s, String sku, String qty, String store, reportMismatch rm , String l)
//            public void H2HJourney(Screen s, String sku, String qty, String store, reportMismatch rm)
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
                                s.type(sku);
                                s.type(Key.ENTER);
                                Region x1 = null;
                                Thread.sleep(3000);
                                if (s.exists(projPath + "Inquiry/MainPick.PNG") != null) {
                                                x1 = s.find(projPath + "Inquiry/MainPick.PNG").right(200);
                                               
                                }
                                int result = 0;
                                String x = x1.text();
                                System.out.println("Got the Quantity" + x);
                                if (x.equals("")) {
                                                x = "0000";
                                }

                                // .substring(0,5);
                x = x.replace(" ", "");
                                int z = 0;
                                char[] array = x.toCharArray();
                                int[] position = new int[10];
                                int cnt = 0;
                                for (int i = 0; i < array.length; i++) {
                                                if (array[i] == ' ') {

                                                                position[cnt] = i;
                                                                cnt = cnt + 1;
                                                }
                                }
                                String z2;
                                if (cnt == 0) {
                                                z2 = x;
                                } else {
                                                z2 = x.substring(0, position[0]);
                                }

                                System.out.println("Got the Quantity" + z2);
                                if (x.equals("0")) {
                                                result = 9000;
                                } else {
                                                result = Integer.parseInt(z2);
                                }
                                int exp = Integer.parseInt(qty);

                                ////////////////// Checking////////////////
                                if (exp == 0) {
                                                if (result == exp) {
                                                                s.type(Key.ESC);
                                                } else {
                                                                // Vipin's Code goes here

                                                                System.out.println("Into OOS Code" + result);

                                                                session = jsch.getSession(username, hostname, 22);
                                                                session.setConfig("StrictHostKeyChecking", "no");
                                                                session.setPassword(password);
                                                                session.connect();
                                                                ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
                                                                InputStream in = channelExec.getInputStream();
                                                                channelExec.setCommand("grep " + sku + " /usr/argos/data/STVARLOC.DAT");
                                                                channelExec.connect();
                                                                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                                                                String line;
                                                                while ((line = reader.readLine()) != null) {
                                                                                System.out.println("Locations  : " + line.substring(8, 12) + " Count"
                                                                                                                + Integer.parseInt(line.substring(12, 18)));
                                                                                int dd = Integer.parseInt(line.substring(12, 18));
                                                                                String ss = Integer.toString(dd);
                                                                                if (!(ss.equals("0"))) {

                                                                                                s.type(Key.ESC);
                                                                                                s.type(Key.ESC);
                                                                                                s.type(sku);
                                                                                                s.type(Key.ENTER);
                                                                                                s.type("1");
                                                                                                s.type(Key.ENTER);
                                                                                                s.type("1");
                                                                                                s.type(Key.ENTER);
                                                                                                Thread.sleep(500);
                                                                                                System.out.println("Deepak" + ss);
                                                                                                s.type(ss);

                                                                                                Thread.sleep(500);
                                                                                                s.type(Key.ENTER);
                                                                                                if (Integer.parseInt(line.substring(12, 18)) > 50) {
                                                                                                                s.type("y");
                                                                                                }
                                                                                                s.type("-");
                                                                                                s.type(Key.ENTER);
                                                                                                //s.type("y");
                                                                                                if (s.exists(projPath + "Inquiry/negativeBalance.PNG") != null) {
                                                                                                                s.type(Key.ESC);
                                                                                                                s.type(Key.F7);
                                                                                                                s.type(sku);
                                                                                                                s.type(Key.ENTER);
                                                                                                                session.disconnect();
                                                                                                                break;
                                                                                                } else {
                                                                                                                Thread.sleep(500);
                                                                                                                s.type(line.substring(8, 12));
                                                                                                                Thread.sleep(500);
                                                                                                                s.type(Key.ENTER);
                                                                                                                s.type(Key.F7);
                                                                                                                s.type(sku);
                                                                                                                s.type(Key.ENTER);
                                                                                                                session.disconnect();
                                                                                                }
                                                                                }
                                                                }
                                                                Region x2 = null;
                                                                if (s.exists(projPath + "Inquiry/MainPick.PNG") != null) {
                                                                                x2 = s.find(projPath + "Inquiry/MainPick.PNG").right(200);
                                                                }
                                                                String saleable1 = x2.text().replace(" ", "");
                                                                System.out.println("text is " + saleable1);
                                                                if (saleable1.equals("")) {
                                                                                System.out.println("Saleable is set to zero");
                                                                } else {
                                                                                System.out.println("Saleable is set to zero " + saleable1);
                                                                                System.out.println("MandMs" + sku + store + saleable1 + "Here");
                                                                                rm.writeOneRow(sku, store, saleable1,
                                                                                                                "Not able to setup the inventory in Aura due to Missmatch. Please reach out to Aura Team");
                                                                }
                                                                s.type(Key.ESC);

                                                }

                                } else {
                                                if (result == exp) {
                                                                s.type(Key.ESC);
                                                } else if (result > exp) {

                                                                z = result - exp;
                                                                System.out.println("value is " + z);
                                                                String y = Integer.toString(z);
                                                                System.out.println("more");
                                                                s.type(Key.ESC);
                                                                s.type(Key.ESC);
                                                                s.type(sku);
                                                                s.type(Key.ENTER);
                                                                s.type("1");
                                                                s.type("1");
                                                                s.type(Key.ENTER);
                                                                s.type(y);
                                                                s.type(Key.ENTER);
                                                                if (z > 50) {
                                                                                s.type("y");
                                                                }
                                                                s.type("-");
                                                                s.type(Key.ENTER);
                                                                s.type(l);
                                                                //s.type("01AA");
                                                                s.type(Key.ENTER);

                                                                s.type(Key.F7);
                                                                s.type(sku);
                                                                s.type(Key.ENTER);

                                                                s.type(Key.ESC);
                                                } else {

                                                                z = exp - result;
                                                                System.out.println("value is " + z);
                                                                String y = Integer.toString(z);
                                                                System.out.println("less");
                                                                s.type(Key.ESC);
                                                                s.type(Key.ESC);
                                                                s.type(sku);
                                                                s.type(Key.ENTER);
                                                                s.type("1");
                                                                s.type("1");
                                                                s.type(Key.ENTER);
                                                                s.type(y);
                                                                s.type(Key.ENTER);
                                                                if (z > 50) {
                                                                                Thread.sleep(1000);
                                                                                s.type("y");
                                                                }
                                                                s.type("+");
                                                                s.type(Key.ENTER);
                                                                Thread.sleep(2000);
                                                                s.type(l);
                                                                s.type(Key.ENTER);
                                                                s.type(Key.F7);
                                                                s.type(sku);
                                                                s.type(Key.ENTER);
                                                                s.type(Key.ESC);
                                                }
                                }
                                Thread.sleep(500);
                                /*s.type(Key.ESC);
                                Thread.sleep(500);
                                s.type(Key.ESC);
                                Thread.sleep(500);
                                s.type(Key.ESC);
                                Thread.sleep(500);
                                s.type(Key.ESC);*/
                }

}
