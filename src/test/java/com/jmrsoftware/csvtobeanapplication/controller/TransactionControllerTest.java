package com.jmrsoftware.csvtobeanapplication.controller;

import com.jmrsoftware.csvtobeanapplication.model.RealEstateTransaction;
import com.jmrsoftware.csvtobeanapplication.service.TransactionServiceImpl;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TransactionControllerTest {
    @InjectMocks
    TransactionController transactionController;

    @Mock
    TransactionServiceImpl transactionService;

    @Test
    public void testUpdateList() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, InterruptedException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

//        when(transactionService.updateList(any(RealEstateTransaction.class))).thenReturn(HttpStatus.CREATED);

        RealEstateTransaction realEstateTransaction = new RealEstateTransaction("3882 YELLOWSTONE LN","EL DORADO HILLS",95762,"CA",3,2,1362,"Residential", LocalDateTime.of(2017, 2, 13, 15, 56),235738,38.655245,-121.075915);
        ResponseEntity<Object> responseEntity = transactionController.updateList(realEstateTransaction);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }

    @Test
    public void testGetList() throws FileNotFoundException {
        // given
//        RealEstateTransaction realEstateTransaction = new RealEstateTransaction(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com");
        RealEstateTransaction realEstateTransaction = new RealEstateTransaction("3882 YELLOWSTONE LN","EL DORADO HILLS",95762,"CA",3,2,1362,"Residential",  LocalDateTime.of(2017, 2, 13, 15, 56),235738,38.655245,-121.075915);
        RealEstateTransaction realEstateTransaction1 = new RealEstateTransaction("3882 KYOTO LN","JAMBEZI FALLS",95762,"CA",3,2,1362,"Residential", LocalDateTime.of(2017, 2, 13, 15, 56),235738,38.655245,-121.075915);
        ArrayList<RealEstateTransaction> realEstateTransactions= new ArrayList<RealEstateTransaction>();
        realEstateTransactions.addAll(Arrays.asList(realEstateTransaction1, realEstateTransaction));

        when(transactionService.getList()).thenReturn(realEstateTransactions);

        // when
        List<RealEstateTransaction> result = transactionController.getList();

        // then
        assertThat(result.size()).isEqualTo(2);


    }

}