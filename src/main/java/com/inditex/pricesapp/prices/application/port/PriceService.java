package com.inditex.pricesapp.prices.application.port;

import com.inditex.pricesapp.prices.application.exception.PriceNotFoundException;
import com.inditex.pricesapp.prices.domain.model.Price;
import com.inditex.pricesapp.prices.domain.port.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PriceService {

    private static final Logger logger = LoggerFactory.getLogger(PriceService.class);
    private final PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getPriceByParameters(LocalDateTime date, Long productId, Long brandId) {
        if (date == null || productId == null || brandId == null) {
            throw new IllegalArgumentException("Invalid arguments for getPriceByParameters method");
        }

        List<Price> prices = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                brandId, productId, date, date);


        if (!prices.isEmpty()) {
            return prices.stream()
                    .max(Comparator.comparingInt(Price::getPriority))
                    .orElse(null);
        }

        throw new PriceNotFoundException("Price not found for the given parameters");
    }

    public Price getPriceById(Long id) {
        Optional<Price> priceOptional = priceRepository.findById(id);
        return priceOptional.orElse(null); // or throw an exception if necessary
    }
}
