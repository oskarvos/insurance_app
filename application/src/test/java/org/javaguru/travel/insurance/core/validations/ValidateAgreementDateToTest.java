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
class ValidateAgreementDateToTest {

    @Mock
    TravelCalculatePremiumRequest request;

    @InjectMocks
    ValidateAgreementDateTo validateDateTo;

    @Test
    void shouldReturnErrorValidateAgreementDateToNull() {
        when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError> errors = validateDateTo.executeValidate(request);
        assertFalse(errors.isEmpty());
        assertEquals("agreementDateTo", errors.get().getField());
        assertEquals("Must not be empty", errors.get().getMessage());
    }

    @Test
    void shouldReturnListErrorIsEmptyValidateAgreementDateTo() {
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025,10,20));
        Optional<ValidationError> errors = validateDateTo.executeValidate(request);
        assertTrue(errors.isEmpty());
    }

}