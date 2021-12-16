package dp;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	
	//Mehtod for reading EXCEL file
	public static String[][] getDataFromExcelToObject(String fileName, String sheetName) throws IOException{
		String[][] tableArray=null;
		
		//Get excel file within the resources folder
		fileName = System.getProperty("user.dir") +"\\resources\\"+fileName;
		
		try {
		//Get the Excel
		FileInputStream excelFile = new FileInputStream(fileName);
		XSSFWorkbook ExcelWBook = new XSSFWorkbook(excelFile);
		XSSFSheet ExcelWSheet =  ExcelWBook.getSheet(sheetName);
		
		//Range of data
		int totalRow = 1;
		int totalCol = 3;
		
		
			tableArray = new String [totalRow][totalCol];
			for(int i = 0; i < totalRow; i++ ) {
				for(int j = 0; j < totalCol; j++ ) {
					
					 //create method of reading from Excel
					tableArray[i][j] = ExcelWSheet.getRow(i+1).getCell(j+1).getStringCellValue();
					
					System.out.println("insert: " + tableArray[i][j] );
				}
			}
			
			//close the xls service
			ExcelWBook.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
		
		return tableArray;
	}
	

}
