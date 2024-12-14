package com.example.microservice.service;

import com.example.microservice.entities.Reservation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ReservationService {

    private final List<Reservation> reservations = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();

    public Reservation getReservation(Long id) {
        return reservations.stream()
                .filter(reservation -> reservation.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Reservation with ID " + id + " not found"));
    }

    public List<Reservation> listReservations() {
        lock.lock();
        try {
            return new ArrayList<>(reservations);
        } finally {
            lock.unlock();
        }
    }

    public Reservation createReservation(Reservation reservation) {
        lock.lock();
        try {
            reservation.setId((long) (reservations.size() + 1));
            reservations.add(reservation);
            return reservation;
        } finally {
            lock.unlock();
        }
    }

    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        lock.lock();
        try {
            Optional<Reservation> existingReservation = reservations.stream()
                    .filter(reservation -> reservation.getId().equals(id))
                    .findFirst();

            if (existingReservation.isPresent()) {
                Reservation reservation = existingReservation.get();
                if (updatedReservation.getClientName() != null) {
                    reservation.setClientName(updatedReservation.getClientName());
                }
                if (updatedReservation.getClientEmail() != null) {
                    reservation.setClientEmail(updatedReservation.getClientEmail());
                }
                if (updatedReservation.getClientPhone() != null) {
                    reservation.setClientPhone(updatedReservation.getClientPhone());
                }
                if (updatedReservation.getStartDate() != null) {
                    reservation.setStartDate(updatedReservation.getStartDate());
                }
                if (updatedReservation.getEndDate() != null) {
                    reservation.setEndDate(updatedReservation.getEndDate());
                }
                if (updatedReservation.getRoomPreferences() != null) {
                    reservation.setRoomPreferences(updatedReservation.getRoomPreferences());
                }
                return reservation;
            } else {
                throw new IllegalArgumentException("Reservation with ID " + id + " not found");
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean deleteReservation(Long id) {
        lock.lock();
        try {
            return reservations.removeIf(reservation -> reservation.getId().equals(id));
        } finally {
            lock.unlock();
        }
    }
}
