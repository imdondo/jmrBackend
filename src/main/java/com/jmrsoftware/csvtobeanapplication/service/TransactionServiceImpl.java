package com.jmrsoftware.csvtobeanapplication.service;

import com.jmrsoftware.csvtobeanapplication.model.RealEstateTransaction;
import com.jmrsoftware.csvtobeanapplication.process.SmartCsvReader;
import com.jmrsoftware.csvtobeanapplication.process.SmartCsvWriter;
import com.jmrsoftware.csvtobeanapplication.repository.TransactionRepository;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionService transactionService;
    SmartCsvReader smartCsvReader;
    SmartCsvWriter smartCsvWriter;

    @Override
    public List<RealEstateTransaction> getList() throws FileNotFoundException {

//        List<RealEstateTransaction> transactions = (List<RealEstateTransaction>) transactionRepository.findAll();
        List<RealEstateTransaction> transactions = (List<RealEstateTransaction>) smartCsvReader.readCsvFile();

        return transactions;
    }

    @Override
    public synchronized RealEstateTransaction updateList(RealEstateTransaction realEstateTransaction) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, InterruptedException, IOException {
//        List<RealEstateTransaction> list = transactionRepository.save( realEstateTransaction);
        smartCsvWriter.appendCsvFile(realEstateTransaction);
        return realEstateTransaction;
    }

}