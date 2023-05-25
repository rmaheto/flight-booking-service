package com.codemaniac.bookingservice.service;

import com.codemaniac.bookingservice.exception.CustomerServiceException;
import org.openapitools.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl {

    private final WebClient webClient;

    @Value("${customerServiceServer.url}")
    private String customerServiceServerUrl;

    public CustomerServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Customer> getCustomerInfo(Long customerId) {
        String customerServiceUrl = customerServiceServerUrl+"/customers/{customerId}";

        return webClient.get()
                .uri(customerServiceUrl, customerId)
                .retrieve()
                .bodyToMono(Customer.class)
                .onErrorMap(throwable -> new CustomerServiceException("Failed to retrieve customer information", throwable));
    }
}
