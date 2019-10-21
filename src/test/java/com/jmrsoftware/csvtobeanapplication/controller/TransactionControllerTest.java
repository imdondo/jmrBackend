package com.jmrsoftware.csvtobeanapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmrsoftware.csvtobeanapplication.model.RealEstateTransaction;
import com.jmrsoftware.csvtobeanapplication.service.TransactionService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(RealEstateTransaction.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper mapper;


//    RealEstateTransaction realEstateTransaction2 = new RealEstateTransaction("3881 YELLOWSTONE LN","EL DORADO HILLS",95762,"CA",3,2,1362,"Residential", LocalDateTime.of(2017, 2, 13, 15, 56),285738,38.655245,-121.075915);

    private static class Behavior {
        TransactionService transactionService;

        public static Behavior set(TransactionService transactionService) {
            Behavior behavior = new Behavior();
            behavior.transactionService = transactionService;
            return behavior;
        }

        public Behavior hasNoTransactions() throws FileNotFoundException {
            when(transactionService.getList()).thenReturn(Collections.emptyList());
            return this;
        }

        public Behavior returnSame() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, InterruptedException, IOException {
            when(transactionService.updateList(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
            return this;
        }

        public Behavior returnTransactions(RealEstateTransaction... realEstateTransactions) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, InterruptedException {
            RealEstateTransaction realEstateTransaction1 = new RealEstateTransaction();
            realEstateTransaction1.setStreet("3882 YELLOWSTONE LN");
            realEstateTransaction1.setCity("EL DORADO HILLS");
            realEstateTransaction1.setZip(95762);
            realEstateTransaction1.setState("CA");
            realEstateTransaction1.setBeds(3);
            realEstateTransaction1.setBaths(2);
            realEstateTransaction1.setSq_ft(1362);
            realEstateTransaction1.setType("Residential");
            realEstateTransaction1.setSale_date(LocalDate.of(2017, 2, 13));
            realEstateTransaction1.setPrice(235738.000);
            realEstateTransaction1.setLatitude(38.655245f);
            realEstateTransaction1.setLatitude(-121.075915f);

            RealEstateTransaction realEstateTransaction2 = new RealEstateTransaction();
            realEstateTransaction2.setStreet("3882 ARIGATO LN");
            realEstateTransaction2.setCity("KYOTTO");
            realEstateTransaction2.setZip(95762);
            realEstateTransaction2.setState("CA");
            realEstateTransaction2.setBeds(3);
            realEstateTransaction2.setBaths(2);
            realEstateTransaction2.setSq_ft(1362);
            realEstateTransaction2.setType("Residential");
            realEstateTransaction2.setSale_date(LocalDate.of(2017, 2, 13));
            realEstateTransaction2.setPrice(285738.000);
            realEstateTransaction2.setLatitude(38.655245f);
            realEstateTransaction2.setLatitude(-121.075915f);
            List<RealEstateTransaction> transactionList=Arrays.asList(realEstateTransaction1, realEstateTransaction2);
            when(transactionService.getList()).thenReturn((transactionList));
            for (RealEstateTransaction realEstateTransaction : realEstateTransactions) {
                when(transactionService.updateList(realEstateTransaction)).thenReturn(realEstateTransaction);
            }
            return this;
        }
    }

//    @Test
//    public void getList_NoTransactions() throws Exception {
//        Behavior.set(transactionService).hasNoTransactions();
//        String rst = mapper.writeValueAsString(null);
//        List<RealEstateTransaction> list = new ArrayList<RealEstateTransaction>();
//        list.addAll(Arrays.asList(null));
//
//        when(transactionService.getList()).thenReturn(list);
//
//        // when
//        List<RealEstateTransaction> result = transactionService.getList();
//
//        // then
//        assertThat(result.size()).isEqualTo(0);
//
////        assertThat(result.get(0).getStreet())
////                .isEqualTo(realEstateTransaction1.getStreet());
//    }

    @Test
    public void getList_AtLeastOneTransaction() throws Exception {
        List<RealEstateTransaction> list = new ArrayList<RealEstateTransaction>();
        RealEstateTransaction realEstateTransaction1 = new RealEstateTransaction();
        realEstateTransaction1.setStreet("3882 YELLOWSTONE LN");
        realEstateTransaction1.setCity("EL DORADO HILLS");
        realEstateTransaction1.setZip(95762);
        realEstateTransaction1.setState("CA");
        realEstateTransaction1.setBeds(3);
        realEstateTransaction1.setBaths(2);
        realEstateTransaction1.setSq_ft(1362);
        realEstateTransaction1.setType("Residential");
        realEstateTransaction1.setSale_date(LocalDate.of(2017, 2, 13));
        realEstateTransaction1.setPrice(235738.000);
        realEstateTransaction1.setLatitude(38.655245f);
        realEstateTransaction1.setLatitude(-121.075915f);
        Behavior.set(transactionService).returnTransactions(realEstateTransaction1);
        String rst = mapper.writeValueAsString(realEstateTransaction1);
        list.addAll(Arrays.asList(realEstateTransaction1));

        when(transactionService.getList()).thenReturn(list);

        // when
        List<RealEstateTransaction> result = transactionService.getList();

        // then
        assertThat(result.size()).isEqualTo(1);

        assertThat(result.get(0).getStreet())
                .isEqualTo(realEstateTransaction1.getStreet());
    }

    @Test
    public void getList_MoreThanOneTransaction() throws Exception {
        List<RealEstateTransaction> list = new ArrayList<RealEstateTransaction>();
        RealEstateTransaction realEstateTransaction2 = new RealEstateTransaction();
        realEstateTransaction2.setStreet("3882 ARIGATO LN");
        realEstateTransaction2.setCity("KYOTTO");
        realEstateTransaction2.setZip(95762);
        realEstateTransaction2.setState("CA");
        realEstateTransaction2.setBeds(3);
        realEstateTransaction2.setBaths(2);
        realEstateTransaction2.setSq_ft(1362);
        realEstateTransaction2.setType("Residential");
        realEstateTransaction2.setSale_date(LocalDate.of(2017, 2, 13));
        realEstateTransaction2.setPrice(285738.000);
        realEstateTransaction2.setLatitude(38.655245f);
        realEstateTransaction2.setLatitude(-121.075915f);

        RealEstateTransaction realEstateTransaction1 = new RealEstateTransaction();
        realEstateTransaction1.setStreet("3882 YELLOWSTONE LN");
        realEstateTransaction1.setCity("EL DORADO HILLS");
        realEstateTransaction1.setZip(95762);
        realEstateTransaction1.setState("CA");
        realEstateTransaction1.setBeds(3);
        realEstateTransaction1.setBaths(2);
        realEstateTransaction1.setSq_ft(1362);
        realEstateTransaction1.setType("Residential");
        realEstateTransaction1.setSale_date(LocalDate.of(2017, 2, 13));
        realEstateTransaction1.setPrice(235738.000);
        realEstateTransaction1.setLatitude(38.655245f);
        realEstateTransaction1.setLatitude(-121.075915f);

        Behavior.set(transactionService).returnTransactions(realEstateTransaction1, realEstateTransaction2);
        List<RealEstateTransaction> transactions = Arrays.asList(realEstateTransaction1, realEstateTransaction2);
        String transactionContent = mapper.writeValueAsString(transactions);
        System.out.println("This is the second "+ transactionContent);

        list.addAll(Arrays.asList(realEstateTransaction1, realEstateTransaction2));

        when(transactionService.getList()).thenReturn(list);

        // when
        List<RealEstateTransaction> result = transactionService.getList();

        // then
        assertThat(result.size()).isEqualTo(2);

        assertThat(result.get(0).getStreet())
                .isEqualTo(realEstateTransaction1.getStreet());

        assertThat(result.get(1).getStreet())
                .isEqualTo(realEstateTransaction2.getStreet());
    }

    @Test
    public void updateList_Positive() throws Exception {
        RealEstateTransaction realEstateTransaction1 = new RealEstateTransaction();
        realEstateTransaction1.setStreet("3882 YELLOWSTONE LN");
        realEstateTransaction1.setCity("EL DORADO HILLS");
        realEstateTransaction1.setZip(95762);
        realEstateTransaction1.setState("CA");
        realEstateTransaction1.setBeds(3);
        realEstateTransaction1.setBaths(2);
        realEstateTransaction1.setSq_ft(1362);
        realEstateTransaction1.setType("Residential");
        realEstateTransaction1.setSale_date(LocalDate.of(2017, 2, 13));
        realEstateTransaction1.setPrice(235738.000);
        realEstateTransaction1.setLatitude(38.655245f);
        realEstateTransaction1.setLatitude(-121.075915f);
        Behavior.set(transactionService).returnSame();
        String transactionContent = mapper.writeValueAsString(realEstateTransaction1);
        System.out.println("Json Content" + realEstateTransaction1);
        System.out.println("This is the third "+ transactionContent);

        when(transactionService.updateList(realEstateTransaction1)).thenReturn(realEstateTransaction1);
        // when
        List<RealEstateTransaction> result = transactionService.getList();

        // then
        assertThat(result.size()).isEqualTo(0);
        given(transactionService.updateList(Mockito.any())).willReturn(realEstateTransaction1);

    }
    @Test
    public void testGetList() throws FileNotFoundException {
        // given
        List<RealEstateTransaction> list = new ArrayList<RealEstateTransaction>();
        RealEstateTransaction realEstateTransaction1 = new RealEstateTransaction();
        realEstateTransaction1.setStreet("3882 YELLOWSTONE LN");
        realEstateTransaction1.setCity("EL DORADO HILLS");
        realEstateTransaction1.setZip(95762);
        realEstateTransaction1.setState("CA");
        realEstateTransaction1.setBeds(3);
        realEstateTransaction1.setBaths(2);
        realEstateTransaction1.setSq_ft(1362);
        realEstateTransaction1.setType("Residential");
        realEstateTransaction1.setSale_date(LocalDate.of(2017, 2, 13));
        realEstateTransaction1.setPrice(235738.000);
        realEstateTransaction1.setLatitude(38.655245f);
        realEstateTransaction1.setLatitude(-121.075915f);

        RealEstateTransaction realEstateTransaction2 = new RealEstateTransaction();
        realEstateTransaction2.setStreet("3882 ARIGATO LN");
        realEstateTransaction2.setCity("KYOTTO");
        realEstateTransaction2.setZip(95762);
        realEstateTransaction2.setState("CA");
        realEstateTransaction2.setBeds(3);
        realEstateTransaction2.setBaths(2);
        realEstateTransaction2.setSq_ft(1362);
        realEstateTransaction2.setType("Residential");
        realEstateTransaction2.setSale_date(LocalDate.of(2017, 2, 13));
        realEstateTransaction2.setPrice(285738.000);
        realEstateTransaction2.setLatitude(38.655245f);
        realEstateTransaction2.setLatitude(-121.075915f);
        list.addAll(Arrays.asList(realEstateTransaction1, realEstateTransaction2));

        when(transactionService.getList()).thenReturn(list);

        // when
        List<RealEstateTransaction> result = transactionService.getList();

        // then
        assertThat(result.size()).isEqualTo(2);

        assertThat(result.get(0).getStreet())
                .isEqualTo(realEstateTransaction1.getStreet());

        assertThat(result.get(1).getStreet())
                .isEqualTo(realEstateTransaction2.getStreet());
    }

    @Test
    public void testUpdateList() throws Exception {

        List<RealEstateTransaction> list = new ArrayList<RealEstateTransaction>();
        RealEstateTransaction realEstateTransaction1 = new RealEstateTransaction();
        realEstateTransaction1.setStreet("3882 YELLOWSTONE LN");
        realEstateTransaction1.setCity("EL DORADO HILLS");
        realEstateTransaction1.setZip(95762);
        realEstateTransaction1.setState("CA");
        realEstateTransaction1.setBeds(3);
        realEstateTransaction1.setBaths(2);
        realEstateTransaction1.setSq_ft(1362);
        realEstateTransaction1.setType("Residential");
        realEstateTransaction1.setSale_date(LocalDate.of(2017, 2, 13));
        realEstateTransaction1.setPrice(235738.000);
        realEstateTransaction1.setLatitude(38.655245f);
        realEstateTransaction1.setLatitude(-121.075915f);
        //building the mock response
        //mocking the bean for any object of AddUserRequest.class
        Mockito.when(transactionService.updateList(ArgumentMatchers.any(RealEstateTransaction.class))).thenReturn(realEstateTransaction1);

    }
}
