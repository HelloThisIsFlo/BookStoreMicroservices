package com.shockn745;

import com.shockn745.domain.model.book.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}

	@Bean
    public CommandLineRunner fillWithFakeData(BookRepository repository) {
	    return args -> {
            List<Book> books = Arrays.asList(
                    makeBook("id-1", "title", "author", 321, 543.12),
                    makeBook("id-2", "Harry Potter", "author", 321, 124),
                    makeBook("id-3", "Ubiq", "Philip K. Dick", 450, 53.2),
                    makeBook("id-4", "Lost thing", "Famous writer", 1342, 789),
                    makeBook("WILL BE ERASED ANYWAY", "Lost thing", "Famous writer", 1342, 789),
                    makeBook("id-4", "This is the Hibernate implementation :D :D ", "Famous writer", 1342, 789),
                    makeBook("id-4", "Lost thing", "Famous writer", 1342, 789)
            );

            books.forEach(repository::save);
        };
    }

    private Book makeBook(String id, String title, String author, int numPage, double price) {
        Book book = new Book(
                new Characteristics(title, author, numPage),
                new Price(price)
        );
        book.setId(new BookId(id));
        return book;
    }
}
