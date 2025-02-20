package com.example.banca_movil_service.service;

import com.example.banca_movil_service.model.Payment;
import com.example.banca_movil_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;


    // Obtener todos los pagos
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    // Obtener un pago por ID
    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    // Guardar un nuevo pago
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Actualizar un pago existente
    public Payment update(Long id, Payment paymentDetails) {
        return paymentRepository.findById(id).map(payment -> {
            payment.setEmit_card_id(paymentDetails.getEmit_card_id());
            payment.setReceptor_card_id(paymentDetails.getReceptor_card_id());
            payment.setAmount(paymentDetails.getAmount());
            payment.setStatus(paymentDetails.getStatus());
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    // Eliminar un pago por ID
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}
