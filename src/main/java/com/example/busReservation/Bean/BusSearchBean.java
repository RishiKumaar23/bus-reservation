package com.example.busReservation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public interface BusSearchBean {
    Integer getId();

    Integer getBusId();

    LocalTime getDepartureTime();

    LocalTime getArrivalTime();

    Double getTravelHours();

    Integer getRouteId();

    Set<LocalDate> getTravelDates();

}
