package com.example.kirana.service;

import com.example.kirana.dto.Report_dto;


public interface ReportService {

    public Report_dto generateWeeklyReportInfo();
    public Report_dto generateMonthlyReportInfo();
    public Report_dto generateYearlyReportInfo();


}
