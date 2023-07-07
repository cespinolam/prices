import com.inditex.pricesapp.prices.adapter.controller.PriceController;
import com.inditex.pricesapp.prices.application.exception.InvalidRequestException;
import com.inditex.pricesapp.prices.application.exception.PriceNotFoundException;
import com.inditex.pricesapp.prices.application.port.PriceService;
import com.inditex.pricesapp.prices.domain.model.Brand;
import com.inditex.pricesapp.prices.domain.model.Price;
import com.inditex.pricesapp.prices.domain.model.Product;
import com.inditex.pricesapp.prices.domain.port.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;


class PriceControllerTest {

    public static final String PRODUCT_NAME = "Product A";
    public static final String BRAND_NAME = "ZARA";
    @Mock
    private PriceService priceService;
    @Mock
    private PriceRepository priceRepository;
    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testScenario1() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 00);
        Long productId = 35455L;
        Long brandId = 1L;

        List<Price> prices = new ArrayList<>();
        prices.add(new Price(1L, new Brand(brandId, BRAND_NAME), LocalDateTime.of(2020, 6, 14, 10, 00),
                                    LocalDateTime.of(2020, 6, 15, 23, 59, 59),
                                                            1, new Product(productId, PRODUCT_NAME), 0, 35.50, "EUR"));

        prices.add(new Price(2L, new Brand(brandId, BRAND_NAME), LocalDateTime.of(2020, 6, 14, 10, 00),
                                    LocalDateTime.of(2020, 1, 31, 23, 59, 59),
                                                            2, new Product(productId, PRODUCT_NAME), 1, 35.50, "EUR"));

        when(priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, date, date))
                .thenReturn(prices);

        Price result = priceService.getPriceByParameters(date, productId, brandId);
        assertEquals(35.50, result.getPrice());
    }

    @Test
    public void testScenario2() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 00);
        Long productId = 35455L;
        Long brandId = 1L;

        List<Price> prices = new ArrayList<>();
        prices.add(new Price(1L, new Brand(brandId, BRAND_NAME), LocalDateTime.of(2020, 6, 14, 16, 00),
                LocalDateTime.of(2020, 6, 15, 23, 59, 59),
                1, new Product(productId, PRODUCT_NAME), 0, 25.45, "EUR"));

        prices.add(new Price(2L, new Brand(brandId, BRAND_NAME), LocalDateTime.of(2020, 6, 14, 16, 00),
                LocalDateTime.of(2020, 6, 25, 23, 59, 59),
                2, new Product(productId, PRODUCT_NAME), 1, 25.45, "EUR"));

        when(priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, date, date))
                .thenReturn(prices);

        Price result = priceService.getPriceByParameters(date, productId, brandId);
        assertEquals(25.45, result.getPrice());
    }

    @Test
    public void testScenario3() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 00);
        Long productId = 35455L;
        Long brandId = 1L;

        List<Price> prices = new ArrayList<>();
        prices.add(new Price(1L, new Brand(brandId, BRAND_NAME), LocalDateTime.of(2020, 6, 14, 21, 00),
                LocalDateTime.of(2020, 6, 15, 23, 59, 59),
                1, new Product(productId, PRODUCT_NAME), 0, 38.95, "EUR"));

        prices.add(new Price(2L, new Brand(brandId, BRAND_NAME), LocalDateTime.of(2020, 6, 14, 21, 00),
                LocalDateTime.of(2020, 6, 25, 23, 59, 59),
                2, new Product(productId, PRODUCT_NAME), 1, 38.95, "EUR"));

        when(priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, date, date))
                .thenReturn(prices);

        Price result = priceService.getPriceByParameters(date, productId, brandId);
        assertEquals(38.95, result.getPrice());
    }

    @Test
    public void testScenario4() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 00);
        Long productId = 35455L;
        Long brandId = 1L;

        List<Price> prices = new ArrayList<>();
        prices.add(new Price(1L, new Brand(brandId, BRAND_NAME), LocalDateTime.of(2020, 6, 15, 10, 00),
                LocalDateTime.of(2020, 6, 15, 23, 59, 59),
                1, new Product(productId, PRODUCT_NAME), 0, 38.95, "EUR"));

        prices.add(new Price(2L, new Brand(brandId, BRAND_NAME), LocalDateTime.of(2020, 6, 15, 10, 00),
                LocalDateTime.of(2020, 6, 25, 23, 59, 59),
                2, new Product(productId, PRODUCT_NAME), 1, 38.95, "EUR"));

        when(priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, date, date))
                .thenReturn(prices);

        Price result = priceService.getPriceByParameters(date, productId, brandId);
        assertEquals(38.95, result.getPrice());
    }

    @Test
    public void testScenario5() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 00);
        Long productId = 35455L;
        Long brandId = 1L;

        List<Price> prices = new ArrayList<>();
        prices.add(new Price(1L, new Brand(brandId, BRAND_NAME), LocalDateTime.of(2020, 6, 16, 21, 00),
                LocalDateTime.of(2020, 6, 16, 23, 59, 59),
                1, new Product(productId, PRODUCT_NAME), 0, 25.45, "EUR"));

        prices.add(new Price(2L, new Brand(brandId, BRAND_NAME), LocalDateTime.of(2020, 6, 16, 21, 00),
                LocalDateTime.of(2020, 6, 16, 23, 59, 59),
                2, new Product(productId, PRODUCT_NAME), 1, 25.45, "EUR"));

        when(priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, date, date))
                .thenReturn(prices);

        Price result = priceService.getPriceByParameters(date, productId, brandId);
        assertEquals(25.45, result.getPrice());
    }

    @Test
    void testGetPriceByParameters_Success() {
        // Arrange
        LocalDateTime date = LocalDateTime.now();
        Long productId = 35455L;
        Long brandId = 1L;
        Price expectedPrice = new Price();

        when(priceService.getPriceByParameters(date,productId, brandId)).thenReturn(expectedPrice);

        // Act
        ResponseEntity<Price> response = priceController.getPriceByParameters(date, productId, brandId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPrice, response.getBody());
        verify(priceService, times(1)).getPriceByParameters(date, productId, brandId);
    }

    @Test
    void testGetPriceByParameters_InvalidRequestParameters() {
        // Arrange
        LocalDateTime date = null;
        Long productId = 35455L;
        Long brandId = 1L;

        // Act & Assert
        assertThrows(InvalidRequestException.class,
                () -> priceController.getPriceByParameters(date, productId, brandId));
        verify(priceService, never()).getPriceByParameters(any(), any(), any());
    }

    @Test
    void testGetPriceByParameters_PriceNotFound() {
        // Arrange
        LocalDateTime date = LocalDateTime.now();
        Long productId = 35455L;
        Long brandId = 1L;

        when(priceService.getPriceByParameters(date, productId, brandId)).thenReturn(null);

        // Act & Assert
        assertThrows(PriceNotFoundException.class,
                () -> priceController.getPriceByParameters(date, productId, brandId));
        verify(priceService, times(1)).getPriceByParameters(date, productId, brandId);
    }
}