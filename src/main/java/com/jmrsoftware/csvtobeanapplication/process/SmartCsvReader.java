package com.jmrsoftware.csvtobeanapplication.process;

import com.jmrsoftware.csvtobeanapplication.model.RealEstateTransaction;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class SmartCsvReader {
//    public List<RealEstateTransaction> readCsvFile() throws FileNotFoundException {
//    public void readCsvFile() throws FileNotFoundException {
//        FileReader reader =  new FileReader("src/main/resources/realestatetransactions.csv");
//            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
//            strategy.setType(RealEstateTransaction.class);
//            String[] memberFieldsToBindTo = {"street", "city", "zip", "state", "beds", "baths", "sq_ft", "type", "sale_date", "price", "latitude", "longitude"};
//            strategy.setColumnMapping(memberFieldsToBindTo);
//
//            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
//                    .withMappingStrategy(strategy)
//                    .withSkipLines(1)
//                    .withIgnoreLeadingWhiteSpace(true)
//                    .build();
//
//            Iterator<RealEstateTransaction> realEstateTransactionIterator = csvToBean.iterator();
//            while (realEstateTransactionIterator.hasNext()) {
//                RealEstateTransaction realEstateTransaction = realEstateTransactionIterator.next();
//
//                System.out.println(realEstateTransaction.getStreet());
//                RealEstateTransaction formattedRealEstateTransaction = extractTransactionProperties(realEstateTransaction);
//                System.out.println("Is this it? "+realEstateTransaction);
//                System.out.println(formattedRealEstateTransaction.getZip());
//                System.out.println("---------------------------");
//            }
//
//    }
//    private static RealEstateTransaction extractTransactionProperties(RealEstateTransaction realEstateTransaction) {
//        String[] values = (realEstateTransaction.getStreet().split(";"));
//        realEstateTransaction.setStreet(values[0]);
//        realEstateTransaction.setCity(values[1]);
//        realEstateTransaction.setZip(Integer.valueOf(values[2]));
//        realEstateTransaction.setState(values[3]);
//        realEstateTransaction.setBeds(Integer.valueOf(values[4]));
//        realEstateTransaction.setBaths(Integer.valueOf(values[5]));
//        realEstateTransaction.setSq_ft(Integer.valueOf(values[6]));
//        realEstateTransaction.setType(values[7]);
////        realEstateTransaction.setSale_date(Date.valueOf((values[8])));
//        realEstateTransaction.setPrice(Double.valueOf(values[9]));
//        realEstateTransaction.setLatitude(Float.valueOf(values[10]));
//        realEstateTransaction.setLongitude(Float.valueOf(values[11]));
//        System.out.println("Or maybe here " +realEstateTransaction);
//        return realEstateTransaction;
//    }
public List<RealEstateTransaction> readCsvFile() throws FileNotFoundException {
    FileReader reader =  new FileReader("src/main/resources/realestatetransactions.csv");
    CsvToBean<RealEstateTransaction> csvtobean= new CsvToBeanBuilder<RealEstateTransaction>(reader)
            .withType(RealEstateTransaction.class)
            .withSeparator(';')
            .build();

    return csvtobean.parse();
}
}
