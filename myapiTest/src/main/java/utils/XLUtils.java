package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static int getRowCount(String Xlfile,String Xlsheet) throws IOException {
		fi =new FileInputStream(Xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(Xlsheet);
		int rowCount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}
	
	public static int getCellcount(String Xlfile,String Xlsheet,int rowNum) throws IOException{
		fi =new FileInputStream(Xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(Xlsheet);
		row=ws.getRow(rowNum);
		int cellcount=row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}
	public static String getCellData(String Xlfile,String Xlsheet,int rowNum, int column) throws IOException{
		fi =new FileInputStream(Xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(Xlsheet);
		row=ws.getRow(rowNum);
		cell=row.getCell(column);
		
		String data;
		try {
			DataFormatter formatter=new DataFormatter();
			String celldata=formatter.formatCellValue(cell);
			return celldata;	
			
		}catch(Exception e) {
			data="";
		}wb.close();
		fi.close();
		return data;

}
	
	public static void setCellData(String Xlfile,String Xlsheet,int rowNum, int column,String data) throws IOException{
		fi =new FileInputStream(Xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(Xlsheet);
		row=ws.getRow(rowNum);
		cell=row.getCell(column);
		cell.setCellValue(data);
		fo=new FileOutputStream(Xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
