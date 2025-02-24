package org.javaguru.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelPremiumUnderwriting {

    private final DateTimeService dateTimeService;

    // устанавливаем расчетное время создаем BigDecimal в днях
    BigDecimal premiumCalculateBigDecimal(TravelCalculatePremiumRequest request) {
        return new BigDecimal(dateTimeService.calculateDaysBetweenDates(
                request.getAgreementDateFrom(),
                request.getAgreementDateTo()));
    }
}