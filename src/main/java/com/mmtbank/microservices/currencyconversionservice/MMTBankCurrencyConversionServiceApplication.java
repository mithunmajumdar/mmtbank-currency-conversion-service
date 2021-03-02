package com.mmtbank.microservices.currencyconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.mmtbank.microservices.currencyconversionservice")
public class MMTBankCurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MMTBankCurrencyConversionServiceApplication.class, args);
	}

}
