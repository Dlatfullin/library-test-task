package com.ufuture.library.contollers;

import com.ufuture.library.dto.BookDTO;
import com.ufuture.library.models.Book;
import com.ufuture.library.services.BookService;
import com.ufuture.library.utill.BookNotCreatedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Book Controller", description = "Контроллер для управления книгами в библиотеке")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Получить все книги", description = "Возвращает список всех книг, хранящихся в библиотеке.")
    @ApiResponse(responseCode = "200", description = "Список книг успешно получен",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Book.class)))
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }

    @Operation(summary = "Получить книгу по ID", description = "Возвращает книгу по указанному ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Книга найдена",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "404", description = "Книга не найдена",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable @Parameter(description = "идентификатор пользователя") Long id) {
        return bookService.getBookById(id);
    }

    @Operation(summary = "Добавить новую книгу", description = "Добавляет новую книгу в библиотеку.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Книга успешно создана",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<HttpStatus> addBook(@RequestBody @Valid BookDTO bookDTO,
                                              BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder msg = new StringBuilder();
            bindingResult.getFieldErrors()
                    .forEach(x -> msg.append(x.getField())
                            .append(" - ")
                            .append(x.getDefaultMessage())
                            .append(";"));

            throw new BookNotCreatedException(msg.toString());
        }

        bookService.saveBook(convertBookDTOToBook(bookDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Обновить книгу", description = "Обновляет информацию о книге по указанному ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Книга успешно обновлена",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Книга не найдена",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateBook(@PathVariable("id") @Parameter(description = "идентификатор пользователя") Long id,
                                                 @RequestBody @Valid BookDTO bookDTO,
                                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder msg = new StringBuilder();
            bindingResult.getFieldErrors()
                    .forEach(x -> msg.append(x.getField())
                            .append(" - ")
                            .append(x.getDefaultMessage())
                            .append(";"));

            throw new BookNotCreatedException(msg.toString());
        }

        bookService.updateBook(id, convertBookDTOToBook(bookDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Удалить книгу", description = "Удаляет книгу по указанному ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Книга успешно удалена",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Книга не найдена",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") @Parameter(description = "идентификатор пользователя") Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Book convertBookDTOToBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setDescription(bookDTO.getDescription());
        book.setPrice(bookDTO.getPrice());
        return book;
    }
}
