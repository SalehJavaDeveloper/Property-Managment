//package com.example.property.util;
//
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.stereotype.Component;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//@Component
//public class ExcelParser {
//
//    public Object excelParser(FileInputStream file, Object object) {
//        try {
//            FileInputStream excelFile = new FileInputStream(file.toString());
//            Workbook workbook = new XSSFWorkbook(excelFile);
//
//            Sheet sheet = workbook.getSheetAt(0);
//
//            Iterator<Row> rowIterator = sheet.iterator();
//            List<String> toPojo = new ArrayList<>();
//            int cell = 0;
//            while (rowIterator.hasNext()) {
//                cell = cell + 1;
//                Row row = rowIterator.next();
//                toPojo.add(row.getCell(cell).getStringCellValue());
//
//
//            }
//            object = toPojo;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//
//        return  null;
//    }
//}