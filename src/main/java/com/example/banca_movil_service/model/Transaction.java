package com.example.banca_movil_service.model;

import jakarta.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long payment_id;
    private Long card_id;
    private double amount;
    private String type;
    private String status;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    public Transaction() {}

    public Transaction(Long id, Long payment_id, Long card_id, double amount, String type, String status, Payment payment) {
        this.id = id;
        this.payment_id = payment_id;
        this.card_id = card_id;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.payment = payment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Long payment_id) {
        this.payment_id = payment_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getCard_id() {
        return card_id;
    }

    public void setCard_id(Long card_id) {
        this.card_id = card_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
