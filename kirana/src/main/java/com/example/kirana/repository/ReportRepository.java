package com.example.kirana.repository;

import com.example.kirana.model.Report;
import lombok.Builder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ReportRepository extends MongoRepository<Report, String> {
    List<Report> findByUserIdAndStartDateGreaterThanEqualAndEndDateLessThanEqualAndType(
            String userId, LocalDateTime startDate, LocalDateTime endDate, Report.ReportType type);
}
