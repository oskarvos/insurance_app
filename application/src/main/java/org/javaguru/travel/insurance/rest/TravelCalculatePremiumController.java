package org.javaguru.travel.insurance.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.TravelCalculatePremiumService;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@RestController
@RequestMapping("/insurance/travel")
public class TravelCalculatePremiumController {

    private final TravelCalculatePremiumRequestLogger requestLogger;
    private final TravelCalculatePremiumResponseLogger responseLogger;
    private final TravelCalculatePremiumService calculatePremiumService;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
        requestLogger.logRequest(request);
        TravelCalculatePremiumResponse response = calculatePremiumService.calculatePremium(request);
        responseLogger.logResponse(response);
        return response;
    }
}