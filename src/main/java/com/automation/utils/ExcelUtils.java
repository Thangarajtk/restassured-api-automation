package com.automation.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public final class ExcelUtils {

  FileInputStream in;
  FileOutputStream out;
  int cellNum, rowNum;
  private XSSFWorkbook wb;
  private XSSFSheet sh;
  private XSSFRow row;
  private XSSFCell cell;

  public static List<Map<String, Object>> getExcelData(String filePath) throws IOException {
    List<Map<String, Object>> dataFromExcel = new ArrayList<>();
      Sheet sheet;
      try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {
          sheet = workbook.getSheetAt(0);
      }
      int totalRows = sheet.getPhysicalNumberOfRows();
    Map<String, Object> mapData;
    List<String> allKeys = new ArrayList<>();

    for (int i = 0; i < totalRows; i++) {
      mapData = new LinkedHashMap<>();
      if (i == 0) {
        int totalCols = sheet.getRow(0).getPhysicalNumberOfCells();
        for (int j = 0; j < totalCols; j++) {
          allKeys.add(sheet.getRow(0).getCell(j).getStringCellValue());
        }
      } else {
        int totalCols = sheet.getRow(i).getPhysicalNumberOfCells();
        for (int j = 0; j < totalCols; j++) {
          Cell cell = sheet.getRow(i).getCell(j);
          Object cellValue;
          switch (cell.getCellType()) {
            case NUMERIC:
              cellValue = cell.getNumericCellValue();
              break;
            case FORMULA:
              cellValue = cell.getCellFormula();
              break;
            default:
              cellValue = cell.getStringCellValue();
              break;
          }
          mapData.put(allKeys.get(j), cellValue);
        }
        dataFromExcel.add(mapData);
      }
    }

    return dataFromExcel;
  }

  public void writeData(String tcID, String key, String value) {
    try {
      File f = new File(System.getProperty("user.dir") + "/TestData.xlsx");
      in = new FileInputStream(f);
      XSSFWorkbook workbook = new XSSFWorkbook(in);

      in.close();

      sh = workbook.getSheet("Test");
      int cellCount = sh.getRow(0).getLastCellNum();

      for (int i = 1; i <= cellCount; i++) {
        if ((sh.getRow(0).getCell(i).getStringCellValue()).equals(key)) {
          cellNum = i;
          break;
        }
      }
      int rowCount = sh.getLastRowNum() + 1;
      for (int j = 1; j <= rowCount; j++) {
        if ((sh.getRow(j).getCell(0).getStringCellValue()).equals(tcID)) {
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
      log.info("Written successfully on file....");
      workbook.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (in != null || out != null) {
          in.close();
          out.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
