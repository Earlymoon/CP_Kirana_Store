package com.example.kirana.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reports")
public class Report {
    @Id
    private String id;
    private String userId;
    private ReportType type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Map<String, BigDecimal> totalCredits;
    private Map<String, BigDecimal> totalDebits;
    private Map<String, BigDecimal> netFlow;
    private LocalDateTime generatedAt;

    public enum ReportType {
        WEEKLY, MONTHLY, YEARLY
    }
}

