package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TravelCalculatePremiumRequestValidatorTest {

    TravelCalculatePremiumRequest request;
    TravelCalculatePremiumRequestValidator validator;

    @BeforeEach
    void setUp() {
        request = new TravelCalculatePremiumRequest();
        validator = new TravelCalculatePremiumRequestValidator();
    }

    @Test
    void shouldValidatePersonFirstNameNullListErrorsSize() {
        request.setPersonFirstName(null);
        request.setPersonLastName("Ivanov");
        request.setAgreementDateFrom(LocalDate.now());
        request.setAgreementDateTo(null);
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(2, errors.size());
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("Must not be empty", errors.get(0).getMassage());
    }

    @Test
    void shouldValidatePersonFirstLastNameNullListErrorsSize() {
        request.setPersonFirstName(null);
        request.setPersonLastName(null);
        request.setAgreementDateFrom(null);
        request.setAgreementDateTo(null);
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(4, errors.size());
        assertEquals("agreementDateFrom", errors.get(2).getField());
        assertEquals("Must not be empty", errors.get(3).getMassage());

    }

    @Test
    void shouldValidatePersonFirstNameErrorMassage() {
        request.setPersonFirstName(null);
        request.setPersonLastName(null);
        request.setAgreementDateFrom(null);
        request.setAgreementDateTo(null);
        List<ValidationError> errors = validator.validate(request);
        assertEquals("agreementDateFrom", errors.get(2).getField());
    }

    @Test
    void shouldValidatePersonFirstLastNameListErrorSize() {
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("Ivanov");
        request.setAgreementDateFrom(LocalDate.of(2025, 1, 1));
        request.setAgreementDateTo(LocalDate.now());
        List<ValidationError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }


    @Test
    void shouldValidateDateFromFirstDateToNoError() {
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("Ivanov");
        request.setAgreementDateFrom(LocalDate.of(2024, 3, 3));
        request.setAgreementDateTo(LocalDate.of(2025, 3, 3));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    void shouldValidateDateToFirstDateFromYesError() {
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("Ivanov");
        request.setAgreementDateFrom(LocalDate.of(2025, 3, 3));
        request.setAgreementDateTo(LocalDate.of(2024, 3, 3));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
    }

}