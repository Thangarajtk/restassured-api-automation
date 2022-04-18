package com.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private XSSFWorkbook wb;
	private XSSFSheet sh;
	private XSSFRow row;
	private XSSFCell cell;
	
	FileInputStream in;
	FileOutputStream out;
	int cellNum;
	int rowNum;

	public ExcelUtils() {
		String filePath = System.getProperty("user.dir")+"/src/test/resources/TestData.xlsx";
		try {
			wb = new XSSFWorkbook(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public XSSFSheet getSheetByIndex(int index) {
		sh = wb.getSheetAt(index);
		return sh;
	}

	public int getRowCount() {
		int rowCount = getSheetByIndex(0).getLastRowNum();
		return rowCount;
	}

	public int getColumnCount() {
		row = getSheetByIndex(0).getRow(0);
		int cellCount = row.getLastCellNum();
		return cellCount;
	}

	public HashMap<String, ArrayList<Object>> getExcelData() {
		HashMap<String, ArrayList<Object>> map = new HashMap<String, ArrayList<Object>>();
		ArrayList<Object> list;

		for(int i = 0;i < getColumnCount(); i++) {
			list = new ArrayList<Object>();
			for(int j = 1;j<getRowCount(); j++) {
				cell = getSheetByIndex(0).getRow(j).getCell(i);
				
				switch(cell.getCellType()) {
				case NUMERIC:
					list.add(cell.getNumericCellValue());
					break;
				case FORMULA:
					list.add(cell.getCellFormula());
					break;
				default:
					list.add(cell.getStringCellValue());
					break;
				}
			}
			map.put(getSheetByIndex(0).getRow(0).getCell(i).getStringCellValue(), list);
		}
		return map;
	}
	
	public void writeData(String tcID, String key, String value) 
	{
		try
		{
			File f = new File(System.getProperty("user.dir")+"\\TestData.xlsx");
			in = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(in);

			in.close();

			sh = workbook.getSheet("Test");
			int cellCount = sh.getRow(0).getLastCellNum();

			for(int i=1;i<=cellCount;i++)
			{
				if((sh.getRow(0).getCell(i).getStringCellValue()).equals(key))
				{
					cellNum = i;
					break;
				}
			}
			int rowCount = sh.getLastRowNum()+1;
			for(int j=1;j<=rowCount;j++)
			{
				if((sh.getRow(j).getCell(0).getStringCellValue()).equals(tcID))
				{
					rowNum = j;
					break;
				}
			}
			row = sh.getRow(rowNum);
			cell = row.createCell(cellNum);
			cell.setCellValue(value);

			out = new FileOutputStream(f);
			workbook.write(out);
			out.close();
			System.out.println("Written successfully on file....");
			workbook.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(in!=null || out!=null) {
					in.close();
					out.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

}
