package com.ed.notification;

import com.ed.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        log.info("Get NotificationRequest: {}", notificationRequest);
        notificationRepository.save(
                Notification.builder()
                        .toCustomerEmail(notificationRequest.toCustomerName())
                        .sender("cryptocurrency-service")
                        .message(notificationRequest.message())
                        .build()
        );
    }
}
