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
class ValidateAgreementDateFromTest {

    @Mock
    TravelCalculatePremiumRequest request;

    @InjectMocks
    ValidateAgreementDateFrom validateDateFrom;

    @Test
    void shouldReturnErrorValidateAgreementDateFromNull() {
        when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> errors = validateDateFrom.executeValidate(request);
        assertFalse(errors.isEmpty());
        assertEquals("agreementDateFrom", errors.get().getField());
        assertEquals("Must not be empty", errors.get().getMessage());
    }

    @Test
    void shouldReturnListErrorIsEmptyValidateAgreementDateFrom() {
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025,10,20));
        Optional<ValidationError> errors = validateDateFrom.executeValidate(request);
        assertTrue(errors.isEmpty());
    }

}