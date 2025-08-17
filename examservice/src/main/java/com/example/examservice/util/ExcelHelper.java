package com.example.examservice.util;

import com.example.examservice.entity.StudentResultEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<StudentResultEntity> excelToStudentResults(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<StudentResultEntity> results = new ArrayList<>();
            int rowNumber = 0;

            while (rows.hasNext()) {
                Row currentRow = rows.next();

                if (rowNumber == 0) { // ✅ skip header row
                    rowNumber++;
                    continue;
                }

                StudentResultEntity student = new StudentResultEntity();

                // Map based on column index from your Excel
                student.setRegNo(getCellValueAsString(currentRow.getCell(0)));
                student.setStudentName(getCellValueAsString(currentRow.getCell(1)));
                student.setCourseCode(getCellValueAsString(currentRow.getCell(2)));
                student.setCourseTitle(getCellValueAsString(currentRow.getCell(3)));
                student.setCredits(getCellValueAsString(currentRow.getCell(4)));
                student.setGradePoints(getCellValueAsString(currentRow.getCell(5)));
                student.setLetterGrade(getCellValueAsString(currentRow.getCell(6)));

                results.add(student);
            }

            workbook.close();
            return results;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse Excel file: " + e.getMessage());
        }
    }

    // ✅ utility: safely convert any cell to String
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }
}
