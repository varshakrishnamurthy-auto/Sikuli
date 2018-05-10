package AuraPack;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.*;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class reportMismatch {
	static String path = System.getProperty("user.dir");
	static String projPath = "C:/EclipseWorkspace/Sikuli/Data/";

	public static WritableCellFormat createFormatCellStatus(String State) throws WriteException{
	    WritableFont wfontStatus = new WritableFont(WritableFont.createFont("TIMES"), WritableFont.DEFAULT_POINT_SIZE, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);       
	    //WritableFont wfontStatus = new WritableFont(WritableFont.createFont("Arial"), WritableFont.DEFAULT_POINT_SIZE, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, colour);
	    WritableCellFormat fCellstatus = new WritableCellFormat(wfontStatus);
	    fCellstatus.setWrap(true);
	    fCellstatus.setAlignment(jxl.format.Alignment.CENTRE);
	    fCellstatus.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	    fCellstatus.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
	    if(State == "Heading"){
	    			fCellstatus.setBackground(Colour.GRAY_25);
	    }else if(State == "GREEN"){
	    			fCellstatus.setBackground(Colour.GREEN);
	    }else if(State == "RED"){
	    			fCellstatus.setBackground(Colour.RED);
	    }else if(State == "YELLOW"){
	    			fCellstatus.setBackground(Colour.YELLOW);
	    }
	    return fCellstatus;
	}
	
	static int rowInv = 0 ;
	static int colInv = 0 ;
	static int rowcount = 0;
	static WritableWorkbook wwbCopy;
	static WritableSheet shSheet;
	static Sheet sh;
	
	
	public reportMismatch() throws BiffException, IOException, WriteException{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String filename = projPath+"TrialReport_"+timeStamp+".xls";
		File Report = new File(filename);
    	reportMismatch.wwbCopy = Workbook.createWorkbook(Report);
    	reportMismatch.shSheet = wwbCopy.createSheet("SuiteReport", 0);

/////////////////////////////Inserting Heading into the excel sheet /////////////////////////////////
     	int h1 = 0;
    	String[] Heading = {"Sl No","Mismatch SKU","Store","Quantity","Comments"};
		  for(h1=0;h1<=4;h1++){
    		jxl.write.Label labTemp = new jxl.write.Label(h1, 0, Heading[h1],createFormatCellStatus("Heading"));
    		shSheet.addCell(labTemp);
        }
		  
		  reportMismatch.rowcount = reportMismatch.rowcount+1;
	}
	
	public static void writeOneRow(String MMSku,String MMStore,String MMQuantity , String Comments) throws  BiffException, IOException, WriteException {
		System.out.println("MISSMATCH"+MMSku+MMStore+MMQuantity+Comments);
		jxl.write.Label l1 = new jxl.write.Label(0, rowcount, Integer.toString(rowcount) ,createFormatCellStatus(""));
		shSheet.addCell(l1);
		jxl.write.Label l2 = new jxl.write.Label(1, rowcount, MMSku,createFormatCellStatus(""));
		shSheet.addCell(l2);
		jxl.write.Label l3 = new jxl.write.Label(2, rowcount, MMStore,createFormatCellStatus(""));
		shSheet.addCell(l3);
		jxl.write.Label l4 = new jxl.write.Label(3, rowcount, MMQuantity,createFormatCellStatus(""));
		shSheet.addCell(l4);
		jxl.write.Label l5 = new jxl.write.Label(4, rowcount, Comments,createFormatCellStatus(""));
		shSheet.addCell(l5);

		 rowcount = rowcount+1;
	}
	public static void closeConnection() throws  BiffException, IOException, WriteException {
		CellView cell;
		for(int x=0;x<60;x++)
		{
		    cell=shSheet.getColumnView(x);
		    cell.setAutosize(true);
		    shSheet.setColumnView(x, cell);
		}
		wwbCopy.write();
		  wwbCopy.close();
	}
	

}
