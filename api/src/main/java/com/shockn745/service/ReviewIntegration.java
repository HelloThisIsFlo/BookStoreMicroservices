package com.shockn745.service;

import com.shockn745.model.Review;
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
public class ReviewIntegration {

    private static final String REVIEW_SERVICE = "reviews";

    private LoadBalancerClient loadBalancerClient;
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public ReviewIntegration(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
    }

    public List<Review> getAllForBook(String bookId) {
        ServiceInstance instance = loadBalancerClient.choose(REVIEW_SERVICE);
        URI uri = instance.getUri();
        String url = uri.toString() + "/find?bookId=" + bookId;

        Review[] reviews = restTemplate.getForObject(url, Review[].class);


        return Arrays.asList(reviews);
    }
}
