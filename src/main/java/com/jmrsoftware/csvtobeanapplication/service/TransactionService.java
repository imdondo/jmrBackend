package com.jmrsoftware.csvtobeanapplication.service;

import com.jmrsoftware.csvtobeanapplication.model.RealEstateTransaction;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public interface TransactionService {

    public List<RealEstateTransaction> getList() throws FileNotFoundException;

    RealEstateTransaction updateList(RealEstateTransaction realEstateTransaction) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, InterruptedException, IOException;
//    RealEstateTransaction save(RealEstateTransaction realEstateTransaction);
//    List<RealEstateTransaction> findAll();
}
