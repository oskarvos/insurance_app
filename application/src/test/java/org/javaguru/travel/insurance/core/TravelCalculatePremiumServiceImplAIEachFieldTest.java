package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TravelCalculatePremiumServiceImplAIEachFieldTest {

    private TravelCalculatePremiumRequest request;
    private TravelCalculatePremiumResponse response;

    @BeforeEach
    void setUp() {
        TravelCalculatePremiumService service = new TravelCalculatePremiumServiceImpl();

        request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("Ivanov");
        request.setPersonFirstName("Ivan");
        request.setAgreementDateFrom(LocalDate.of(2025, 1, 1));
        request.setAgreementDateTo(LocalDate.of(2025, 2, 4));

        response = service.calculatePremium(request);
    }

    @Test
    void shouldPopulateResponsePersonLastName() {
        assertEquals("Ivanov", response.getPersonLastName());
    }

    @Test
    void shouldPopulateResponsePersonFirstName() {
        assertEquals("Ivan", response.getPersonFirstName());
    }

    @Test
    void shouldPopulateResponseAgreementDateFrom() {
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    @Test
    void shouldPopulateResponseAgreementDateTo() {
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

    @Test
    void shouldPopulateResponseAgreementPrice() {
        assertEquals(new BigDecimal(34), response.getAgreementPrice());
    }

    @Test
    void shouldCheckForNull() {
        assertNotNull(response, "ответ не должен быть null");
    }

    @Test
    void shouldCalculateDaysBetweenDate() {
        long result = calculateDaysBetweenAgreementDates(request);

        assertEquals(34, result);
    }

    private Long calculateDaysBetweenAgreementDates(TravelCalculatePremiumRequest request) {  // считает коллиство дней между датами
        return ChronoUnit.DAYS.between(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}