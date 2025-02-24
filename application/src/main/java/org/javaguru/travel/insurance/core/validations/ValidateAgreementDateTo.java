package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ValidateAgreementDateTo implements TravelRequestValidate {

    @Override
    public Optional<ValidationError> executeValidate(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null)
                ? Optional.of(new ValidationError("agreementDateTo",
                "Must not be empty"))
                : Optional.empty();
    }
}