package org.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
class DateTimeService {

    long calculateDaysBetweenDates(LocalDate date1, LocalDate date2) {  // считает количество дней между датами
        return ChronoUnit.DAYS.between(date1, date2);
    }

}