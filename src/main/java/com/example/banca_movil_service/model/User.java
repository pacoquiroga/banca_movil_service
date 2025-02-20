package com.example.banca_movil_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Card> cards; // Un usuario puede tener varias tarjetas

    public User() {}

    public User(String username, String email, String password, List<Card> cards) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.cards = cards;
    }

    // Constructor con ID (para recuperar usuarios existentes)
    public User(Long id, String username, String email, String password, List<Card> cards) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.cards = cards;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
