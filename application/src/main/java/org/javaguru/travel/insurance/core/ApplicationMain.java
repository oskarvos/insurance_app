package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ApplicationMain {

    public static void main(String[] args) {

        TravelCalculatePremiumRequest request = request();
        DateTimeService dateTimeService = new DateTimeService();
        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl(validator, dateTimeService);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        System.out.println(response);
    }


    static public TravelCalculatePremiumRequest request() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("Ivanov");
        request.setAgreementDateFrom(LocalDate.of(2025, 1, 1));
        request.setAgreementDateTo(LocalDate.of(2025, 1, 4));
        return request;
    }
}
