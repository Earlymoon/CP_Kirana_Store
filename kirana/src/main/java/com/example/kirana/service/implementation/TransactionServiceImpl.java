package com.example.kirana.service.implementation;

import com.example.kirana.dto.Transaction_dto;
import com.example.kirana.model.Transaction;
import com.example.kirana.repository.TransactionRepository;
import com.example.kirana.service.CurrencyConversionService;
import com.example.kirana.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransactionServiceImpl extends RuntimeException implements TransactionService {



    private TransactionRepository transactionRepository;

    @Autowired
    private CurrencyConversionService currencyConversionService;

    TransactionServiceImpl(TransactionRepository transactionRepository){
        this.transactionRepository=transactionRepository;
    }

    @Override
    public Transaction recordTransaction(Transaction_dto transaction_dto) {
        Transaction transaction=new Transaction();
        transaction.setAmount(transaction_dto.getAmount());
        transaction.setTransactionDate(transaction_dto.getTransactionDate());
        transaction.setType(transaction.getType());
        transaction.setCurrency("INR");
        transaction.setType(transaction_dto.getType());
        transaction.setUserId(transaction_dto.getUserId());

      return transactionRepository.save(transaction);

    }

    @Override
    public List<Transaction> getTransactionsByUserId(String userId) {
        return transactionRepository.findByUserId(userId);  // Assuming you have this method in your repository
    }


    @Override
    public Transaction updateTransaction(String id, Transaction_dto transaction_dto) {
        Transaction existingTransaction=transactionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("not found transaction with id "+id));
        existingTransaction.setAmount(transaction_dto.getAmount());
        existingTransaction.setTransactionDate(transaction_dto.getTransactionDate());
        existingTransaction.setCurrency(transaction_dto.getCurrency());
        return transactionRepository.save(existingTransaction);

    }

    @Override
    public void deleteTransaction(String id) throws ChangeSetPersister.NotFoundException {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        transactionRepository.delete(transaction);
    }



}
