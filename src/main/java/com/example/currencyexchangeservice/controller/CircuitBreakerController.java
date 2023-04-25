package com.example.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger =
            LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
   // @Retry(name = "default")//(we use default retry configurations)on failure of the method retry calling it 3 times if itis still failing then return th error
    //@Retry(name = "sample-api",fallbackMethod = "hardcodedResponse")// we will use sample-api conf in the .properties file
//    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")// we replaced retry annotation with this because circuit breaker wil prevent executing the method after failing certain amount of times so t will just return the hardcoded response without executing the function
  //  @RateLimiter(name="default")
    //10s => 10000 calls to the sample api
   @Bulkhead(name="sample-api")

    public String sampleApi() {
        logger.info("Sample api call received");
	//	ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",
	//				String.class);
//		return forEntity.getBody();
        return "sample-api";
    }
    public String hardcodedResponse(Exception ex) {
        return "fallback-response";
    }
}
