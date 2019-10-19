package com.jmrsoftware.csvtobeanapplication.process;

import com.jmrsoftware.csvtobeanapplication.model.RealEstateTransaction;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

class SmartCsvReaderTest {
    @Test
    public void readCsvFile() throws FileNotFoundException {
        List<RealEstateTransaction> realEstateTransactions = new SmartCsvReader().readCsvFile();
//Ideally this is where I should be logging but just to make sure that I can check the values from the console
        realEstateTransactions.forEach(transaction -> System.out.println(transaction));
    }

}