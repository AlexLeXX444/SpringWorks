package ru.app.application.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    public void notify(String operation, String message) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("[" + operation.toUpperCase() + "] " + message + " at " + now);
    }
}
