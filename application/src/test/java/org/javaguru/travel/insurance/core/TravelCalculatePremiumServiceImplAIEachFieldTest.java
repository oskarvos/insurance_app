package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TravelCalculatePremiumServiceImplAIEachFieldTest {

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
    public void shouldPopulateResponsePersonLastName() {
        assertEquals("Ivanov", response.getPersonLastName());
    }

    @Test
    public void shouldPopulateResponsePersonFirstName() {
        assertEquals("Ivan", response.getPersonFirstName());
    }

    @Test
    public void shouldPopulateResponseAgreementDateFrom() {
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    @Test
    public void shouldPopulateResponseAgreementDateTo() {
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

    @Test
    public void shouldCheckForNull() {
        assertNotNull(response,"ответ не должен быть null");
    }
}