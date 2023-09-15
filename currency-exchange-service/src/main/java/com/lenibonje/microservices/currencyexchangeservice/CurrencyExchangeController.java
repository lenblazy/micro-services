package com.lenibonje.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

        logger.info("Retrieving exchange value from {} to {}", from, to);

        ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);

        String port = environment.getProperty("server.port");

        //CHANGE KUBERNETES
//        String host = environment.getProperty(environment.getProperty("HOSTNAME"));
//        String version = "v11";



        exchangeValue.setPort(Integer.parseInt(port));
        return exchangeValue;
    }

}
