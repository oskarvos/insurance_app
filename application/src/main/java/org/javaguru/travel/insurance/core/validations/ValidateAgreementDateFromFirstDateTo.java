package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ValidateAgreementDateFromFirstDateTo implements TravelRequestValidate {

    @Override
    public Optional<ValidationError> executeValidate(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() != null
                && request.getAgreementDateTo() != null
                && (request.getAgreementDateTo().isBefore(request.getAgreementDateFrom())))
                ? Optional.of(new ValidationError("agreementDateToFirstAgreementDateFrom",
                "DateFrom must be first DateTo"))
                : Optional.empty();
    }
}