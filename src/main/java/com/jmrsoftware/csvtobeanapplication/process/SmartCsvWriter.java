package com.jmrsoftware.csvtobeanapplication.process;

import com.jmrsoftware.csvtobeanapplication.model.RealEstateTransaction;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SmartCsvWriter {
    public void appendCsvFile(RealEstateTransaction realEstateTransaction) {
         final String  FILENAME="realestatetransactions.csv";
        try {

            // Creating writer class to generate csv file
            Writer writer = Files.newBufferedWriter(Paths.get(FILENAME));

            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.DEFAULT_SEPARATOR)
                    .build();

            List<RealEstateTransaction> myTransaction = new ArrayList<>();
            myTransaction.add(realEstateTransaction);
            beanToCsv.write(myTransaction);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
