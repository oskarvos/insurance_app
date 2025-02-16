package org.javaguru.travel.insurance.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeServiceTest {

    DateTimeService dateTimeService = new DateTimeService();
    LocalDate date1;
    LocalDate date2;
    long result;

    @BeforeEach
    void setUp() {
        date1 = LocalDate.of(2025, 1, 1);
        date2 = LocalDate.of(2025, 2, 5);
    }

    @Test
    void shouldCalculateDaysBetweenDatesPositive() {
        result = dateTimeService.calculateDaysBetweenDates(date1, date2);
        assertEquals(35, result);
    }

    @Test
    void shouldCalculateDaysBetweenDatesNegative() {
        result = dateTimeService.calculateDaysBetweenDates(date2, date1);
        assertEquals(-35, result);
    }

}