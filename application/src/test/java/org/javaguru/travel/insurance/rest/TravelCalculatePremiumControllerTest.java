package org.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader jsonFile;

    @Test
    @DisplayName("request success")
    void shouldShowNotErrors() throws Exception {
        compare(
                "rest/TravelCalculatePremiumRequest_success.json",
                "rest/TravelCalculatePremiumResponse_success.json"
        );
    }

    @Test
    @DisplayName("request firstName - null")
    void shouldShowErrorBadRequestWhenNullFirstName() throws Exception {
        compare(
                "rest/TravelCalculatePremiumRequest_firstName_not_provided.json",
                "rest/TravelCalculatePremiumResponse_firstName_not_provided.json"
        );
    }

    @Test
    @DisplayName("request LastName - null")
    void shouldShowErrorBadRequestWhenNullFirstNameNullLastName() throws Exception {
        compare(
                "rest/TravelCalculatePremiumRequest_LastName_not_provided.json",
                "rest/TravelCalculatePremiumResponse_LastName_not_provided.json"
        );
    }

    @Test
    @DisplayName("request DateFrom - null")
    void shouldShowErrorBadRequestWhenNullDateFrom() throws Exception {
        compare(
                "rest/TravelCalculatePremiumRequest_agreementDateFrom_not_provided.json",
                "rest/TravelCalculatePremiumResponse_agreementDateFrom_not_provided.json"
        );
    }

    @Test
    @DisplayName("request DateTo - null")
    void shouldShowErrorBadRequestWhenNullDateTo() throws Exception {
        compare(
                "rest/TravelCalculatePremiumRequest_agreementDateTo_not_provided.json",
                "rest/TravelCalculatePremiumResponse_agreementDateTo_not_provided.json"
        );
    }

    @Test
    @DisplayName("request DateFrom later DateTo - null")
    void shouldShowErrorBadRequestWhenDateFromLaterDateTo() throws Exception {
        compare(
                "rest/TravelCalculatePremiumRequest_dateTo_lessThen_dateFrom.json",
                "rest/TravelCalculatePremiumResponse_dateTo_lessThen_dateFrom.json"
        );
    }

    @Test
    @DisplayName("request firstName and lastName - null")
    void shouldShowErrorBadRequestWhenNullFirstAndLastName() throws Exception {
        compare(
                "rest/TravelCalculatePremiumRequest_first_lastName_not_provided.json",
                "rest/TravelCalculatePremiumResponse_first_lastName_not_provided.json"
        );
    }

    @Test
    @DisplayName("request all fields")
    void shouldShowErrorBadRequestAllField() throws Exception {
        compare(
                "rest/TravelCalculatePremiumRequest_allFields_not_provided.json",
                "rest/TravelCalculatePremiumResponse_allFields_not_provided.json"
        );
    }

    @Test
    @DisplayName("request DateFrom past")
    void shouldShowErrorBadRequestDateFromPast() throws Exception {
        compare(
                "rest/TravelCalculatePremiumRequest_agreementDateFrom_past.json",
                "rest/TravelCalculatePremiumResponse_agreementDateFrom_past.json"
        );
    }

    @Test
    @DisplayName("request DateTo past")
    void shouldShowErrorBadRequestDateToPast() throws Exception {
        compare(
                "rest/TravelCalculatePremiumRequest_agreementDateTo_past.json",
                "rest/TravelCalculatePremiumResponse_agreementDateTo_past.json"
        );
    }

    private void compare(String requestJsonFile, String responseJsonFile) throws Exception {
        String requestJson = jsonFile.readJsonFile(requestJsonFile);
        String responseJson = jsonFile.readJsonFile(responseJsonFile);

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(requestJson)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(responseJson));
    }

}