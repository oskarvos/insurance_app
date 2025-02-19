package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorTest {

    @Mock
    TravelCalculatePremiumRequest request;

    TravelCalculatePremiumRequestValidator validator;

    @BeforeEach
    void setUp() {
        validator = new TravelCalculatePremiumRequestValidator();
    }

    @Test
    void shouldReturnErrorValidatePersonFirstNameNull() {
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("Ivanov");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025, 1, 1));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025, 1, 5));
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("personFirstName", errors.getFirst().getField());
        assertEquals("Must not be empty", errors.getFirst().getMessage());
    }

    @Test
    void shouldReturnErrorValidatePersonLastNameNull() {
        when(request.getPersonFirstName()).thenReturn("Ivan");
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025, 1, 1));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025, 1, 5));
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("personLastName", errors.getFirst().getField());
        assertEquals("Must not be empty", errors.getFirst().getMessage());
    }

    @Test
    void shouldReturnErrorValidateAgreementDateFromNull() {
        when(request.getPersonFirstName()).thenReturn("Ivan");
        when(request.getPersonLastName()).thenReturn("Ivanov");
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025, 1, 5));
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agreementDateFrom", errors.getFirst().getField());
        assertEquals("Must not be empty", errors.getFirst().getMessage());
    }

    @Test
    void shouldReturnErrorValidateAgreementDateToNull() {
        when(request.getPersonFirstName()).thenReturn("Ivan");
        when(request.getPersonLastName()).thenReturn("Ivanov");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025, 1, 1));
        when(request.getAgreementDateTo()).thenReturn(null);
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agreementDateTo", errors.getFirst().getField());
        assertEquals("Must not be empty", errors.getFirst().getMessage());
    }

    @Test
    void shouldReturnErrorValidateFieldPersonNull() {
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(null);
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(4, errors.size());
    }

    @Test
    void shouldReturnNotErrorValidatePerson() {
        when(request.getPersonFirstName()).thenReturn("Ivan");
        when(request.getPersonLastName()).thenReturn("Ivanov");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025, 1, 1));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025, 1, 5));
        List<ValidationError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
        assertEquals(0, errors.size());
    }

    @Test
    void shouldReturnErrorValidateDateToFirstDateFrom() {
        when(request.getPersonFirstName()).thenReturn("Ivan");
        when(request.getPersonLastName()).thenReturn("Ivanov");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025, 1, 1));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2024, 1, 1));
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agreementDateToFirstAgreementDateFrom", errors.getFirst().getField());
        assertEquals("DateFrom must be first DateTo", errors.getFirst().getMessage());
    }

}