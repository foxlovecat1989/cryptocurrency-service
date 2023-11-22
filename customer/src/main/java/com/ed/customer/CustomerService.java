package com.ed.customer;

import com.ed.clients.fraud.FraudCheckResponse;
import com.ed.clients.fraud.FraudClient;
import com.ed.clients.notification.NotificationClient;
import com.ed.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(request.email());
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        // todo: check if email valid
        // todo: check if email not taken

        Customer customer = Customer.builder()
                .username(request.username())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getUsername(),
                customer.getEmail(),
                String.format("Hi %s, welcome to CryptoCurrency Service...", customer.getUsername())
        );
        notificationClient.sendNotification(notificationRequest);
    }
}
