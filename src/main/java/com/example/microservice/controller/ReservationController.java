package com.example.microservice.controller;

import com.example.microservice.entities.Reservation;
import com.example.microservice.repository.ReservationRepository;
import com.example.microservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.example.microservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/graphql")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/{id}")
    public String getReservation(@PathVariable String id) {
        String query = """
                query {
                    getReservation(id: "%s") {
                        id
                        clientName
                        clientEmail
                        clientPhone
                        startDate
                        endDate
                        roomPreferences
                    }
                }
                """.formatted(id);
        return executeGraphQL(query);
    }

    @GetMapping
    public String listReservations() {
        String query = """
                query {
                    listReservations {
                        id
                        clientName
                        clientEmail
                        clientPhone
                        startDate
                        endDate
                        roomPreferences
                    }
                }
                """;
        return executeGraphQL(query);
    }

    @PostMapping
    public String createReservation(@RequestBody Map<String, Object> input) {
        // Extract the fields from the request
        String clientName = (String) input.get("clientName");
        String clientEmail = (String) input.get("clientEmail");
        String clientPhone = (String) input.get("clientPhone");
        String startDate = (String) input.get("startDate");
        String endDate = (String) input.get("endDate");
        String roomPreferences = (String) input.get("roomPreferences");

        // Build the GraphQL mutation string
        String mutation = """
            mutation {
                createReservation(clientName: "%s", clientEmail: "%s", clientPhone: "%s", startDate: "%s", endDate: "%s", roomPreferences: "%s") {
                    id
                    clientName
                    clientEmail
                    clientPhone
                    startDate
                    endDate
                    roomPreferences
                }
            }
            """.formatted(clientName, clientEmail, clientPhone, startDate, endDate, roomPreferences);

        // Execute the query through the service
        return executeGraphQL(mutation);

    }

    @PutMapping("/{id}")
    public String updateReservation(@PathVariable String id, @RequestBody String input) {
        String parsedInput = parseInput(input);
        String mutation = """
                mutation {
                    updateReservation(id: "%s", %s) {
                        id
                        clientName
                        clientEmail
                        clientPhone
                        startDate
                        endDate
                        roomPreferences
                    }
                }
                """.formatted(id, parsedInput);
        return executeGraphQL(mutation);
    }

    @DeleteMapping("/{id}")
    public String deleteReservation(@PathVariable String id) {
        String mutation = """
                mutation {
                    deleteReservation(id: "%s")
                }
                """.formatted(id);
        return executeGraphQL(mutation);
    }

    private String executeGraphQL(String queryOrMutation) {
        try {
            return reservationService.executeQuery(queryOrMutation);
        } catch (Exception e) {
            // Log and handle the exception
            return "Error: " + e.getMessage();
        }
    }

    private String parseInput(String input) {
        // Convert JSON request body into a GraphQL-friendly format
        // Example: {"clientName": "John Doe", ...} -> clientName: "John Doe", ...
        return input.replace("\"", "\\\"") // Escape quotes
                .replace("{", "")
                .replace("}", "")
                .trim();
    }
}
