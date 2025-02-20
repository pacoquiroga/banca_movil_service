package com.example.banca_movil_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long emit_card_id;
    private Long receptor_card_id;
    private double amount;
    private String status;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Transaction> transactions;

    public Payment(Long emit_card_id, Long receptor_card_id, double amount, String status, List<Transaction> transactions) {
        this.emit_card_id = emit_card_id;
        this.receptor_card_id = receptor_card_id;
        this.amount = amount;
        this.status = status;
        this.transactions = transactions;
    }

    public Payment() {
    }

    public Long getEmit_card_id() {
        return emit_card_id;
    }

    public void setEmit_card_id(Long emit_card_id) {
        this.emit_card_id = emit_card_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReceptor_card_id() {
        return receptor_card_id;
    }

    public void setReceptor_card_id(Long receptor_card_id) {
        this.receptor_card_id = receptor_card_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
