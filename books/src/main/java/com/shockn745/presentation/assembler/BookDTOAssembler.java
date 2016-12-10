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
        BookDTO dto = new BookDTO();
        dto.setBookId(book.id().idString());
        dto.setTitle(book.characteristics().title());
        dto.setAuthor(book.characteristics().author());
        dto.setNumPages(book.characteristics().numPages());
        dto.setPrice(book.price().amount());
        return dto;
    }
}
