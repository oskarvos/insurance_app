package org.javaguru.travel.insurance.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ValidationError {

    private String field;
    private String massage;
}