package org.javaguru.travel.insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class CoreResponse {

    private List<ValidationError> error;

    public boolean hasErrors() {
        return error != null && !error.isEmpty();
    }

}