package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private DateTimeService dateService;
    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    private TravelCalculatePremiumRequest request;
    private TravelCalculatePremiumResponse response;

    @BeforeEach
    void setUp() {
        request = createRequestWithFields();
        when(dateService.calculateDaysBetweenDates(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(25L);
        when(requestValidator.validate(request)).thenReturn(List.of());
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
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

    @Test
    void shouldPopulateResponseAgreementDateTo() {
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

    @Test
    void shouldPopulateArgumentPriceNotNull() {
        assertNotNull(response.getAgreementPrice());
    }

    @Test
    void shouldCalculateDaysBetweenDate() {
        BigDecimal actual = BigDecimal.valueOf(25);
        assertEquals(response.getAgreementPrice(), actual);
    }

    private TravelCalculatePremiumRequest createRequestWithFields() {
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("Ivanov");
        request.setAgreementDateFrom(LocalDate.now());
        request.setAgreementDateTo(LocalDate.now());
        return request;
    }

}