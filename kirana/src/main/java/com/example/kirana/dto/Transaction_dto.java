package com.example.kirana.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction_dto {
    private String userId;
    private String type; // "CREDIT" or "DEBIT"
    private String currency; // e.g., "INR", "USD"
    private Double amount; // Amount in Double
    private LocalDateTime transactionDate; // Date of the
}
