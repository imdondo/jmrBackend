package com.jmrsoftware.csvtobeanapplication.process;

import com.jmrsoftware.csvtobeanapplication.model.RealEstateTransaction;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.context.annotation.ComponentScan;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@ComponentScan
public class SmartCsvReader {

    public List<RealEstateTransaction> readCsvFile() throws FileNotFoundException {
        FileReader reader = new FileReader("src/main/resources/realestatetransactions.csv");
        CsvToBean<RealEstateTransaction> csvtobean = new CsvToBeanBuilder<RealEstateTransaction>(reader)
                .withType(RealEstateTransaction.class)
                .withSeparator(';')
                .build();

        return csvtobean.parse();
    }
}
