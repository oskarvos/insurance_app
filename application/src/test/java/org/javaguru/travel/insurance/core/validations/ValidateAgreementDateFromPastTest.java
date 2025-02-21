package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateAgreementDateFromPastTest {

    @Mock
    TravelCalculatePremiumRequest request;

    @InjectMocks
    ValidateAgreementDateFromPast validateDateFromPast;

    @Test
    void shouldReturnErrorAgreementDateFromIsPastDateNow () {
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025,1,1));
        Optional<ValidationError> errors = validateDateFromPast.executeValidate(request);

        assertFalse(errors.isEmpty());
        assertEquals("agreementDateFromPast", errors.get().getField());
        assertEquals("DateFrom not must be past", errors.get().getMessage());
    }

    @Test
    void shouldReturnCorrectResponseAgreementDateFrom () {
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2026,2,21));
        Optional<ValidationError> errors = validateDateFromPast.executeValidate(request);

        assertTrue(errors.isEmpty());
    }

}