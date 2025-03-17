package bip.online.biplio2023.controller;

import bip.online.biplio2023.entity.Author;
import bip.online.biplio2023.response.BaseResponse;
import bip.online.biplio2023.response.DataResponse;
import bip.online.biplio2023.response.ListResponse;
import bip.online.biplio2023.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public ResponseEntity<ListResponse<Author>> getAllAuthors() {
        return ResponseEntity.ok(
                new ListResponse<Author>(true, "Список авторов", authorService.findAllAuthors()));
    }

    @GetMapping
    public ResponseEntity<DataResponse<Author>> by_id(@RequestParam Long id) {
        return ResponseEntity.ok(
                new DataResponse<Author>(true, "Найден следующий автор", authorService.findById(id).orElseThrow()));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Author>> save(@RequestBody Author author) {
        return ResponseEntity.ok(
                new DataResponse<Author>(true, "Автор сохранен", authorService.save(author)));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Author author) {
        authorService.update(author);
        return ResponseEntity.ok(
                new BaseResponse(true, "Автор обновлен"));
    }
    
    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam Long id) {
        authorService.deleteById(id);
        return ResponseEntity.ok(
                new BaseResponse(true, "Автор удален"));
    }
}
