package com.example.kirana.controller;

import com.example.kirana.dto.Transaction_dto;
import com.example.kirana.model.Transaction;
import com.example.kirana.service.CurrencyConversionService;
import com.example.kirana.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CurrencyConversionService currencyConversionService;


    @PostMapping()
    public ResponseEntity<Transaction> recordTransaction(@RequestBody Transaction_dto transaction_dto){

        if(!transaction_dto.getCurrency().equals("INR")){
            Double newAmount=currencyConversionService.convertCurrency(transaction_dto.getAmount(),transaction_dto.getCurrency());
            transaction_dto.setAmount(newAmount);
            transaction_dto.setCurrency("INR");

        }
        Transaction transaction=transactionService.recordTransaction(transaction_dto);
        System.out.println(transaction);
        return ResponseEntity.ok(transaction);
    }


    @GetMapping("/user/{userId}")
    public List<Transaction> getTransaction(@PathVariable  String userId){
        return transactionService.getTransactionsByUserId(userId);

    }
    @PutMapping("/user/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable String id, @RequestBody Transaction_dto transaction_dto) {
        Transaction updatedTransaction = transactionService.updateTransaction(id, transaction_dto);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
    }


//    this will delete transaction based on the id provide by mongodb not userId
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable String id) throws ChangeSetPersister.NotFoundException {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
