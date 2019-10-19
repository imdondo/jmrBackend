package com.jmrsoftware.csvtobeanapplication.controller;

import com.jmrsoftware.csvtobeanapplication.model.RealEstateTransaction;
import com.jmrsoftware.csvtobeanapplication.service.TransactionService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = {"http://localhost:4200"})
@Slf4j
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping(path = "/transactions", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<RealEstateTransaction> getList() throws FileNotFoundException {
        return transactionService.getList();
    }

    @PostMapping(path = "/transactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateList(@Valid @RequestBody RealEstateTransaction realEstateTransaction) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, InterruptedException, IOException {
        return ResponseEntity.ok(transactionService.updateList(realEstateTransaction));
    }

    @PostMapping("/transactions")
    public ResponseEntity<Void> updateList(@RequestBody RealEstateTransaction realEstateTransaction, UriComponentsBuilder builder) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, InterruptedException, IOException {
        RealEstateTransaction realEstateTransaction1 = transactionService.updateList(realEstateTransaction);

        if (realEstateTransaction == null) {
            return ResponseEntity.noContent().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/transactions/{id}").buildAndExpand(realEstateTransaction.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
