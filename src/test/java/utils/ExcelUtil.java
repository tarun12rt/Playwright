package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.*;

public class ExcelUtil {

    private static Workbook workbook;
    private static Sheet sheet;

    /**
     * Load Excel file & sheet
     */
    private static void loadExcel(String filePath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to load Excel file", e);
        }
    }

    /**
     * Read data as List of Map (BEST for TestNG)
     */
    public static List<Map<String, String>> getTestData(
            String filePath,
            String sheetName
    ) {
        loadExcel(filePath, sheetName);

        List<Map<String, String>> dataList = new ArrayList<>();
        Row headerRow = sheet.getRow(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row currentRow = sheet.getRow(i);
            Map<String, String> rowData = new HashMap<>();

            for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                String key = headerRow.getCell(j).getStringCellValue();
                String value = getCellValue(currentRow.getCell(j));
                rowData.put(key, value);
            }
            dataList.add(rowData);
        }
        return dataList;
    }

    /**
     * Read single cell
     */
    public static String getCellValue(String filePath, String sheetName, int row, int col) {
        loadExcel(filePath, sheetName);
        return getCellValue(sheet.getRow(row).getCell(col));
    }

    private static String getCellValue(Cell cell) {

        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {

            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                return String.valueOf(cell.getNumericCellValue());

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                return cell.getCellFormula();

            default:
                return "";
        }
    }

}
