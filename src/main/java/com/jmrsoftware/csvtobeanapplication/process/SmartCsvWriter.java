package com.jmrsoftware.csvtobeanapplication.process;

import com.jmrsoftware.csvtobeanapplication.model.RealEstateTransaction;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.context.annotation.ComponentScan;

import java.io.FileWriter;
import java.io.IOException;

@ComponentScan
public class SmartCsvWriter {
    public RealEstateTransaction appendCsvFile(RealEstateTransaction realEstateTransaction) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, InterruptedException {
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

        return realEstateTransaction;
    }
}

