package com.example.kirana.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "transactions") //This tells Spring Boot to create or use a MongoDB collection named transactions to store this data.
public class Transaction {
    @Id
    private String id;
    private String userId;
    private String type; // "CREDIT" or "DEBIT"
    private String currency; // e.g., "INR", "USD"
    private Double amount; // Amount in Double
    private LocalDateTime transactionDate; // Date of the transaction
}
