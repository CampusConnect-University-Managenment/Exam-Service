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

                if (rowNumber == 0) { // skip header
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                StudentResultEntity student = new StudentResultEntity();
                int cellIdx = 0;

                while (cellsInRow.hasNext()) {
                    Cell cell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0 -> student.setStudentId(cell.getStringCellValue());
                        case 1 -> student.setStudentName(cell.getStringCellValue());
                        case 2 -> student.setSem(cell.getStringCellValue());
                        case 3 -> student.setRegNo(cell.getStringCellValue());
                        case 4 -> student.setCourseCode(cell.getStringCellValue());
                        case 5 -> student.setCourseTitle(cell.getStringCellValue());
                        case 6 -> student.setCredits(cell.getStringCellValue());
                        case 7 -> student.setGradePoints(cell.getStringCellValue());
                        case 8 -> student.setLetterGrade(cell.getStringCellValue());
                    }
                    cellIdx++;
                }
                results.add(student);
            }

            workbook.close();
            return results;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse Excel file: " + e.getMessage());
        }
    }
}
