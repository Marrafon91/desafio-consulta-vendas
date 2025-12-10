package com.devsuperior.dsmeta.services.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public interface HandleDate {

    default LocalDate handleMaxDate(String maxDate) {
        if (maxDate == null || maxDate.isBlank()) {
            return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        }
        return LocalDate.parse(maxDate);
    }

    default LocalDate handleMinDate(String minDate, LocalDate maxDate) {
        if (minDate == null || minDate.isBlank()) {
            return maxDate.minusYears(1L);
        }
        return LocalDate.parse(minDate);
    }
}
