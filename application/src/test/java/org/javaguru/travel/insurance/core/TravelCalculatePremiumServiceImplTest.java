package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private TravelPremiumUnderwriting underwriting;

    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    private TravelCalculatePremiumRequest request;
    private TravelCalculatePremiumResponse response;

    @Test
    void shouldPopulateResponsePersonLastName() {
        request = createRequestWithFieldsNotErrors();
        response = service.calculatePremium(request);

        assertEquals("Ivanov", response.getPersonLastName());
    }

    @Test
    void shouldPopulateResponsePersonFirstName() {
        request = createRequestWithFieldsNotErrors();
        response = service.calculatePremium(request);

        assertEquals("Ivan", response.getPersonFirstName());
    }

    @Test
    void shouldPopulateResponseAgreementDateFrom() {
        request = createRequestWithFieldsNotErrors();
        response = service.calculatePremium(request);

        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
        assertEquals(LocalDate.of(2025, 2, 17), response.getAgreementDateFrom());
    }

    @Test
    void shouldPopulateResponseAgreementDateTo() {
        request = createRequestWithFieldsNotErrors();
        response = service.calculatePremium(request);

        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
        assertEquals(LocalDate.of(2025, 2, 19), response.getAgreementDateTo());
    }

    @Test
    void shouldPopulateArgumentPriceNotNull() {
        request = createRequestWithFieldsNotErrors();
        when(underwriting.premiumCalculateBigDecimal(request)).thenReturn(new BigDecimal(2));
        response = service.calculatePremium(request);

        assertNotNull(response.getAgreementPrice());
    }

    @Test
    void shouldCalculateDaysBetweenDate() {
        request = createRequestWithFieldsNotErrors();
        when(underwriting.premiumCalculateBigDecimal(request)).thenReturn(new BigDecimal(2L));
        response = service.calculatePremium(request);

        BigDecimal actual = BigDecimal.valueOf(2);
        assertEquals(actual,response.getAgreementPrice());
    }

    @Test
    void shouldReturnListErrorsLengthSize() {
        var errors = new ValidationError("field", "message");
        when(requestValidator.validate(request)).thenReturn(List.of(errors));
        response = service.calculatePremium(request);
        assertEquals(1, response.getErrors().size());
    }

    private TravelCalculatePremiumRequest createRequestWithFieldsNotErrors() {
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("Ivanov");
        request.setAgreementDateFrom(LocalDate.of(2025, 2, 17));
        request.setAgreementDateTo(LocalDate.of(2025, 2, 19));
        return request;
    }
}