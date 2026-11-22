package com.diego.shopeeProfitManager.controller;

import com.diego.shopeeProfitManager.importacao.ExcelImportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/importacao")
public class ExcelImportController {

    private final ExcelImportService excelImportService;

    public ExcelImportController(ExcelImportService excelImportService) {
        this.excelImportService = excelImportService;
    }

    @PostMapping("/excel")
    public ResponseEntity<String> importarExcel(
            @RequestParam("arquivo") MultipartFile arquivo) {

        excelImportService.importarExcel(arquivo);

        return ResponseEntity.ok("Arquivo Excel recebido e processado com sucesso!");
    }

}
