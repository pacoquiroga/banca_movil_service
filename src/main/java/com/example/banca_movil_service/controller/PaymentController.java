package com.example.banca_movil_service.controller;

import com.example.banca_movil_service.dto.card.CardDTO;
import com.example.banca_movil_service.model.Card;
import com.example.banca_movil_service.model.Payment;
import com.example.banca_movil_service.model.Transaction;
import com.example.banca_movil_service.service.CardService;
import com.example.banca_movil_service.service.PaymentService;
import com.example.banca_movil_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CardService cardService;

    // Obtener todos los pagos
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.findAll();
    }

    // Obtener un pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Optional<Payment> payment = paymentService.findById(id);
        return payment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo pago
    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        Payment paymentdb = paymentService.save(payment);

        Long emit_id = payment.getEmit_card_id();
        Long receptor_id = payment.getReceptor_card_id();
        double amount = payment.getAmount();

        Transaction debit = new Transaction(emit_id, amount, "DEBITO", payment.getStatus(), payment );
        transactionService.createTransaction(debit);

        Transaction credit = new Transaction(receptor_id, amount, "CREDITO", payment.getStatus(), payment );
        transactionService.createTransaction(credit);

        Card emit_card = cardService.findById(emit_id);
        if (emit_card == null) {
            return null;
        }
        emit_card.setBalance(emit_card.getBalance() - amount);
        cardService.update(emit_id, emit_card);

        Card receptor_card = cardService.findById(receptor_id);
        if (receptor_card == null) {
            return null;
        }
        receptor_card.setBalance(receptor_card.getBalance() + amount);
        cardService.update(receptor_id, receptor_card);


        return paymentdb;
    }

    // Actualizar un pago existente
    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment paymentDetails) {
        try {
            Payment updatedPayment = paymentService.update(id, paymentDetails);
            return ResponseEntity.ok(updatedPayment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un pago por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
