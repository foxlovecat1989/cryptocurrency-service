package com.ed.clients.notification;

public record NotificationRequest(
        String toCustomerEmail,
        String toCustomerName,
        String message
) {
}
