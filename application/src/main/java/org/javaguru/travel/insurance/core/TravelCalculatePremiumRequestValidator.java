package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TravelCalculatePremiumRequestValidator {

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName(request).ifPresent(errors::add);
        validateAgreementDateFrom(request).ifPresent(errors::add);
        validateAgreementDateTo(request).ifPresent(errors::add);
        validateAgreementDateFromFirstAgreementDateTo(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validatePersonFirstName(TravelCalculatePremiumRequest request) {
        return ((request.getPersonFirstName() == null) || (request.getPersonFirstName().isEmpty()))
                ? Optional.of(new ValidationError("personFirstName", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<ValidationError> validatePersonLastName(TravelCalculatePremiumRequest request) {
        return ((request.getPersonLastName() == null) || (request.getPersonLastName().isEmpty()))
                ? Optional.of(new ValidationError("personLastName", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateAgreementDateFrom(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null)
                ? Optional.of(new ValidationError("agreementDateTo", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateAgreementDateFromFirstAgreementDateTo(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() != null
                && request.getAgreementDateTo() != null)
                && (request.getAgreementDateTo().isBefore(request.getAgreementDateFrom())
                || (request.getAgreementDateFrom().isEqual(request.getAgreementDateTo())))
                ? Optional.of(new ValidationError("agreementDateToFirstAgreementDateFrom", "DateFrom must be first DateTo"))
                : Optional.empty();
    }

}