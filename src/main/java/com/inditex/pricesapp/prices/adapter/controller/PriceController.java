package com.inditex.pricesapp.prices.adapter.controller;

import com.inditex.pricesapp.prices.application.exception.InvalidRequestException;
import com.inditex.pricesapp.prices.application.exception.PriceNotFoundException;
import com.inditex.pricesapp.prices.application.port.PriceService;
import com.inditex.pricesapp.prices.domain.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
public class PriceController {
    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<Price> getPriceByParameters(
           @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {

        if (date == null || productId == null || brandId == null) {
            throw new InvalidRequestException("Invalid request parameters");
        }

        Price price = priceService.getPriceByParameters(date, productId, brandId);

        if (price != null) {
            return new ResponseEntity<>(price, HttpStatus.OK);
        }

        throw new PriceNotFoundException("Price not found for the given parameters");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Price> getPriceById(@PathVariable Long id) {
        Price price = priceService.getPriceById(id);
        if (price != null) {
            return ResponseEntity.ok(price);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
