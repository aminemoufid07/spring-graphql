package com.example.microservice.controller;

import com.example.microservice.entities.Reservation;
import com.example.microservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationRepository repository;

    @PostMapping
    public Reservation create(@RequestBody Reservation reservation) {
        return repository.save(reservation);
    }

    @GetMapping("/{id}")
    public Reservation getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    @PutMapping("/{id}")
    public Reservation update(@PathVariable Long id, @RequestBody Reservation reservationDetails) {
        Reservation reservation = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setClientName(reservationDetails.getClientName());
        reservation.setStartDate(reservationDetails.getStartDate());
        reservation.setEndDate(reservationDetails.getEndDate());
        reservation.setRoomPreferences(reservationDetails.getRoomPreferences());
        return repository.save(reservation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
