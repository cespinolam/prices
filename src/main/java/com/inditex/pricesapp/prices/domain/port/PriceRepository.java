package com.inditex.pricesapp.prices.domain.port;

import com.inditex.pricesapp.prices.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
   List<Price> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long brandId, Long productId, LocalDateTime date, LocalDateTime date2);
}
