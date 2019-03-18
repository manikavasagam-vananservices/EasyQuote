package com.vanan.Common;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileProcessing {

    private HSSFSheet sheet;
    private HSSFWorkbook workbook;
    private HSSFCell cell;

    private XSSFSheet ExcelWSheet;
    private XSSFWorkbook ExcelWBook;
    private XSSFCell Cell;
    private String fileExtenstion = "";

    public void setExcelFile(String fileName, String SheetName) {
        try {
            fileExtenstion = fileName.substring(fileName.lastIndexOf(".") + 1);
            FileInputStream ExcelFile = new FileInputStream(fileName);
            if (fileExtenstion.equals("xlsx")) {
                ExcelWBook = new XSSFWorkbook(ExcelFile);
                ExcelWSheet = ExcelWBook.getSheet(SheetName);
            } else if (fileExtenstion.equals("xls")) {
                workbook = new HSSFWorkbook(ExcelFile);
                sheet = workbook.getSheet(SheetName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCellData(int RowNum, int ColNum) {

        String CellData = "";
        try {

            if (fileExtenstion.equals("xlsx")) {
                Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
                CellData = Cell.getStringCellValue().trim();
            } else if (fileExtenstion.equals("xls")) {
                cell = sheet.getRow(RowNum).getCell(ColNum);
                CellData = cell.getStringCellValue().trim();
            }
        } catch (Exception e) {
        }
        return CellData;
    }

    public double getNumericCellData(int RowNum, int ColNum) {

        Double CellData = 0.00;
        try {

            if (fileExtenstion.equals("xlsx")) {
                Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
                CellData = Cell.getNumericCellValue();
            } else if (fileExtenstion.equals("xls")) {
                cell = sheet.getRow(RowNum).getCell(ColNum);
                CellData = cell.getNumericCellValue();
            }
        } catch (Exception e) {
        }
        return CellData;
    }

    public double getFloatCellData(int RowNum, int ColNum) {

        Double CellData = 0.00;
        try {

            if (fileExtenstion.equals("xlsx")) {

                Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
                Cell.setCellType(CellType.STRING);
                CellData = Double.valueOf(Cell.getStringCellValue());
            } else if (fileExtenstion.equals("xls")) {
                cell = sheet.getRow(RowNum).getCell(ColNum);
                CellData = cell.getNumericCellValue();
            }
        } catch (Exception e) {
        }
        return CellData;
    }


    public void setCellData(String Result, int RowNum, int ColNum) {
        try {
            if (fileExtenstion.equals("xlsx")) {
                Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
                Cell.setCellValue(Result);

            } else if (fileExtenstion.equals("xls")) {
                cell = sheet.getRow(RowNum).getCell(ColNum);
                cell.setCellValue(Result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeFileContent(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            if (fileExtenstion.equals("xlsx")) {

                ExcelWBook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            } else if (fileExtenstion.equals("xls")) {

                workbook.write();
                fileOut.flush();
                fileOut.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getRowUsed() {
        int RowCount = 0;
        try {
            if (fileExtenstion.equals("xlsx")) {
                RowCount = ExcelWSheet.getLastRowNum();
            } else if (fileExtenstion.equals("xls")) {
                RowCount = sheet.getLastRowNum();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        //System.out.println("ROW "+ RowCount);
        return RowCount;
    }
}
