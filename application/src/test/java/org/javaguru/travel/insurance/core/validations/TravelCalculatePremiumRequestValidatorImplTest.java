package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorImplTest {

    @Mock
    TravelCalculatePremiumRequest request;

    @InjectMocks
    TravelCalculatePremiumRequestValidatorImpl requestValidator;

    @Test
    void shouldShowValidateErrors() {
        TravelRequestValidate requestValidate1 = mock(TravelRequestValidate.class);
        TravelRequestValidate requestValidate2 = mock(TravelRequestValidate.class);

        when(requestValidate1.executeValidate(request)).thenReturn(Optional.of(new ValidationError()));
        when(requestValidate2.executeValidate(request)).thenReturn(Optional.of(new ValidationError()));

        List<TravelRequestValidate> travelRequestValidations = List.of(requestValidate1, requestValidate2);

        ReflectionTestUtils.setField(requestValidator, "travelRequestValidations", travelRequestValidations);
        List<ValidationError> errors = requestValidator.validate(request);

        assertEquals(2, errors.size());
    }

    @Test
    void shouldShowNoErrors() {
        TravelRequestValidate requestValidate1 = mock(TravelRequestValidate.class);
        TravelRequestValidate requestValidate2 = mock(TravelRequestValidate.class);

        when(requestValidate1.executeValidate(request)).thenReturn(Optional.empty());
        when(requestValidate2.executeValidate(request)).thenReturn(Optional.empty());

        List<TravelRequestValidate> travelRequestValidations = List.of(requestValidate1, requestValidate2);

        ReflectionTestUtils.setField(requestValidator, "travelRequestValidations", travelRequestValidations);
        List<ValidationError> errors = requestValidator.validate(request);

        assertTrue(errors.isEmpty());
    }

}