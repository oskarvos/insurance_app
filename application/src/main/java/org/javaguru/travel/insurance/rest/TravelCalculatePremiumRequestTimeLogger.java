package org.javaguru.travel.insurance.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.google.common.base.Stopwatch;

@Component
class TravelCalculatePremiumRequestTimeLogger {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestTimeLogger.class);

    void logRequestTime(Stopwatch stopwatch) {
        if (stopwatch.isRunning()) {
            stopwatch.stop();
            long elapsedMillis = stopwatch.elapsed().toMillis();
            logger.info("Request processing time (ms): {}", elapsedMillis);
            stopwatch.reset(); // Сбрасываем таймер для повторного использования
        } else {
            logger.warn("Stopwatch is not running. Cannot log execution time.");
        }
    }
}