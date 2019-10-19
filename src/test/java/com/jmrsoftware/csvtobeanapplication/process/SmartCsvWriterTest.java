package com.jmrsoftware.csvtobeanapplication.process;

import com.jmrsoftware.csvtobeanapplication.model.RealEstateTransaction;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

class SmartCsvWriterTest {


    @Test
    public void appendCsvFile() throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException, InterruptedException {
        String filename = "src/main/resources/realestatetransactions.csv";
        RealEstateTransaction realEstateTransaction = new RealEstateTransaction();
        realEstateTransaction.setStreet("KONICHIWA ST");
        realEstateTransaction.setCity("ARIGATO");
        realEstateTransaction.setZip(95838);
        realEstateTransaction.setState("CA");
        realEstateTransaction.setBeds(2);
        realEstateTransaction.setBaths(1);
        realEstateTransaction.setSq_ft(836);
        realEstateTransaction.setType("Residential");
//        LocalDateTime dateTime= LocalDateTime.of(2017, Month.FEBRUARY,3,6,30,40,50000);
        LocalDate dateTime = LocalDate.of(2017, Month.FEBRUARY, 3);
        realEstateTransaction.setSale_date(dateTime);
        realEstateTransaction.setPrice((double) 59222);
        realEstateTransaction.setLatitude((float) 38.631913);
        realEstateTransaction.setLongitude((float) -121.434879);

        new SmartCsvWriter().appendCsvFile(realEstateTransaction);
    }
}