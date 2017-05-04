package utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Created by ggiorgi on 5/2/2017.
 */
public class ExcelHandler {

    static DataFormatter formatter = new DataFormatter();

    public static Object[][] getTableArray(String FilePath) {
        try {
            String sheetName = PropertiesHandler.getPropertyValue("sheetName");
            FileInputStream excelFile = new FileInputStream(FilePath);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(excelFile);
            XSSFSheet sheet = xssfWorkbook.getSheet(sheetName);
            int startRow = 1;
            int startCol = 0;
            int ci, cj;
            int totalRows = sheet.getLastRowNum();
            int totalCols = sheet.getRow(0).getLastCellNum(); //gives the no of columns which are actually filled with contents(If the 2nd column of 10 columns is not filled you will get 9)
            String[][] tabArray = new String[totalRows][totalCols];

            ci = 0;

            for (int i = startRow; i <= totalRows; i++, ci++) {

                cj = 0;

                for (int j = startCol; j < totalCols; j++, cj++) {

                    tabArray[ci][cj] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
                    System.out.println(tabArray[ci][cj]);

                }

            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }


    public static String[][] readExcelData() {
        XSSFWorkbook workbook = null;

        String excelPath = "C:\\Users\\ggiorgi\\Desktop\\Automation\\automationFramework\\src\\main\\resources\\testData\\testData.xlsx";
        FileInputStream fileInputStream = null;
        try {

            fileInputStream = new FileInputStream(new File(excelPath));
            workbook = new XSSFWorkbook(fileInputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheetAt(0);
        int totalRows = sheet.getPhysicalNumberOfRows();
        int totalColumns = sheet.getRow(1).getPhysicalNumberOfCells();

        String[][] data = new String[totalRows - 1][totalColumns];
        for (int i = 1; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                XSSFCell x = sheet.getRow(i).getCell(j);

                System.out.println(i);
                System.out.println(j);

                data[i - 1][j] = formatter.formatCellValue(x);
                System.out.print(data.length);

            }
        }
        return data;

    }
}
