package com.example.kirana.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.kirana.model.Transaction;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TransactionRepository extends MongoRepository<Transaction,String> {

     List<Transaction>findByUserId(String userId);
    @Query("{ 'id': ?0 }")
    List<Transaction>findByIdAndDateRange(String id, LocalDateTime start, LocalDateTime end);

    List<Transaction>findByTransactionDateBetweenAndType(LocalDateTime startDate, LocalDateTime endDate, String type);




}
