package bip.online.biplio2023.controller;

import bip.online.biplio2023.entity.Author;
import bip.online.biplio2023.response.BaseResponse;
import bip.online.biplio2023.response.DataResponse;
import bip.online.biplio2023.response.ListResponse;
import bip.online.biplio2023.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Авторы", description = "Взаимодействие с авторами")
@RestController
@RequestMapping("api/v1/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Operation(
            summary = "Получение всех авторов",
            description = "Позволяет получить список всех авторов"
    )
    @GetMapping("/all")
    public ResponseEntity<ListResponse<Author>> getAllAuthors() {
        return ResponseEntity.ok(
                new ListResponse<Author>(true, "Список авторов", authorService.findAllAuthors()));
    }

    @Operation(
            summary = "Получение автора",
            description = "Позволяет получить автора по идентификатору"
    )
    @GetMapping
    public ResponseEntity<DataResponse<Author>> by_id(@RequestParam Long id) {
        return ResponseEntity.ok(
                new DataResponse<Author>(true, "Найден следующий автор", authorService.findById(id).orElseThrow()));
    }

    @Operation(
            summary = "Сохранить автора",
            description = "Позволяет сохранить автора в базу данных"
    )
    @PostMapping
    public ResponseEntity<DataResponse<Author>> save(@RequestBody Author author) {
        return ResponseEntity.ok(
                new DataResponse<Author>(true, "Автор сохранен", authorService.save(author)));
    }

    @Operation(
            summary = "Обновить автора",
            description = "Позволяет изменить автора в базе данных"
    )
    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Author author) {
        authorService.update(author);
        return ResponseEntity.ok(
                new BaseResponse(true, "Автор обновлен"));
    }

    @Operation(
            summary = "Удалить автора",
            description = "Позволяет удалить автора из базы данных"
    )
    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam Long id) {
        authorService.deleteById(id);
        return ResponseEntity.ok(
                new BaseResponse(true, "Автор удален"));
    }
}
