package org.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void restControllerTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Ivan\",\n" +
                                "\"personLastName\" : \"Ivanov\",\n" +
                                "\"agreementDateFrom\" : \"2025-02-19\",\n" +
                                "\"agreementDateTo\" : \"2025-02-20\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Ivan")))
                .andExpect(jsonPath("personLastName", is("Ivanov")))
                .andExpect(jsonPath("agreementDateFrom", is("2025-02-19")))
                .andExpect(jsonPath("agreementDateTo", is("2025-02-20")))
                .andExpect(jsonPath("agreementPrice", is(1)))
                .andExpect(jsonPath("errors", nullValue()))
                .andReturn();
    }

    @Test
    void shouldShowErrorBadRequestWhenNullFirstName() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : null,\n" +
                                "\"personLastName\" : \"Ivanov\",\n" +
                                "\"agreementDateFrom\" : \"2025-02-19\",\n" +
                                "\"agreementDateTo\" : \"2025-02-20\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors[0].field", is("personFirstName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty")))
                .andExpect(jsonPath("errors", is(hasSize(1))))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    void shouldShowErrorBadRequestWhenNullFirstNameNullLastName() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : null,\n" +
                                "\"personLastName\" : \"\",\n" +
                                "\"agreementDateFrom\" : \"2025-02-19\",\n" +
                                "\"agreementDateTo\" : \"2025-02-20\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors[0].field", is("personFirstName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty")))
                .andExpect(jsonPath("errors[1].field", is("personLastName")))
                .andExpect(jsonPath("errors[1].message", is("Must not be empty")))
                .andExpect(jsonPath("errors", is(hasSize(2))))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    void shouldShowErrorBadRequestWhenNullLastName() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Ivan\",\n" +
                                "\"personLastName\" : null,\n" +
                                "\"agreementDateFrom\" : \"2025-02-19\",\n" +
                                "\"agreementDateTo\" : \"2025-02-20\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors[0].field", is("personLastName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty")))
                .andExpect(jsonPath("errors", is(hasSize(1))))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    void shouldShowErrorBadRequestWhenNullDateFrom() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Ivan\",\n" +
                                "\"personLastName\" : \"Ivanov\",\n" +
                                "\"agreementDateFrom\" : \"\",\n" +
                                "\"agreementDateTo\" : \"2025-02-20\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors[0].field", is("agreementDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty")))
                .andExpect(jsonPath("errors", is(hasSize(1))))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    void shouldShowErrorBadRequestWhenNullDateTo() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Ivan\",\n" +
                                "\"personLastName\" : \"Ivanov\",\n" +
                                "\"agreementDateFrom\" : \"2025-02-19\",\n" +
                                "\"agreementDateTo\" : \"\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors[0].field", is("agreementDateTo")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty")))
                .andExpect(jsonPath("errors", is(hasSize(1))))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }


    @Test
    void shouldShowErrorBadRequestWhenDateFromLaterDateTo() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Ivan\",\n" +
                                "\"personLastName\" : \"Ivanov\",\n" +
                                "\"agreementDateFrom\" : \"2025-02-21\",\n" +
                                "\"agreementDateTo\" : \"2025-02-20\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors[0].field", is("agreementDateToFirstAgreementDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("DateFrom must be first DateTo")))
                .andExpect(jsonPath("errors", is(hasSize(1))))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

}