package org.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeServiceTest {

    DateTimeService dateTimeService = new DateTimeService();

    @Test
    void shouldCalculateDaysBetweenDatesPositive() {
        var date1 = LocalDate.of(2025, 1, 1);
        var date2 = LocalDate.of(2025, 2, 5);
        var result = dateTimeService.calculateDaysBetweenDates(date1, date2);
        assertEquals(35, result);
    }

    @Test
    void shouldCalculateDaysBetweenDatesNegative() {
        var date1 = LocalDate.of(2025, 1, 1);
        var date2 = LocalDate.of(2025, 2, 5);
        var result = dateTimeService.calculateDaysBetweenDates(date2, date1);
        assertEquals(-35, result);
    }

    @Test
    void shouldCalculateDaysBetweenDatesZero() {
        var date1 = LocalDate.of(2025, 1, 1);
        var date2 = LocalDate.of(2025, 1, 1);
        var result = dateTimeService.calculateDaysBetweenDates(date2, date1);
        assertEquals(0, result);
    }

}