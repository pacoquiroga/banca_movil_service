package com.example.banca_movil_service.controller;

import com.example.banca_movil_service.dto.card.TransactionDTO;
import com.example.banca_movil_service.model.Card;
import com.example.banca_movil_service.service.CardService;
import com.example.banca_movil_service.dto.card.CardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    // Obtener todas las tarjetas
    @GetMapping
    public ResponseEntity<List<CardDTO>> getAllCards() {
        List<Card> cards = cardService.findAll();

        List<CardDTO> cardDTOs = cards.stream()
                .map(card -> new CardDTO(card.getId(), card.getNumber(), card.getCreationYear(),
                        card.getExpiryYear(), card.getExpiryMonth(), card.getCvv(),
                        card.getCardType(), card.isFrozen(), card.getPin(), card.getBalance()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(cardDTOs);
    }

    // Obtener una tarjeta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        Card card = cardService.findById(id);
        return (card != null) ? ResponseEntity.ok(card) : ResponseEntity.notFound().build();
    }

    // Crear una nueva tarjeta
    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        return ResponseEntity.ok(cardService.create(card));
    }

    // Actualizar una tarjeta por ID
    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @RequestBody Card cardDetails) {
        Card updatedCard = cardService.update(id, cardDetails);
        return (updatedCard != null) ? ResponseEntity.ok(updatedCard) : ResponseEntity.notFound().build();
    }

    // Eliminar una tarjeta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        return cardService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Obtener las tarjetas de un usuario por su ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CardDTO>> getCardsByUserId(@PathVariable Long userId) {
        List<Card> cards = cardService.getCardsByUserId(userId);
        if (cards.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Convertir las tarjetas a CardDTO
        List<CardDTO> cardDTOs = cards.stream()
                .map(card -> new CardDTO(card.getId(), card.getNumber(), card.getCreationYear(),
                        card.getExpiryYear(), card.getExpiryMonth(), card.getCvv(),
                        card.getCardType(), card.isFrozen(), card.getPin(), card.getBalance()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(cardDTOs);
    }

    @PutMapping("/transaction/{id}")
    public ResponseEntity<CardDTO> doTransaction(@PathVariable Long id, @RequestBody TransactionDTO transaction) {
        Card card = cardService.findById(id);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }
        card.setBalance(card.getBalance() + transaction.getAmount());
        Card updatedCard = cardService.update(id, card);
        CardDTO responseCard = new CardDTO(updatedCard.getId(), updatedCard.getNumber(), updatedCard.getCreationYear(),
                updatedCard.getExpiryYear(), updatedCard.getExpiryMonth(), updatedCard.getCvv(),
                updatedCard.getCardType(), updatedCard.isFrozen(), updatedCard.getPin(), updatedCard.getBalance());
        return ResponseEntity.ok(responseCard);
    }

    @PutMapping("/freeze/{id}")
    public ResponseEntity<CardDTO> freezeCard(@PathVariable Long id){
        Card card = cardService.findById(id);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }
        card.setFrozen(true);
        Card updatedCard = cardService.update(id, card);
        CardDTO responseCard = new CardDTO(updatedCard.getId(), updatedCard.getNumber(), updatedCard.getCreationYear(),
                updatedCard.getExpiryYear(), updatedCard.getExpiryMonth(), updatedCard.getCvv(),
                updatedCard.getCardType(), updatedCard.isFrozen(), updatedCard.getPin(), updatedCard.getBalance());
        return ResponseEntity.ok(responseCard);
    }

    @PutMapping("/unfreeze/{id}")
    public ResponseEntity<CardDTO> unfreezeCard(@PathVariable Long id){
        Card card = cardService.findById(id);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }
        card.setFrozen(false);
        Card updatedCard = cardService.update(id, card);
        CardDTO responseCard = new CardDTO(updatedCard.getId(), updatedCard.getNumber(), updatedCard.getCreationYear(),
                updatedCard.getExpiryYear(), updatedCard.getExpiryMonth(), updatedCard.getCvv(),
                updatedCard.getCardType(), updatedCard.isFrozen(), updatedCard.getPin(), updatedCard.getBalance());
        return ResponseEntity.ok(responseCard);
    }
}
