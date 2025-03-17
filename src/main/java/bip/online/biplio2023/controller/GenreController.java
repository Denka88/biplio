package bip.online.biplio2023.controller;

import bip.online.biplio2023.entity.Genre;
import bip.online.biplio2023.response.BaseResponse;
import bip.online.biplio2023.response.DataResponse;
import bip.online.biplio2023.response.ListResponse;
import bip.online.biplio2023.service.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/all")
    public ResponseEntity<ListResponse<Genre>> getAllGenres() {
        return ResponseEntity.ok(
                new ListResponse<Genre>(true, "Список жанров", genreService.findAllGenres()));
    }

    @GetMapping
    public ResponseEntity<DataResponse<Genre>> by_id(@RequestParam Long id) {
        return ResponseEntity.ok(
                new DataResponse<Genre>(true, "Найден следующий жанр", genreService.findById(id).orElseThrow()));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Genre>> save(@RequestBody Genre genre) {
        return ResponseEntity.ok(
                new DataResponse<Genre>(true, "Жанр сохранен", genreService.save(genre)));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Genre genre) {
        genreService.update(genre);
        return ResponseEntity.ok(
                new BaseResponse(true, "Жанр обновлен"));
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam Long id) {
        genreService.deleteById(id);
        return ResponseEntity.ok(
                new BaseResponse(true, "Жанр удален"));
    }
}
