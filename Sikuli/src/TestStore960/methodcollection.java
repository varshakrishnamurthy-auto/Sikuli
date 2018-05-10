package TestStore960;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

public class methodcollection {
                static String path = System.getProperty("user.dir");
                // static String projPath = path+"\\Img\\Aura\\";
                static String projPath = "C:\\EclipseWorkspace\\Sikuli\\Img\\Aura\\";

                public static void logintoAura(Screen s) throws FindFailed, InterruptedException, AWTException {
                                
                                
                                Robot robot = new Robot();
                                robot.keyPress(KeyEvent.VK_WINDOWS);
                                robot.keyPress(KeyEvent.VK_D);
                                robot.keyRelease(KeyEvent.VK_D);
                                robot.keyRelease(KeyEvent.VK_WINDOWS);
                                
                                //s.click(projPath + "ClearDesktop.PNG");
                                while (s.exists(projPath + "Login\\Aura.PNG") == null) {
                                                System.out.println("Waiting For 2 sec");
                                                Thread.sleep(2000);
                                }
                                s.doubleClick(projPath + "Login\\Aura.PNG");
                                Thread.sleep(5000);
                                s.click(projPath + "Login\\AuraOp.PNG");
                                Thread.sleep(1000);
                                s.click(projPath + "Login\\AuraAnita.PNG");
                                s.type("9044");
                                s.type(Key.ENTER);
                                Thread.sleep(2000);
                                s.type("argos");
                                s.type(Key.ENTER);
                                Thread.sleep(2000);
                                s.type("SUPPORT");
                                s.type(Key.ENTER);
                                s.type("sup1");
                                // JOptionPane.showMessageDialog(null, "Fail");
                                s.type(Key.ENTER);
                                // return min;
                                // JOptionPane.showMessageDialog(null, "Fail");
                }

                public static void OpenNewRetailJApplication(Screen s) throws FindFailed, InterruptedException, AWTException {
                                /*BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                                try {
                                                System.out.println("taking second screen shot");
                                                ImageIO.write(image, "png", new File("C:\\Users\\Public\\screenshot.png"));
                                } catch (IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                }*/
                                Robot robot = new Robot();
                                robot.keyPress(KeyEvent.VK_WINDOWS);
                                robot.keyPress(KeyEvent.VK_D);
                                robot.keyRelease(KeyEvent.VK_D);
                                robot.keyRelease(KeyEvent.VK_WINDOWS);

                                while (s.exists(projPath + "Login\\Aura.PNG") == null) {
                                                System.out.println("Waiting For 2 sec");
                                                Thread.sleep(2000);
                                }
                                s.doubleClick(projPath + "Login\\Aura.PNG");
                                Thread.sleep(5000);
                                s.doubleClick(projPath + "Login\\AuraOp.PNG");
                                Thread.sleep(1000);
                                s.doubleClick(projPath + "Login\\AuraAnita.PNG");
                                
                }

                public static void loginToStore(Screen s, String store) throws FindFailed, InterruptedException {
                                Thread.sleep(2000);
                                s.type(store);
                                
                                s.type(Key.ENTER);
                                Thread.sleep(4000);
                                if (store.equals("9099")) {
                                                System.out.println("Storezzz" + store);
                                                s.type("argos");
                                                s.type(Key.ENTER);
                                                Thread.sleep(4000);
                                                s.type("SUPPORT");
                                                s.type(Key.ENTER);
                                                s.type("d1sc0ver");
                                                //s.type(Key.ENTER);
                                                
                                }
                                
                if(store.equals("9098") || store.equals("9087") || store.equals("960")) {
                                                System.out.println("Storezzz" + store);
                                                s.type("argos");
                                                s.type(Key.ENTER);
                                                Thread.sleep(4000);
                                                s.type("SUPPORT");
                                                s.type(Key.ENTER);
                                                s.type("d1sc0ver");
                                                /*s.type(Key.ENTER);*/
                                } else if (store.equals("9091") || store.equals("9092") || store.equals("9093") || store.equals("9096")) {
                                                Thread.sleep(10000);
                                                s.type("SUPPORT");
                                                s.type(Key.ENTER);
                                                s.type("argos2");
                                                s.type(Key.ENTER);
                                } else if (store.equals("9094") || store.equals("9095")) {
                                                Thread.sleep(10000);
                                                s.type("SUPPORT");
                                                s.type(Key.ENTER);
                                                s.type("argos3");
                                                s.type(Key.ENTER);
                                }
                }

                // ManagerMenu to Change Stock Quantities menu
                public static void managersMenuTOChangeStockQuantities(Screen s) throws FindFailed, InterruptedException {
                                s.type("4");
                                Thread.sleep(100);
                                s.type("5");
                                Thread.sleep(100);
                                s.type("3");
                                Thread.sleep(100);
                }

                public static void ChangeStockQuantitiesTOStockEnquiry(Screen s) throws FindFailed, InterruptedException {
                                s.type(Key.F7);
                                Thread.sleep(100);
                }

                public static void StockEnquiryTOChangeStockQuantities(Screen s) throws FindFailed, InterruptedException {
                                s.type(Key.ESC);
                                s.type(Key.ESC);
                }

                

                public static void loadInventory(Screen s, String sku, String inv, String loc)
                                                throws FindFailed, InterruptedException {
                                s.type(sku);
                                s.type(Key.ENTER);
                                s.type("1");
                                s.type("1");
                                s.type(Key.ENTER);
                                s.type(inv);
                                s.type(Key.ENTER);
                                s.type("+");
                                s.type(Key.ENTER);
                                s.type(loc);
                                s.type(Key.ENTER);
                                
                }

                public static void LoadItems(Screen s, String sku)
                                                throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
                                s.type("4");
                                s.type("3");
                                s.type("1");
                                s.type("1");
                                // At Stock File Maintainance
                                s.type(sku);
                                s.type(Key.ENTER);
                                Thread.sleep(100);
                                
                                if (s.exists(projPath + "Inquiry\\ItemNotExist.PNG") != null) {
                                	System.out.println("Sku is invalid or already load");
                                } 
                                else {
                                      s.type(Key.ESC);
                                      System.out.println("Sku is valid");
                                }                
                                s.type(Key.ESC);
                                s.type(Key.ESC);
                                s.type(Key.ESC);
                                s.type(Key.ESC);
                }

                public static void setInventoryToZero(Screen s, String sku)
                                                throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {

                }

                public static int getMainStockAtStockEnquiry(Screen s, String sku) throws FindFailed, InterruptedException {
                                s.type(sku);
                                s.type(Key.ENTER);
                                Region x1 = s.find(projPath + "Inquiry\\MainStock.PNG").right(200);
                                String x = x1.text();
                                
                                char[] array = x.toCharArray();
                                int[] position = new int[10];
                                int cnt = 0;
                                for (int i = 0; i < array.length; i++) {
                                                if (array[i] == ' ') {
                                                                // System.out.println(i);
                                                                position[cnt] = i;
                                                                cnt = cnt + 1;
                                                }
                                }
                                // String x = x1.text();
                                String z2 = x.substring(0, position[0]);
                                int result = Integer.parseInt(z2);
                                return (result);
                }

                
}
