package com.service;

import com.model.Stock;
import com.repository.StockRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {

    private StockService stockService;

    @Mock
    private StockRepository repository;

    @Before
    public void setup() {
        final Stock stock = createStock();
        stockService = new StockService(repository);
        when(repository.findAll()).thenReturn(Arrays.asList(stock));
        when(repository.findById(1L)).thenReturn(Optional.of(stock));
        when(repository.save(any(Stock.class))).thenReturn(stock);
    }

    @Test
    public void getRequest() {
        final List stockList = stockService.getStockList();
        assertTrue(!CollectionUtils.isEmpty(stockList));
    }

    @Test
    public void getStockById() {
        final Stock stock = stockService.getStockById(1L);
        assertNotNull(stock);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getStockByIdInvalidInput() {
        final Stock stock = stockService.getStockById(2L);
        assertNull(stock);
    }

    @Test
    public void saveStock() {
        final Stock inputStock = createStock();
        final Stock savedStock = stockService.saveStock(inputStock);
        assertNotNull(savedStock);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveStockInvalidInput() {
        final Stock inputStock = createStock();
        inputStock.setName(null);
        final Stock savedStock = stockService.saveStock(inputStock);
        assertNull(savedStock);
    }

    @Test
    public void updateStock() {
        final Stock inputStock = createStock();
        inputStock.setName("XYZ");
        final Stock savedStock = stockService.updateStock(1L, inputStock);
        assertEquals("XYZ", savedStock.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateStockInvalidInput() {
        final Stock inputStock = createStock();
        final Stock savedStock = stockService.updateStock(5L, inputStock);
        assertNull(savedStock);
    }

    private Stock createStock() {
        final Stock stock = new Stock();
        stock.setId(1L);
        stock.setName("ABC");
        stock.setPrice(new BigDecimal(100.0));
        stock.setUpdatedTime(new Date());
        return stock;
    }

}
