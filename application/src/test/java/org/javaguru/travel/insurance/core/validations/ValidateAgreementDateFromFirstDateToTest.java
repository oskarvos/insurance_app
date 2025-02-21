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
class ValidateAgreementDateFromFirstDateToTest {

    @Mock
    TravelCalculatePremiumRequest request;

    @InjectMocks
    ValidateAgreementDateFromFirstDateTo validateDateFromFirstDateTo;

    @Test
    void shouldReturnErrorWhenDateFromFirstDateTo() {
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025, 10, 1));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025, 9, 1));
        Optional<ValidationError> errors = validateDateFromFirstDateTo.executeValidate(request);

        assertFalse(errors.isEmpty());
        assertEquals("agreementDateToFirstAgreementDateFrom", errors.get().getField());
        assertEquals("DateFrom must be first DateTo", errors.get().getMessage());
    }

    @Test
    void shouldReturnCorrectResponseDateToFirstDateFrom() {
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025, 10, 1));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025, 11, 1));
        Optional<ValidationError> errors = validateDateFromFirstDateTo.executeValidate(request);

        assertTrue(errors.isEmpty());
    }

}