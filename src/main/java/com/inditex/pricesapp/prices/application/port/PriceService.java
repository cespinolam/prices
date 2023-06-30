package com.inditex.pricesapp.prices.application.port;

import com.inditex.pricesapp.prices.application.exception.PriceNotFoundException;
import com.inditex.pricesapp.prices.domain.model.Price;
import com.inditex.pricesapp.prices.domain.port.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class PriceService {
    private final PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getPriceByParameters(LocalDate date, LocalTime time, Long productId, Long brandId) {
        if (date == null || time == null || productId == null || brandId == null) {
            throw new IllegalArgumentException("Invalid arguments for getPriceByParameters method");
        }

        LocalDateTime dateTime = LocalDateTime.of(date, time);
        List<Price> prices = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                brandId, productId, dateTime, dateTime);


        if (!prices.isEmpty()) {
            return prices.get(0);
        }

        throw new PriceNotFoundException("Price not found for the given parameters");
    }
}
