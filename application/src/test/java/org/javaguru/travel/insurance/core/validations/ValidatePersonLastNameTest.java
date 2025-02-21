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
class ValidatePersonLastNameTest {

    @Mock
    TravelCalculatePremiumRequest request;

    @InjectMocks
    ValidatePersonLastName validateLastName;

    @Test
    void shouldReturnErrorValidatePersonLastNameNull() {
        when(request.getPersonLastName()).thenReturn(null);
        Optional<ValidationError> errors = validateLastName.executeValidate(request);
        assertFalse(errors.isEmpty());
        assertEquals("personLastName", errors.get().getField());
        assertEquals("Must not be empty", errors.get().getMessage());
    }

    @Test
    void shouldReturnErrorValidatePersonLastNameIsEmpty() {
        when(request.getPersonLastName()).thenReturn("");
        Optional<ValidationError> errors = validateLastName.executeValidate(request);
        assertFalse(errors.isEmpty());
        assertEquals("personLastName", errors.get().getField());
        assertEquals("Must not be empty", errors.get().getMessage());
    }

    @Test
    void shouldReturnListErrorIsEmptyValidatePersonLastName() {
        when(request.getPersonLastName()).thenReturn("Ivanov");
        Optional<ValidationError> errors = validateLastName.executeValidate(request);
        assertTrue(errors.isEmpty());
    }

}