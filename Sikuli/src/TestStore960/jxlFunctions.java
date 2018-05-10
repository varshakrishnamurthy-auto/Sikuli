package TestStore960;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class jxlFunctions {
//            static String path = System.getProperty("user.dir");
                static String projPath ="C:/EclipseWorkspace/Sikuli/Data/";
                static int rowInv = 0;
                static int colInv = 0;
                // SKU sheet

                static int rowSku = 0;
                static int colSku = 0;
                // Store sheet

                static int rowstore = 0;
                static int colstore = 0;

                static int rowbend = 0;
                static int colbend = 0;

                static Sheet sh;
                static Sheet shsku;
                static Sheet shstore;
                static Sheet shbend;

                public jxlFunctions() throws BiffException, IOException {
                                Workbook inventoryInputSheet = Workbook
                                                                .getWorkbook(new File(projPath+"TDM InputSheet_s960.xls"));
                                // Inventory Sheet
                                this.sh = inventoryInputSheet.getSheet("TDM_Inventory_Sheet");
                                this.rowInv = sh.getRows();
                                this.colInv = sh.getColumns();
                                // SKU sheet
                                this.shsku = inventoryInputSheet.getSheet("Total Products");
                                this.rowSku = shsku.getRows();
                                this.colSku = shsku.getColumns();
                                // Store sheet
                                this.shstore = inventoryInputSheet.getSheet("Store");
                                this.rowstore = shstore.getRows();
                                this.colstore = shstore.getColumns();
                                System.out.println(rowstore);
                                this.shbend = inventoryInputSheet.getSheet("Backend");
                                this.rowbend = shbend.getRows();
                                this.colbend = shbend.getColumns();
                }

                public static ArrayList<String> getcolumnValuesFromIventorySheet(String columnName)
                                                throws BiffException, IOException {
                                int column = 0;
                                if (columnName == "ENVIRONMENT") {
                                                column = 0;
                                } else if (columnName == "Journey") {
                                                column = 1;
                                } else if (columnName == "HUB STORE") {
                                                column = 4;
                                } else if (columnName == "HUB Quantity") {
                                                column = 5;
                                } else if (columnName == "SPOKE STORE") {
                                                column = 6;
                                } else if (columnName == "SPOKE Quantity") {
                                                column = 7;
                                } else if (columnName == "DC") {
                                                column = 2;
                                } else if (columnName == "DC Quantity") {
                                                column = 3;
                                } else if (columnName == "SKU") {
                                                column = 8;
                                } else if (columnName == "LOAD PRODUCTS JOURNEY WISE") {
                                                column = 10;
                                }
                                else if (columnName == "LOCATION") {
                                                column = 11;
                                }
                                ArrayList<String> skulist = new ArrayList<String>();
                                int i;
                                String temp = "";
                                for (i = 1; i < rowInv; i++) {
                                                temp = sh.getCell(column, i).getContents();
                                                if (temp != "" && temp != null) {
                                                                skulist.add(temp);
                                                }
                                }
                                return (skulist);
                }

                public static ArrayList<String> getcolumnValuesFromProductSheet(String columnName)
                                                throws BiffException, IOException {
                                int column = 0;
                                if (columnName == "1ManH2H") {
                                                column = 1;
                                } else if (columnName == "Instock") {
                                                column = 2;
                                } else if (columnName == "InstockLow") {
                                                column = 3;
                                } else if (columnName == "OOS") {
                                                column = 4;
                                } else if (columnName == "H2S") {
                                                column = 5;
                                } else if (columnName == "CSO") {
                                                column = 6;
                                } else if (columnName == "RFC") {
                                                column = 14;
                                } else if (columnName == "Conveniance") {
                                                column = 15;
                                } else if (columnName == "PreOrder") {
                                                column = 18;
                                }
                                System.out.println(column);
                                System.out.println(rowSku);
                                ArrayList<String> skulist = new ArrayList<String>();
                                int i = 0;
                                String temp = "";
                                for (i = 1; i < rowSku; i++) {
                                                temp = shsku.getCell(column, i).getContents();

                                                if (temp != "" && temp != null) {
                                                                skulist.add(temp);
                                                }
                                }

                                return (skulist);
                }

                public static ArrayList<String> getStoresFromStoresTab(String env, String findstores)
                                                throws BiffException, IOException {
                                int i = 0;
                                String envmatch = "";
                                String temp = "";
                                int column = 0;
                                if (findstores == "Hub") {
                                                column = 1;
                                } else if (findstores == "Spoke") {
                                                column = 2;
                                } else if (findstores == "RFC") {
                                                column = 4;
                                } else if (findstores == "Conveniance") {
                                                column = 7;
                                }
                                ArrayList<String> list = new ArrayList<String>();
                                for (i = 1; i < rowstore; i++) {
                                                envmatch = shstore.getCell(0, i).getContents();
                                                System.out.println(envmatch);
                                                if (envmatch.equals(env)) {

                                                                temp = shstore.getCell(column, i).getContents();
                                                                if (temp != "" && temp != null) {
                                                                                list.add(temp);
                                                                }
                                                }

                                }

                                return (list);
                }

                public static String getStoreInfoFromBackEnd(String backendcolumn, String Store) throws BiffException, IOException {
                                int i = 0;
                                String envmatch = "";
                                String temp = "";
                                int column = 0;
                                if (backendcolumn == "Stores") {
                                                column = 26;
                                } else if (backendcolumn == "IP") {
                                                column = 27;
                                } else if (backendcolumn == "Username") {
                                                column = 28;
                                } else if (backendcolumn == "Password") {
                                                column = 29;
                                } else if (backendcolumn == "AURA Username") {
                                                column = 30;
                                } else if (backendcolumn == "AURA Password") {
                                                column = 31;
                                }
                                String Result = "";
                                // ArrayList<String> list = new ArrayList<String>();
                                for (i = 1; i < 33; i++) {
                                                temp = shbend.getCell(26, i).getContents();

                                                if (temp.equals(Store)) {
                                                                Result = shbend.getCell(column, i).getContents();
                                                                System.out.println(Result);
                                                }
                                }

                                return (Result);
                }

}

