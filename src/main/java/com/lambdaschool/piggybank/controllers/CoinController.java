package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repositories.CoinRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController {

    @Autowired
    private CoinRepo coinRepo;

    @GetMapping(value = "/total", produces = "application/json")
    public ResponseEntity<?> getTotal() {

        List<Coin> coinList = new ArrayList<>();
        coinRepo.findAll().iterator().forEachRemaining(coinList::add);

        double total = 0.0;
        for (Coin c : coinList) {
            total += c.getValue() * c.getQuantity();
            System.out.println(c.getQuantity() + " " + c.getName());
        }



        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}