package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

    @Test
    void shouldPopulateResponsePersonLastName() {
        TravelCalculatePremiumRequest request = createRequestWithFields();
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals("Ivanov", response.getPersonLastName());
    }

    @Test
    void shouldPopulateResponsePersonFirstName() {
        TravelCalculatePremiumRequest request = createRequestWithFields();
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals("Ivan", response.getPersonFirstName());
    }

    @Test
    void shouldPopulateResponseAgreementDateFrom() {
        var request = createRequestWithFields();
        var response = service.calculatePremium(request);

        assertEquals(LocalDate.of(2025, 1, 1), response.getAgreementDateFrom());
    }

    @Test
    void shouldPopulateResponseAgreementDateTo() {
        var request = createRequestWithFields();
        var response = service.calculatePremium(request);

        assertEquals(LocalDate.of(2025, 2, 5), response.getAgreementDateTo());
    }

    private TravelCalculatePremiumRequest createRequestWithFields() {
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("Ivanov");
        request.setAgreementDateFrom(LocalDate.of(2025, 1, 1));
        request.setAgreementDateTo(LocalDate.of(2025, 2, 5));
        return request;
    }
}