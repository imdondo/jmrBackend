package com.jmrsoftware.csvtobeanapplication.repository;

import com.jmrsoftware.csvtobeanapplication.model.RealEstateTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<RealEstateTransaction, Long> {
}
