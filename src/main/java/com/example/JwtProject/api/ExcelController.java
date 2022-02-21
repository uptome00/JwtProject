package com.example.JwtProject.api;

import com.example.JwtProject.service.ExcelService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@RestController
@RequestMapping("/excel")
@RequiredArgsConstructor
@Slf4j
public class ExcelController {

    private ExcelService excelService;

    @PostMapping("/send")
    public ResponseEntity<?> sendApi(@RequestBody Path path) throws IOException, OpenXML4JException, InterruptedException {
//        log.info(path.getPath());
        excelService = new ExcelService(path.getPath());
        int workBookLenght = excelService.getWorkBookLenght();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:4001/user"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.ok().body(excelService.getData());
    }
}

@Data
class Path{
    private String path;
}
