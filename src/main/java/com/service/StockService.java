package com.service;

import com.model.Stock;
import com.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private static final Logger LOG = LoggerFactory.getLogger(StockService.class);

    @Autowired
    private StockRepository repository;

    @PostConstruct
    public void initializeWithDummyData() {
        LOG.debug("Initializing data at startup........");
        repository.save(new Stock("Google", new BigDecimal(100.00), new Date()));
        repository.save(new Stock("Microsoft", new BigDecimal(102.00), new Date()));
        repository.save(new Stock("Apple", new BigDecimal(105.00), new Date()));
        repository.save(new Stock("SpaceX", new BigDecimal(95.00), new Date()));
    }

    /**
     * Fetches the list of all the stocks available in the repository
     * @return
     */
    public List<Stock> getStockList() {
        LOG.debug("Returning all the stocks");
        return repository.findAll();
    }

    /**
     * Finds the stock by stockID. If no results are fetched IllegalArgumentException is thrown
     * @param stockId
     * @return
     */
    public Stock getStockById(final Long stockId) {
        final Optional<Stock> stockOptional = repository.findById(stockId);
        if (stockOptional.isPresent()) {
            LOG.debug("Stock found for stock id {}", stockId);
            return stockOptional.get();
        }
        LOG.warn("No stock found for stockId {}", stockId);
        throw new IllegalArgumentException("No stock found for stock id" + stockId);
    }

    /**
     * Saves the stock sent in the request payload. In case of any corrupt input IllegalArgumentException is thrown
     *
     * @param stock
     * @return
     */
    public Stock saveStock(final Stock stock) {
        if (null == stock.getName() || null == stock.getPrice() || null == stock.getUpdatedTime()) {
            LOG.error("Invalid input received stockName {} stockPrice {} updatedTime {}", stock.getName(),
                    stock.getPrice(), stock.getUpdatedTime());
            throw new IllegalArgumentException("Input data is invalid");
        }
        LOG.debug("Stock {} saved", stock.getName());
        return repository.save(stock);
    }

    /**
     * Finds a stock with stockId and updates its attributes with the input payload. In case the stock is not found
     * it throws an IllegalArgumentException
     *
     * @param stockId
     * @param inputStock payload data
     * @return
     */
    public Stock updateStock(final Long stockId, final Stock inputStock) {
        final Optional<Stock> stockOptional = repository.findById(stockId);
        if (stockOptional.isPresent()) {
            final Stock stock = stockOptional.get();
            stock.setName(inputStock.getName());
            stock.setPrice(inputStock.getPrice());
            stock.setUpdatedTime(inputStock.getUpdatedTime());
            LOG.debug("Stock updated for id {} ", stockId);
            return repository.save(stock);
        }
        LOG.error("No stock found for the stock id {}", stockId);
        throw new IllegalArgumentException("Input data is invalid");
    }

}
