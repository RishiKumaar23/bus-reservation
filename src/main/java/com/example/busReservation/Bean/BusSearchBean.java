package com.example.busReservation.Bean;

import java.time.LocalTime;

public interface BusSearchBean {
    Integer getId();

    Integer getBusId();

    LocalTime getDepartureTime();

    LocalTime getArrivalTime();

    Double getTravelHours();

    Integer getRouteId();



 //   Set<LocalDate> getTravelDates();

}
