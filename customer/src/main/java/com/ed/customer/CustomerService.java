package com.ed.customer;

import com.ed.clients.fraud.FraudCheckResponse;
import com.ed.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;

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
    }
}
