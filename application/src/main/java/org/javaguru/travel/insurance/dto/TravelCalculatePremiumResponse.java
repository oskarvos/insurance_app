package org.javaguru.travel.insurance.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse {

    private String personFirstName;
    private String personLastName;
    private LocalDate agreementDateFrom;
    private LocalDate agreementDateTo;
    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }

    @Override
    public String toString() {
        return "TravelCalculatePremiumResponse{" +
                "personFirstName='" + personFirstName + '\'' +
                ", personLastName='" + personLastName + '\'' +
                ", agreementDateFrom=" + agreementDateFrom +
                ", agreementDateTo=" + agreementDateTo +
                ", agreementPrice=" + agreementPrice +
                "\n" + getError();
    }
}