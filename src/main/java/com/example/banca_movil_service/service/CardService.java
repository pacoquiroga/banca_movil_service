package com.example.banca_movil_service.service;

import com.example.banca_movil_service.model.Card;
import com.example.banca_movil_service.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    public Card findById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    public Card create(Card card) {
        return cardRepository.save(card);
    }

    public Card update(Long id, Card cardDetails) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            card.setNumber(cardDetails.getNumber());
            card.setCreationYear(cardDetails.getCreationYear());
            card.setExpiryYear(cardDetails.getExpiryYear());
            card.setExpiryMonth(cardDetails.getExpiryMonth());
            card.setCvv(cardDetails.getCvv());
            card.setCardType(cardDetails.getCardType());
            card.setFrozen(cardDetails.isFrozen());
            card.setPin(cardDetails.getPin());
            card.setBalance(cardDetails.getBalance());
            card.setUser(cardDetails.getUser());
            return cardRepository.save(card);
        }
        return null; // O lanzar una excepción personalizada
    }

    public boolean delete(Long id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Card> getCardsByUserId(Long userId) {
        return cardRepository.findByUserId(userId);
    }
}
