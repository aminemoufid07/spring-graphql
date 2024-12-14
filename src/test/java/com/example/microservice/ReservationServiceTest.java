package com.example.microservice;



import com.example.microservice.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Test
    void testGetReservation() {
        String query = """
                query {
                    getReservation(id: "1") {
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
        String response = reservationService.executeQuery(query);
        System.out.println(response);
    }

    @Test
    void testListReservations() {
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
        String response = reservationService.executeQuery(query);
        System.out.println(response);
    }

    @Test
    void testCreateReservation() {
        String mutation = """
                mutation {
                    createReservation(
                        clientName: "Jane Smith",
                        clientEmail: "jane.smith@example.com",
                        clientPhone: "0987654321",
                        startDate: "2024-02-01",
                        endDate: "2024-02-05",
                        roomPreferences: "Mountain View"
                    ) {
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
        String response = reservationService.executeQuery(mutation);
        System.out.println(response);
    }

    @Test
    void testUpdateReservation() {
        String mutation = """
                mutation {
                    updateReservation(
                        id: "1",
                        clientName: "Updated Name",
                        clientPhone: "1112223333"
                    ) {
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
        String response = reservationService.executeQuery(mutation);
        System.out.println(response);
    }

    @Test
    void testDeleteReservation() {
        String mutation = """
                mutation {
                    deleteReservation(id: "1")
                }
                """;
        String response = reservationService.executeQuery(mutation);
        System.out.println(response);
    }
}
