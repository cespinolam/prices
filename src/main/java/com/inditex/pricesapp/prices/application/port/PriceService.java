package com.inditex.pricesapp.prices.application.port;

import com.inditex.pricesapp.prices.application.exception.PriceNotFoundException;
import com.inditex.pricesapp.prices.domain.model.Price;
import com.inditex.pricesapp.prices.domain.port.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

       // LocalDateTime dateTime = LocalDateTime.of(date, time);
       // LocalDate localDate = LocalDate.parse(date);
       // LocalTime localTime = LocalTime.parse(time);

       // LocalDateTime startDateTime = LocalDateTime.of(date, LocalTime.MIN);
       // LocalDateTime endDateTime = LocalDateTime.of(date, LocalTime.MAX);
        List<Price> prices = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                brandId, productId, date, date);

       // logger.debug("Getting price by parameters. Start Date: {}, End Date: {}, Product ID: {}, Brand ID: {}", dateTime, dateTime, productId, brandId);

        if (!prices.isEmpty()) {
            return prices.stream()
                   // .filter(price -> price.getStartDate().toLocalTime().isBefore(date) || price.getStartDate().toLocalTime().equals(time))
                   // .filter(price -> price.getEndDate().toLocalTime().isAfter(time) || price.getEndDate().toLocalTime().equals(time))
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
