package com.example.microservice.service;

import com.example.microservice.entities.Reservation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Service;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReservationService {

    private final RestTemplate restTemplate;

    public ReservationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String executeQuery(String query) {
        String url = "http://localhost:8080/graphql"; // Replace with your GraphQL API URL

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = "{ \"query\": \"" + query + "\" }";
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(url, request, String.class);
    }
}
