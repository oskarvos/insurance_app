package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplAIOneTest {

    private TravelCalculatePremiumService service;
    private TravelCalculatePremiumRequest request;
    private TravelCalculatePremiumResponse response;

    @BeforeEach
    void setUp() {
        service = new TravelCalculatePremiumServiceImpl();

        request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("Ivanov");
        request.setPersonFirstName("Ivan");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());

        response = service.calculatePremium(request);
    }

    @Test
    void shouldPopulateResponse() {
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }
}