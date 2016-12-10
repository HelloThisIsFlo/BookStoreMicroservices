package com.shockn745;

import com.shockn745.data.TempFakeRepo;
import com.shockn745.domain.model.review.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ReviewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewsApplication.class, args);
	}

	//temp todo remove
    @Bean
    public ReviewRepository getTempRepo() {
	    return new TempFakeRepo();
    }

    @Bean
	public CommandLineRunner fillWithFakeReview(ReviewRepository reviewRepository) {
		return args -> {
            reviewRepository.save(makeReview("3", "Georges", 12));
            reviewRepository.save(makeReview("3", "Patrick", 23));
            reviewRepository.save(makeReview("1", "Georges", 77));
            reviewRepository.save(makeReview("1", "Mark", 89));
            reviewRepository.save(makeReview("1", "Francis", 99));
		};
	}

	private Review makeReview(String bookId, String username, int rating) {
        return new Review(new ReviewId("WILL BE OVERRIDEN"), new BookId(bookId), new User(username), new Rating(rating));
    }
}
