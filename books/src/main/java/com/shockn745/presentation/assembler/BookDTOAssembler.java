package com.shockn745.presentation.assembler;

import com.shockn745.domain.model.book.Book;
import com.shockn745.presentation.model.BookDTO;
import org.springframework.stereotype.Component;

/**
 * @author Kempenich Florian
 */
@Component
public class BookDTOAssembler {

    public BookDTO toDTO(Book book) {
        return new BookDTO(
                book.id().idString(),
                book.characteristics().title(),
                book.characteristics().author(),
                book.characteristics().numPages(),
                book.price().amount()
        );
    }
}
