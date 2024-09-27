package com.example.kirana.service.implementation;

import com.example.kirana.dto.Report_dto;
import com.example.kirana.model.Transaction;
import com.example.kirana.repository.TransactionRepository;
import com.example.kirana.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{

    @Autowired
    private TransactionRepository transactionRepository;

    public Report_dto generateWeeklyReportInfo(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekAgo = now.minus(7, ChronoUnit.DAYS);

        return calculateAllTheReports(weekAgo,now);
    }

    @Override
    public Report_dto generateMonthlyReportInfo() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime monthAgo = now.minus(1, ChronoUnit.MONTHS);
        return calculateAllTheReports(monthAgo,now);
    }

    @Override
    public Report_dto generateYearlyReportInfo() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yearAgo = now.minus(1, ChronoUnit.YEARS);

        return calculateAllTheReports(yearAgo,now);
    }

    public Report_dto calculateAllTheReports(LocalDateTime startDate,LocalDateTime endDate){
        List<Transaction>creditTransactions=transactionRepository.
                findByTransactionDateBetweenAndType(startDate,endDate,"CREDIT");
        List<Transaction>debitTransactions=transactionRepository.
                findByTransactionDateBetweenAndType(startDate,endDate,"DEBIT");

        Double totalCredit = creditTransactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();

        Double totalDebit = debitTransactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
        Double netFlow=totalCredit-totalDebit;

        return new Report_dto(totalCredit,totalDebit,netFlow,creditTransactions,debitTransactions);
    }
}