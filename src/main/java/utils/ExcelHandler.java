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
                data[i - 1][j] = formatter.formatCellValue(x);


            }
        }
        return data;

    }
}
