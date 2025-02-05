package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());

        var daysBetween = calculateDaysBetweenAgreementDates(request);
        response.setAgreementPrice(new BigDecimal(daysBetween)); // установливаем расчетное время создаем BigDecimal в днях

        return response;
    }

    private long calculateDaysBetweenAgreementDates(TravelCalculatePremiumRequest request) {  // считает коллиство дней между датами
        return ChronoUnit.DAYS.between(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}
