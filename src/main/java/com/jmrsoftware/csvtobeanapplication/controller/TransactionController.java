package com.jmrsoftware.csvtobeanapplication.controller;

import com.jmrsoftware.csvtobeanapplication.model.ApiResponse;
import com.jmrsoftware.csvtobeanapplication.model.RealEstateTransaction;
import com.jmrsoftware.csvtobeanapplication.service.TransactionService;
import com.jmrsoftware.csvtobeanapplication.service.TransactionServiceImpl;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions/")
@CrossOrigin(origins = {"http://localhost:4200"})
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    //@Qualifier("clientService")
    public void setTransactionService(TransactionService transactionService){
        this.transactionService=transactionService;
    }

    @GetMapping( produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<RealEstateTransaction> getList() throws FileNotFoundException {
        return transactionService.getList();
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<RealEstateTransaction> updateList(@RequestBody RealEstateTransaction realEstateTransaction) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, InterruptedException, IOException {

        return new ApiResponse<RealEstateTransaction>(HttpStatus.OK.value(), "RealEstateTransaction saved successfully.",transactionService.updateList(realEstateTransaction));
    }

}
