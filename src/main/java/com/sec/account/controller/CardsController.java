package com.sec.account.controller;

import com.sec.account.model.Cards;
import com.sec.account.repo.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {
    @Autowired
    private CardsRepository cardsRepository;
    @GetMapping("/myCards")
    public List<Cards> getcards(@RequestParam("id") int customerId){
        List<Cards> cards = cardsRepository.findByCustomerId(customerId);
        return cards;
    }
}
