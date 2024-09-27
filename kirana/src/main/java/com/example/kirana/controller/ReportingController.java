package com.example.kirana.controller;

import com.example.kirana.dto.Report_dto;
import com.example.kirana.model.Transaction;
import com.example.kirana.service.ReportService;
import com.example.kirana.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/reporting")
public class ReportingController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/weekly")
    public Report_dto generateWeeklyReport() {
        return reportService.generateWeeklyReportInfo();
    }
    @GetMapping("/monthly")
    public Report_dto generateMonthlyReport(){
        return reportService.generateMonthlyReportInfo();
    }
    @GetMapping("/yearly")
    public Report_dto generateYearlyReport(){
        return reportService.generateYearlyReportInfo();
    }



}
