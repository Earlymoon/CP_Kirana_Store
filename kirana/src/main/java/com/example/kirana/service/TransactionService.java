package com.example.kirana.service;

import com.example.kirana.dto.Transaction_dto;
import com.example.kirana.model.Transaction;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface TransactionService {
    // Method to record a transaction (credit or debit)
    Transaction recordTransaction(Transaction_dto transaction_dto);

    // Method to fetch all transactions for a particular user
    List<Transaction> getTransactionsByUserId(String userId);

    Transaction updateTransaction(String id, Transaction_dto transaction_dto);

    void deleteTransaction(String id) throws ChangeSetPersister.NotFoundException;
}
