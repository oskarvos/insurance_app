package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ValidatePersonLastName implements TravelRequestValidate {

    @Override
    public Optional<ValidationError> executeValidate(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null) || (request.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationError("personLastName", "Must not be empty"))
                : Optional.empty();
    }

}