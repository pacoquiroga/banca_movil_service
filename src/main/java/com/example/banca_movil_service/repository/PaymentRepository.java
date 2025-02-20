package com.example.banca_movil_service.repository;

import com.example.banca_movil_service.model.Card;
import com.example.banca_movil_service.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
