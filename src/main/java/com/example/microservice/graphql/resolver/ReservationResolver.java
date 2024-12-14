//package com.example.microservice.graphql.resolver;
//
//import com.example.microservice.entities.Reservation;
//import com.example.microservice.service.ReservationService;
//import graphql.kickstart.tools.GraphQLMutationResolver;
//import graphql.kickstart.tools.GraphQLQueryResolver;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeParseException;
//import java.util.List;
//
//@Component
//public class ReservationResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
//
//    private final ReservationService reservationService;
//
//    public ReservationResolver(ReservationService reservationService) {
//        this.reservationService = reservationService;
//    }
//
//    // Query resolvers
//    public Reservation getReservation(Long id) {
//        return reservationService.getReservation(id);
//    }
//
//    public List<Reservation> listReservations() {
//        return reservationService.listReservations();
//    }
//
//    // Mutation resolvers
//    public Reservation createReservation(String clientName, String clientEmail, String clientPhone,
//                                         String startDate, String endDate, String roomPreferences) {
//        validateDate(startDate);
//        validateDate(endDate);
//
//        Reservation reservation = new Reservation();
//        reservation.setClientName(clientName);
//        reservation.setClientEmail(clientEmail);
//        reservation.setClientPhone(clientPhone);
//        reservation.setStartDate(LocalDate.parse(startDate));
//        reservation.setEndDate(LocalDate.parse(endDate));
//        reservation.setRoomPreferences(roomPreferences);
//
//        return reservationService.createReservation(reservation);
//    }
//
//    public Reservation updateReservation(Long id, String clientName, String clientEmail, String clientPhone,
//                                         String startDate, String endDate, String roomPreferences) {
//        Reservation updatedReservation = new Reservation();
//        updatedReservation.setClientName(clientName);
//        updatedReservation.setClientEmail(clientEmail);
//        updatedReservation.setClientPhone(clientPhone);
//        if (startDate != null) {
//            validateDate(startDate);
//            updatedReservation.setStartDate(LocalDate.parse(startDate));
//        }
//        if (endDate != null) {
//            validateDate(endDate);
//            updatedReservation.setEndDate(LocalDate.parse(endDate));
//        }
//        updatedReservation.setRoomPreferences(roomPreferences);
//
//        return reservationService.updateReservation(id, updatedReservation);
//    }
//
//    public boolean deleteReservation(Long id) {
//        return reservationService.deleteReservation(id);
//    }
//
//    private void validateDate(String date) {
//        try {
//            LocalDate.parse(date);
//        } catch (DateTimeParseException e) {
//            throw new IllegalArgumentException("Invalid date format: " + date);
//        }
//    }
//}
