package com.controller;

import com.model.Stock;
import com.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "api/stocks", produces = MediaType.APPLICATION_JSON_VALUE)
public class StockController {

    private static final Logger LOG = LoggerFactory.getLogger(StockController.class);

    private static final String INVALID_INPUT = "INVALID_INPUT";

    @Autowired
    private StockService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Stock>> getStock() {
        LOG.info("Get all stocks");
        return new ResponseEntity(service.getStockList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{stockId}", method = RequestMethod.GET)
    public ResponseEntity getStockById(@PathVariable("stockId") final Long stockId) {
        LOG.info("Get stock By Id {} ", stockId);
        ResponseEntity result;
        try {
            result = new ResponseEntity(service.getStockById(stockId), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            result = new ResponseEntity(INVALID_INPUT, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Stock> createStock(@RequestBody final Stock stock) {
        ResponseEntity result;
        LOG.info("Save a new stock");
        try {
            result = new ResponseEntity(service.saveStock(stock), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            result = new ResponseEntity(INVALID_INPUT, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @RequestMapping(value = "/{stockId}", method = RequestMethod.PUT)
    public ResponseEntity updateStock(@PathVariable("stockId") final Long stockId, @RequestBody final Stock stock) {
        LOG.info("Update stock By Id {}", stockId);
        ResponseEntity result;
        try {
            result = new ResponseEntity(service.updateStock(stockId, stock), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            result = new ResponseEntity(INVALID_INPUT, HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
