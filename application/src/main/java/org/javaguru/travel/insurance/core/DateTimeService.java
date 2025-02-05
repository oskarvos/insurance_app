package org.javaguru.travel.insurance.core;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class DateTimeService {

    Long calculateDaysBetweenDates(LocalDate date1, LocalDate date2) {  // считает коллиство дней между датами
        return ChronoUnit.DAYS.between(date1, date2);
    }
}
