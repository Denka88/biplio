package bip.online.biplio2023.controller;

import bip.online.biplio2023.entity.Book;
import bip.online.biplio2023.response.BaseResponse;
import bip.online.biplio2023.response.DataResponse;
import bip.online.biplio2023.response.ListResponse;
import bip.online.biplio2023.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Книги", description = "Взаимодействие с книгами")
@RestController
@RequestMapping("api/v1/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(
            summary = "Получение всех книг",
            description = "Позволяет получить список всех книг"
    )
    @GetMapping("/all")
    public ResponseEntity<ListResponse<Book>> getAllBooks() {
        return ResponseEntity.ok(
                new ListResponse<Book>(true, "Список книг" ,bookService.findAllBooks()));
    }

    @Operation(
            summary = "Получение книги",
            description = "Позволяет получить книгу по идентификатору"
    )
    @GetMapping
    public ResponseEntity<DataResponse<Book>> by_id(@RequestParam Long id) {
        return ResponseEntity.ok(
                new DataResponse<Book>(true, "Найдена следующая книга", bookService.findById(id).orElseThrow()));
    }

    @Operation(
            summary = "Сохранить книгу",
            description = "Позволяет сохранить книгу в базу данных"
    )
    @PostMapping
    public ResponseEntity<DataResponse<Book>> save(@RequestBody Book book) {
        return ResponseEntity.ok(
                new DataResponse<Book>(true, "Книга сохранена", bookService.save(book)));
    }

    @Operation(
            summary = "Обновить книгу",
            description = "Позволяет изменить книгу в базе данных"
    )
    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Book book) {
        bookService.update(book);
        return ResponseEntity.ok(
                new BaseResponse(true, "Книга обновлен"));
    }

    @Operation(
            summary = "Удалить книгу",
            description = "Позволяет удалить книгу из базы данных"
    )
    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam Long id) {
        bookService.deleteById(id);
        return ResponseEntity.ok(
                new BaseResponse(true, "Книга удалена"));
    }
}
