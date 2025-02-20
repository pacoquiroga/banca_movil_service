package com.example.banca_movil_service.repository;

import com.example.banca_movil_service.model.Payment;
import com.example.banca_movil_service.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
