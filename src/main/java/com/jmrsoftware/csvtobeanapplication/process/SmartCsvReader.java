package com.jmrsoftware.csvtobeanapplication.process;

import com.jmrsoftware.csvtobeanapplication.model.RealEstateTransaction;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class SmartCsvReader {
    public List<RealEstateTransaction> readCsvFile() throws FileNotFoundException {
        FileReader reader =  new FileReader("realestatetransactions.csv");
        CsvToBean<RealEstateTransaction> csvtobean= new CsvToBeanBuilder<RealEstateTransaction>(reader)
                .withType(RealEstateTransaction.class)
                .withSeparator(',')
                .withSkipLines(1)
                .build();

        return csvtobean.parse();
    }
}
