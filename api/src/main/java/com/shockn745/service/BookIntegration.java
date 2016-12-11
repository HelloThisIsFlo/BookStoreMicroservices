package com.shockn745.service;

import com.shockn745.model.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kempenich Florian
 */
@Component
public class BookIntegration {

    private static final String BOOK_SERVICE = "books";

    private LoadBalancerClient loadBalancerClient;
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public BookIntegration(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
    }

    public List<BookDto> getAll() {
        String url = getBookServiceUrl("/all");

        BookDto[] books = restTemplate.getForObject(url, BookDto[].class);


        return Arrays.asList(books);
    }

    private String getBookServiceUrl(String pathAndParameters) {
        ServiceInstance instance = loadBalancerClient.choose(BOOK_SERVICE);
        URI uri = instance.getUri();
        return uri.toString() + pathAndParameters;
    }

    public BookDto getBook(String bookId) {
        String url = getBookServiceUrl("/bookDetails?bookId=" + bookId);
        return restTemplate.getForObject(url, BookDto.class);
    }

}
