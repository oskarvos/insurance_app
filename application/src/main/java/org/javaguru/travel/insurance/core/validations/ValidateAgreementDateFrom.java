package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ValidateAgreementDateFrom implements TravelRequestValidate {

    @Override
    public Optional<ValidationError> executeValidate(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(new ValidationError("agreementDateFrom",
                "Must not be empty"))
                : Optional.empty();
    }
}