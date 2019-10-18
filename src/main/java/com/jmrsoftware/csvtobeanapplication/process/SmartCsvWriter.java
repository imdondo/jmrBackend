package com.jmrsoftware.csvtobeanapplication.process;

import com.jmrsoftware.csvtobeanapplication.model.RealEstateTransaction;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.boot.autoconfigure.data.elasticsearch.ReactiveElasticsearchRepositoriesAutoConfiguration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


public class SmartCsvWriter {
    public void appendCsvFile(RealEstateTransaction realEstateTransaction) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, InterruptedException {
//        Writer writer  = new FileWriter("src/main/resources/realestatetransactions.csv");
        CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/realestatetransactions.csv", true));

//
        StatefulBeanToCsv<RealEstateTransaction> beanToCsv = new StatefulBeanToCsvBuilder<RealEstateTransaction>(writer)
                .withSeparator(',')
                .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                .withOrderedResults(true)
                .build();

        beanToCsv.write(realEstateTransaction);
        beanToCsv.getCapturedExceptions();
        writer.close();

    }
}

