package com.example.banca_movil_service.repository;

import com.example.banca_movil_service.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByUserId(Long userId);
}
