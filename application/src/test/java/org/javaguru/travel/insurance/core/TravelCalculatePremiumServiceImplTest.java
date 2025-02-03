package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

    @Test
    public void shouldPopulateResponsePersonLastName() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("Ivanov");

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals("Ivanov", response.getPersonLastName());
    }

    @Test
    public void shouldPopulateResponsePersonFirstName() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals("Ivan", response.getPersonFirstName());
    }

    @Test
    public void shouldPopulateResponseAgreementDateFrom() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(new Date());

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    @Test
    public void shouldPopulateResponseAgreementDateTo() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(new Date());

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }
}