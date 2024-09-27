package com.example.kirana.dto;

import com.example.kirana.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report_dto {
    private Double totalCredit;
    private Double totalDebit;
    private Double netFlow;
    private List<Transaction> creditTransactions;
    private List<Transaction> debitTransactions;


}
