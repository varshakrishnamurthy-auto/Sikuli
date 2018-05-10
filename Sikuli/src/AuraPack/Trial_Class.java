package AuraPack;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.jcraft.jsch.JSchException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class Trial_Class {
	//static String path = System.getProperty("user.dir");
	static String projPath = "C:/EclipseWorkspace/Sikuli/Img/";

	public static void main(String[] args) throws BiffException, IOException, FindFailed, InterruptedException,
			ClassNotFoundException, SQLException, JSchException, WriteException, AWTException {
		////////////////////////////////////////////////////////////////////
		//////////////////////////////////// Initialization////////////////////
	
		jxlFunctions jf = new jxlFunctions();
		reportMismatch rm = new reportMismatch();
		Screen s = new Screen();
		s.click(projPath + "Aura/Aura.PNG");
		Thread.sleep(1000);
		//s.click(projPath + "Login\\AuraAnita.PNG");
		Region x1 = null;
        Thread.sleep(3000);
        if (s.exists(projPath + "Aura/Inquiry/MainPick.PNG") != null) {
            x1 = s.find(projPath + "Aura/Inquiry/MainPick.PNG").right(200);
            
        	//System.out.println("Got the Quantity" + x1);
        }
        int result = 0;
        String x = x1.text();
        System.out.println("Got the Quantity" + x);
		
	}
}
