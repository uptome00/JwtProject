package com.example.JwtProject.service;

import com.example.JwtProject.repository.ExcelRepository;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class ExcelService {

    private  ExcelRepository excelRepository;

    public ExcelService( String path) throws IOException {
        this.excelRepository = new ExcelRepository(path);
    }

    public int getWorkBookLenght() {
        excelRepository.getWorkbook().getNumberOfSheets();
        return 0 ;
    }

    public <K, V>Map<K ,V> getData() {
        XSSFSheet sheet = excelRepository.getSheetBySheetIndex(0);
        List<String> headers = new ArrayList<>();
        List<String> data = new ArrayList<>();

        sheet.getRow(0).forEach(coloum -> {headers.add(String.valueOf(coloum));});
        sheet.getRow(1).forEach(coloum -> {data.add(String.valueOf(coloum));});

        Map<String, String> map = toMap(headers,data);
        return (Map<K, V>) map;
    }

    public static <K, V> Map<K, V> toMap(List<K> keys, List<V> values) {
        return IntStream.range(0, keys.size()).boxed()
                .collect(Collectors.toMap(keys::get, values::get));
    }
}
