package com.example.banca_movil_service.repository;

import com.example.banca_movil_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
