package bip.online.biplio2023.controller;

import bip.online.biplio2023.entity.Genre;
import bip.online.biplio2023.response.BaseResponse;
import bip.online.biplio2023.response.DataResponse;
import bip.online.biplio2023.response.ListResponse;
import bip.online.biplio2023.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Жанры", description = "Взаимодействие с жанрами")
@RestController
@RequestMapping("/api/v1/genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @Operation(
            summary = "Получение всех жанров",
            description = "Позволяет получить список всех жанров"
    )
    @GetMapping("/all")
    public ResponseEntity<ListResponse<Genre>> getAllGenres() {
        return ResponseEntity.ok(
                new ListResponse<Genre>(true, "Список жанров", genreService.findAllGenres()));
    }

    @Operation(
            summary = "Получение жанра",
            description = "Позволяет получить жанр по идентификатору"
    )
    @GetMapping
    public ResponseEntity<DataResponse<Genre>> by_id(@RequestParam Long id) {
        return ResponseEntity.ok(
                new DataResponse<Genre>(true, "Найден следующий жанр", genreService.findById(id).orElseThrow()));
    }

    @Operation(
            summary = "Сохранить жанр",
            description = "Позволяет сохранить жанр в базу данных"
    )
    @PostMapping
    public ResponseEntity<DataResponse<Genre>> save(@RequestBody Genre genre) {
        return ResponseEntity.ok(
                new DataResponse<Genre>(true, "Жанр сохранен", genreService.save(genre)));
    }

    @Operation(
            summary = "Обновить жанр",
            description = "Позволяет изменить жанр в базе данных"
    )
    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Genre genre) {
        genreService.update(genre);
        return ResponseEntity.ok(
                new BaseResponse(true, "Жанр обновлен"));
    }

    @Operation(
            summary = "Удалить жанр",
            description = "Позволяет удалить жанр из базы данных"
    )
    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam Long id) {
        genreService.deleteById(id);
        return ResponseEntity.ok(
                new BaseResponse(true, "Жанр удален"));
    }
}
