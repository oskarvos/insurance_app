//package org.javaguru.travel.insurance.rest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import com.google.common.base.Stopwatch;
//
//@Component
//public class TravelCalculatePremiumRequestTimeLogger {
//
//    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestTimeLogger.class);
//
//    void logRequestTime(StopWatch stopWatch) {
//        if (stopWatch.isRunning()) {
//            stopWatch.stop();
//            long elapsedMillis = stopWatch.elapsed().toMillis();
//            logger.info("Request processing time (ms): {}", elapsedMillis);
//            stopWatch.reset(); // Сбрасываем таймер для повторного использования
//        } else {
//            logger.warn("Stopwatch is not running. Cannot log execution time.");
//        }
//    }
//
//}

// TODO проверить зависимости
