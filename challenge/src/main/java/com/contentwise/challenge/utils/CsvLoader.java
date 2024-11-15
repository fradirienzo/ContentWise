package com.contentwise.challenge.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CsvLoader implements CommandLineRunner {

    private final CsvService csvService;

    public CsvLoader(CsvService csvService) {
        this.csvService = csvService;
    }

    @Override
    public void run(String... args) throws Exception {
        String filePath = "static/users.csv";
        csvService.loadCsvData(filePath);
    }
}
