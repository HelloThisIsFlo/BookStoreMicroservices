package com.shockn745;

import com.shockn745.domain.model.review.ReviewRepository;
import com.shockn745.data.TempFakeRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReviewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewsApplication.class, args);
	}

	//temp todo remove
    @Bean
    public ReviewRepository getTempRepo() {
	    return new TempFakeRepo();
    }
}
