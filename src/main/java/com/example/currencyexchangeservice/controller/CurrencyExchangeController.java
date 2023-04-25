package com.example.currencyexchangeservice.controller;
import com.example.currencyexchangeservice.entity.ExchangeValue;
import com.example.currencyexchangeservice.repository.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue
            (@PathVariable String from, @PathVariable String to){

        ExchangeValue exchangeValue =
                repository.findByFromAndTo(from, to);

        exchangeValue.setEnvironment(
                environment.getProperty("local.server.port"));

        logger.info("{}", exchangeValue);

        return exchangeValue;
    }
}