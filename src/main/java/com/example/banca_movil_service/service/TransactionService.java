package com.example.banca_movil_service.service;

import com.example.banca_movil_service.model.Transaction;
import com.example.banca_movil_service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


    // Crear una nueva transacción
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Obtener todas las transacciones
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    // Obtener una transacción por su ID
    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    // Actualizar una transacción existente
    public Transaction update(Long id, Transaction transactionDetails) {
        Optional<Transaction> existingTransaction = transactionRepository.findById(id);
        if (existingTransaction.isPresent()) {
            Transaction transaction = existingTransaction.get();
            transaction.setAmount(transactionDetails.getAmount());
            transaction.setCard_id(transactionDetails.getCard_id());
            transaction.setType(transactionDetails.getType());
            transaction.setStatus(transactionDetails.getStatus());
            transaction.setPayment(transactionDetails.getPayment());
            return transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("Transaction not found with id: " + id);
        }
    }

    // Eliminar una transacción por su ID
    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }
}
