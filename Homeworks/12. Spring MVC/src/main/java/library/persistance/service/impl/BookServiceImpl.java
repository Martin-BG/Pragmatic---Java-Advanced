package library.persistance.service.impl;

import library.config.Messages;
import library.exceptions.InvalidDtoException;
import library.model.dto.binding.BookCreationDto;
import library.model.dto.view.BookViewDto;
import library.model.entity.Book;
import library.model.entity.User;
import library.parser.api.ModelParser;
import library.persistance.repository.BookRepository;
import library.persistance.service.api.BookService;
import library.persistance.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final UserService userService;
    private final ModelParser mapper;
    private final Messages messages;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository,
                           final UserService userService,
                           final ModelParser mapper,
                           final Messages messages) {
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.mapper = mapper;
        this.messages = messages;
    }

    @Override
    public boolean addBook(final BookCreationDto dto) throws InvalidDtoException {
        Book book = this.mapper.convert(dto, Book.class);
        try {
            this.bookRepository.save(book);
        } catch (Throwable e) {
            throw new InvalidDtoException(this.messages.get("error.persist.book") + e.getMessage());
        }
        return true;
    }

    @Override
    public List<BookViewDto> getAllBooks() {
        return this.bookRepository
                .findAll()
                .stream()
                .map(book -> {
                    BookViewDto dto = this.mapper.convert(book, BookViewDto.class);
                    if (book.getUser() != null) {
                        dto.setUserName(book.getUser().getName());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void toggleUser(final Long bookId, final String userName) throws InvalidDtoException {
        try {
            Book book = this.bookRepository.getOne(bookId);
            User user = this.userService.findOneByName(userName);

            if (user != null) {
                if (user.equals(book.getUser())) {
                    book.setUser(null);
                } else {
                    book.setUser(user);
                }
            }
        } catch (Exception e) {
            throw new InvalidDtoException(this.messages.get("error.persist.book.user" + e.getMessage()));
        }
    }
}
