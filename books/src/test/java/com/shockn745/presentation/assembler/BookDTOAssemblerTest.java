package com.shockn745.presentation.assembler;

import com.shockn745.domain.model.book.Book;
import com.shockn745.domain.model.book.BookId;
import com.shockn745.domain.model.book.Characteristics;
import com.shockn745.domain.model.book.Price;
import com.shockn745.presentation.model.BookDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Kempenich Florian
 */
public class BookDTOAssemblerTest {

    private BookDTOAssembler assembler;

    @Before
    public void setUp() throws Exception {
        assembler = new BookDTOAssembler();

    }

    @Test
    public void ensureMappingIsCorrect() throws Exception {
        String title = "This is the title";
        String author = "This is the author";
        int numberOfPages = 234;
        double price = 567.4;
        String id = "this is the id";


        Book book = new Book(new Characteristics(title, author, numberOfPages), new Price(price));
        book.setId(new BookId(id));

        BookDTO dto = assembler.toDTO(book);

        assertEquals(id, dto.getBookId());
        assertEquals(title, dto.getTitle());
        assertEquals(author, dto.getAuthor());
        assertEquals(numberOfPages, dto.getNumPages());
        assertEquals(price, dto.getPrice(), 0);

    }
}