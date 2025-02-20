package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;

import java.util.Optional;

public interface TravelRequestValidate {

    Optional<ValidationError> executeValidate(TravelCalculatePremiumRequest request);
}
