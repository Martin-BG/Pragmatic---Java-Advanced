package library.persistance.service.api;

import library.exceptions.InvalidDtoException;
import library.model.dto.binding.BookCreationDto;
import library.model.dto.view.BookViewDto;

import java.util.List;

public interface BookService {

    boolean addBook(final BookCreationDto dto) throws InvalidDtoException;

    List<BookViewDto> getAllBooks();

    void toggleUser(final Long bookId, final String userName) throws InvalidDtoException;
}
