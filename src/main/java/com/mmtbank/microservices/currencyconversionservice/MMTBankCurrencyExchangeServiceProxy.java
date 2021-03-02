package com.mmtbank.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="mmtbank-currency-exchange-service", url="localhost:9000")
//Enabling feign  
@FeignClient(name="mmtbank-currency-exchange-service")  
//enabling ribbon  
@RibbonClient(name="mmtbank-currency-exchange-service")  

public interface MMTBankCurrencyExchangeServiceProxy {
	@GetMapping("/mmtbank-currency-exchange/from/{from}/to/{to}")		//where {from} and {to} are path variable
	public MMTBankCurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to); //from map to USD and to map to INR
}
