package com.shockn745.presentation;

import com.shockn745.application.BookService;
import com.shockn745.domain.model.book.Book;
import com.shockn745.domain.model.book.BookId;
import com.shockn745.domain.model.book.BookNotFound;
import com.shockn745.presentation.assembler.BookDTOAssembler;
import com.shockn745.presentation.model.BookDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kempenich Florian
 */
@RestController
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;
    private final BookDTOAssembler dtoAssembler;

    @Autowired
    public BookController(BookService bookService, BookDTOAssembler dtoAssembler) {
        this.bookService = bookService;
        this.dtoAssembler = dtoAssembler;
    }

    @RequestMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO test() {
        return new BookDTO(
                "book-id-1",
                "This is the title",
                "Mark author",
                357,
                127.4
        );
    }

    @RequestMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    // TODO: 12/10/2016 Implement with pagination
    public List<BookDTO> getAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();

        return allBooks.stream()
                .map(dtoAssembler::toDTO)
                .collect(Collectors.toList());
    }

    @RequestMapping("/bookDetails")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBookDetails(@RequestParam("bookId") String bookId) throws BookNotFound {
        BookId id = new BookId(bookId);
        Book book = bookService.getBookDetails(id);
        return dtoAssembler.toDTO(book);
    }


}
