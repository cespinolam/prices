import com.inditex.pricesapp.prices.adapter.controller.PriceController;
import com.inditex.pricesapp.prices.application.exception.InvalidRequestException;
import com.inditex.pricesapp.prices.application.exception.PriceNotFoundException;
import com.inditex.pricesapp.prices.application.port.PriceService;
import com.inditex.pricesapp.prices.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPriceByParameters_Success() {
        // Arrange
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        Long productId = 123L;
        Long brandId = 456L;
        Price expectedPrice = new Price();

        when(priceService.getPriceByParameters(date, time, productId, brandId)).thenReturn(expectedPrice);

        // Act
        ResponseEntity<Price> response = priceController.getPriceByParameters(date, time, productId, brandId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPrice, response.getBody());
        verify(priceService, times(1)).getPriceByParameters(date, time, productId, brandId);
    }

    @Test
    void testGetPriceByParameters_InvalidRequestParameters() {
        // Arrange
        LocalDate date = null;
        LocalTime time = null;
        Long productId = 123L;
        Long brandId = 456L;

        // Act & Assert
        assertThrows(InvalidRequestException.class,
                () -> priceController.getPriceByParameters(date,time, productId, brandId));
        verify(priceService, never()).getPriceByParameters(any(), any(), any(), any());
    }

    @Test
    void testGetPriceByParameters_PriceNotFound() {
        // Arrange
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        Long productId = 123L;
        Long brandId = 456L;

        when(priceService.getPriceByParameters(date, time, productId, brandId)).thenReturn(null);

        // Act & Assert
        assertThrows(PriceNotFoundException.class,
                () -> priceController.getPriceByParameters(date,time, productId, brandId));
        verify(priceService, times(1)).getPriceByParameters(date, time, productId, brandId);
    }
}