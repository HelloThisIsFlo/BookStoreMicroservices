package com.shockn745.presentation;

import com.shockn745.application.BookService;
import com.shockn745.domain.model.book.*;
import com.shockn745.presentation.assembler.BookDTOAssembler;
import com.shockn745.presentation.model.BookDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BookDTO saveNewBook(@RequestBody @Valid BookDTO bookDTO) {
        Characteristics characteristics = new Characteristics(
                bookDTO.getTitle(),
                bookDTO.getAuthor(),
                bookDTO.getNumPages()
        );
        Price price = new Price(bookDTO.getPrice());

        BookId id = bookService.createNewBookEntry(characteristics, price);

        bookDTO.setBookId(id.idString());
        return bookDTO;
    }


}
