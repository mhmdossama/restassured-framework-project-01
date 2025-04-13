package api.utilities;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class XLUtility {
    public FileInputStream FI;
    public FileOutputStream FO;

    public XSSFWorkbook WB;
    public XSSFSheet WS;
    public XSSFRow row;
    public XSSFCell cell;
    public String path;

    public XLUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws Exception {
        FI = new FileInputStream(path);
        WB = new XSSFWorkbook(FI);
        WS = WB.getSheet(sheetName);
        int rowCount = WS.getLastRowNum();
        WB.close();
        FI.close();
        return rowCount;
    }

    public int getCellCount(String sheetName, int rowNum) throws Exception {
        FI = new FileInputStream(path);
        WB = new XSSFWorkbook(FI);
        WS = WB.getSheet(sheetName);
        row = WS.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        WB.close();
        FI.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rowNum, int colNum) throws Exception {
        FI = new FileInputStream(path);
        WB = new XSSFWorkbook(FI);
        WS = WB.getSheet(sheetName);
        row = WS.getRow(rowNum);
        cell = row.getCell(colNum);
        String data;
        try {
            data = cell.getStringCellValue();
        } catch (Exception e) {
            data = String.valueOf(cell.getNumericCellValue());
        }
        WB.close();
        FI.close();
        return data;
    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws Exception {
        FI = new FileInputStream(path);
        WB = new XSSFWorkbook(FI);
        WS = WB.getSheet(sheetName);
        row = WS.getRow(rowNum);
        cell = row.createCell(colNum);
        cell.setCellValue(data);
        FO = new FileOutputStream(path);
        WB.write(FO);
        WB.close();
        FI.close();
        FO.close();
    }

    public void fillGreenColor(String sheetName, int rowNum, int colNum) throws Exception {
        FI = new FileInputStream(path);
        WB = new XSSFWorkbook(FI);
        WS = WB.getSheet(sheetName);
        row = WS.getRow(rowNum);
        cell = row.getCell(colNum);
        cell.setCellValue("Pass");
        FO = new FileOutputStream(path);
        WB.write(FO);
        WB.close();
        FI.close();
        FO.close();
    }

    public void closeFile() throws Exception {
        if (FI != null) {
            FI.close();
        }
        if (FO != null) {
            FO.close();
        }
        if (WB != null) {
            WB.close();
        }
    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data, String path) throws Exception {
        FI = new FileInputStream(path);
        WB = new XSSFWorkbook(FI);
        WS = WB.getSheet(sheetName);
        row = WS.getRow(rowNum);
        cell = row.createCell(colNum);
        cell.setCellValue(data);
        FO = new FileOutputStream(path);
        WB.write(FO);
        WB.close();
        FI.close();
        FO.close();
    }
}