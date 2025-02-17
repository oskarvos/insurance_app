package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelPremiumUnderwritingTest {

    @Mock
    private TravelCalculatePremiumRequest request;
    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private TravelPremiumUnderwriting premiumUnderwriting;

    @Test
    void shouldCalculateBetweenDaysInBigDecimal() {
        when(dateTimeService.calculateDaysBetweenDates(
                request.getAgreementDateFrom(),
                request.getAgreementDateTo()))
                .thenReturn(2L);
        BigDecimal result = premiumUnderwriting.premiumCalculateBigDecimal(request);
        assertEquals(new BigDecimal(2), result);
    }

}