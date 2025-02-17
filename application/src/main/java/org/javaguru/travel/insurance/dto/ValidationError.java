package org.javaguru.travel.insurance.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError {

    private String field;
    private String massage;
}