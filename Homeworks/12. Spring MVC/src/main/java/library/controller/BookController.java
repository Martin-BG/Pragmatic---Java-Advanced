package library.controller;

import library.config.Messages;
import library.exceptions.InvalidDtoException;
import library.exceptions.JsonParsingException;
import library.model.dto.binding.BookCreationDto;
import library.model.dto.view.BookViewDto;
import library.parser.api.DtoValidator;
import library.parser.api.JsonParser;
import library.persistance.service.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class BookController {
    private final Messages messages;
    private final DtoValidator validator;
    private final JsonParser jsonParser;
    private final SimpleLoggedUserRegister loggedRegister;
    private final BookService bookService;

    @Autowired
    public BookController(final Messages messages,
                          final DtoValidator validator,
                          final JsonParser jsonParser,
                          final SimpleLoggedUserRegister loggedRegister,
                          final BookService bookService) {
        this.messages = messages;
        this.validator = validator;
        this.jsonParser = jsonParser;
        this.loggedRegister = loggedRegister;
        this.bookService = bookService;
    }

    @RequestMapping(value = {"/book-create"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addBook(final ModelAndView view,
                                @RequestParam(name = "name", defaultValue = "") final String name,
                                @RequestParam(name = "author", defaultValue = "") final String author,
                                @RequestParam(name = "year", defaultValue = "0") final Integer year,
                                @RequestParam(name = "price", defaultValue = "0") final BigDecimal price) {
        if (this.loggedRegister.noLoggedUser()) {
            view.addObject("message", this.messages.get("login.required"));
            view.setViewName("redirect:/user-login");
        } else {
            if (!name.isEmpty() || !author.isEmpty() || (year != 0) || !BigDecimal.ZERO.equals(price)) {
                try {
                    BookCreationDto dto = new BookCreationDto(name, author, year, price);

                    if (this.validator.isValid(dto) && this.bookService.addBook(dto)) {
                        view.addObject("message", this.messages.get("book.created.message"));
                    }
                } catch (InvalidDtoException e) {
                    view.addObject("message", e.getMessage());
                }
            }

            view.setViewName("/book-create");
        }
        return view;
    }

    @RequestMapping(value = {"/books"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView viewAllBooks(final ModelAndView view,
                                     @RequestParam(name = "bookId", required = false) final Long bookId) {
        if (this.loggedRegister.noLoggedUser()) {
            view.addObject("message", this.messages.get("login.required"));
            view.setViewName("redirect:/user-login");
        } else {
            try {
                if (bookId != null) {
                    this.bookService.toggleUser(bookId, this.loggedRegister.getLoggedUserName());
                }

                final List<BookViewDto> books = this.bookService.getAllBooks();
                view.addObject("message", this.jsonParser.write(books));
            } catch (InvalidDtoException | JsonParsingException e) {
                view.addObject("message", e.getMessage());
            }

            view.addObject("name", this.loggedRegister.getLoggedUserName());
            view.setViewName("/books");
        }

        return view;
    }
}
