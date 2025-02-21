package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatePersonFirstNameTest {

    @Mock
    TravelCalculatePremiumRequest request;

    @InjectMocks
    ValidatePersonFirstName validateFirstName;

    @Test
    void shouldReturnErrorValidatePersonFirstNameNull() {
        when(request.getPersonFirstName()).thenReturn(null);
        Optional<ValidationError> errors = validateFirstName.executeValidate(request);
        assertFalse(errors.isEmpty());
        assertEquals("personFirstName", errors.get().getField());
        assertEquals("Must not be empty", errors.get().getMessage());
    }

    @Test
    void shouldReturnErrorValidatePersonFirstNameIsEmpty() {
        when(request.getPersonFirstName()).thenReturn("");
        Optional<ValidationError> errors = validateFirstName.executeValidate(request);
        assertFalse(errors.isEmpty());
        assertEquals("personFirstName", errors.get().getField());
        assertEquals("Must not be empty", errors.get().getMessage());
    }

    @Test
    void shouldReturnListErrorIsEmptyValidatePersonFirstName() {
        when(request.getPersonFirstName()).thenReturn("Ivan");
        Optional<ValidationError> errors = validateFirstName.executeValidate(request);
        assertTrue(errors.isEmpty());
    }

}