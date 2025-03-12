package bip.online.biplio2023.controller;

import bip.online.biplio2023.entity.Book;
import bip.online.biplio2023.response.BaseResponse;
import bip.online.biplio2023.response.DataResponse;
import bip.online.biplio2023.response.ListResponse;
import bip.online.biplio2023.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<ListResponse<Book>> getAllBooks() {
        return ResponseEntity.ok(
                new ListResponse<Book>(true, "Список книг" ,bookService.findAllBooks()));
    }

    @GetMapping
    public ResponseEntity<DataResponse<Book>> by_id(@RequestParam Long id) {
        return ResponseEntity.ok(
                new DataResponse<Book>(true, "Найдена следующая книга", bookService.findById(id).orElseThrow()));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Book>> save(@RequestBody Book book) {
        return ResponseEntity.ok(
                new DataResponse<Book>(true, "Книга сохранена", bookService.save(book)));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Book book) {
        bookService.update(book);
        return ResponseEntity.ok(
                new BaseResponse(true, "Книга обновлен"));
    }
}
