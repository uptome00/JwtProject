package com.example.JwtProject.repository;

import lombok.Data;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Data
public class ExcelRepository extends XSSFWorkbook {

    private FileInputStream fileInputStream;
    private XSSFWorkbook workbook;

    public ExcelRepository(String path) throws IOException {
        this.fileInputStream = new FileInputStream(path);
        this.workbook = new XSSFWorkbook(fileInputStream);
    }

    public XSSFSheet getSheetBySheetIndex(int indexSheet){
        return workbook.getSheetAt(indexSheet);
    }

    public int getSheetIndexBySheetName(String sheetName){
        return workbook.getSheetIndex(sheetName);
    }
}
