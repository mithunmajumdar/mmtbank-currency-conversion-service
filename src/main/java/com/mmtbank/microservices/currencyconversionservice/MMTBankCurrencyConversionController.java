package com.mmtbank.microservices.currencyconversionservice;  

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;  

@RestController  
public class MMTBankCurrencyConversionController   
{  
	//********Default call***********//
	/*
	@GetMapping("/mmtbank-currency-converter/from/{from}/to/{to}/quantity/{quantity}") //where {from} and {to} represents the column   
	//return a bean back  
	public MMTBankCurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity)  
	{  
		return new MMTBankCurrencyConversionBean(1L, from,to,BigDecimal.ONE, quantity,quantity,0 );
	}
	*/

	//********REST call***********//
	@GetMapping("/mmtbank-currency-converter/from/{from}/to/{to}/quantity/{quantity}") //where {from} and {to} represents the column   
	//return a bean back  
	public MMTBankCurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity)  
	{  
		//setting variables to currency exchange service  
		Map<String, String>uriVariables=new HashMap<>();  
		uriVariables.put("from", from);  
		uriVariables.put("to", to);  
		//calling the currency-exchange-service  
		ResponseEntity<MMTBankCurrencyConversionBean>responseEntity=new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", MMTBankCurrencyConversionBean.class, uriVariables);  
		MMTBankCurrencyConversionBean response=responseEntity.getBody();  
		//creating a new response bean and getting the response back and taking it into Bean  
		return new MMTBankCurrencyConversionBean(response.getId(), from,to,response.getConversionMultiple(), quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());  
	}
	
	//********Feign call***********//
	@Autowired  
	private MMTBankCurrencyExchangeServiceProxy proxy;  
	
	//mapping for mmtbank-currency-converter-feign service
	@GetMapping("/mmtbank-currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}") //where {from} and {to} represents the column 
	//returns a bean 
	public MMTBankCurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity)
	{
		MMTBankCurrencyConversionBean response=proxy.retrieveExchangeValue(from, to);
		//creating a new response bean
		//getting the response back and taking it into Bean
		return new MMTBankCurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()), response.getPort());
	}
}  