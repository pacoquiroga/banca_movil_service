package com.example.banca_movil_service.dto.card;

public class CardDTO {

    private Long id;
    private String number;
    private int creationYear;
    private int expiryYear;
    private int expiryMonth;
    private String cvv;
    private String cardType;
    private boolean isFrozen;
    private String pin;
    private double balance;

    public CardDTO() {}

    public CardDTO(Long id, String number, int creationYear, int expiryYear, int expiryMonth, String cvv,
                   String cardType, boolean isFrozen, String pin, double balance){
        this.id = id;
        this.number = number;
        this.creationYear = creationYear;
        this.expiryYear = expiryYear;
        this.expiryMonth = expiryMonth;
        this.cvv = cvv;
        this.cardType = cardType;
        this.isFrozen = isFrozen;
        this.pin = pin;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
