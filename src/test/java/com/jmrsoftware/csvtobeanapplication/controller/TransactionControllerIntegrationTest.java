package com.jmrsoftware.csvtobeanapplication.controller;

import com.jmrsoftware.csvtobeanapplication.process.SmartCsvReader;
import com.jmrsoftware.csvtobeanapplication.process.SmartCsvWriter;
import com.jmrsoftware.csvtobeanapplication.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class TransactionControllerIntegrationTest {

    @MockBean
    private SmartCsvReader smartCsvReader;
    @MockBean
    private SmartCsvWriter smartCsvWriter;
    @MockBean
    private TransactionService transactionService;

    @Autowired
    TransactionController transactionController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenTransactionControllerInjected_thenNotNull() throws Exception {
        assertThat(transactionController,is("123"));
    }

    @Test
    public void whenGetRequestToBeneficiaries_thenCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/beneficiaries")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));

    }

    @Test
    public void whenPostRequestToBeneficiariesAndValidTransaction_thenCorrectResponse() throws Exception {
        MediaType jsonUtf8 = new MediaType(MediaType.APPLICATION_JSON_VALUE, String.valueOf(Charset.forName("UTF-8")));
        String Transaction = "{\"TransactionName\": \"Jane Dore\", \"accountNumber\" : \"123456789\", \"bankName\" : \"ABSA\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/beneficiaries")
                .content(Transaction)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(jsonUtf8));
    }

    @Test
    public void whenPostRequestToBeneficiariesAndInValidTransaction_thenCorrectReponse() throws Exception {
        String Transaction = "{\"TransactionName\": \"\", \"accountNumber\" : \"123456789\", \"bankName\" : \"ABSA\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/beneficiaries")
                .content(Transaction)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.beneficiaryName", Is.is("beneficiaryName is mandatory")))
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}
