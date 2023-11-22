package com.ed.fraud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class FraudCheckService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(String email) {
        fraudCheckHistoryRepository.saveAndFlush(
                FraudCheckHistory.builder()
                        .customerEmail(email)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        boolean isFraudulent = email.contains("fake");
        log.info("isFraudulent: {}", isFraudulent);

        return isFraudulent;
    }

}
