package bip.online.biplio2023.controller;

import bip.online.biplio2023.entity.Publisher;
import bip.online.biplio2023.response.BaseResponse;
import bip.online.biplio2023.response.DataResponse;
import bip.online.biplio2023.response.ListResponse;
import bip.online.biplio2023.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Издатели", description = "Взаимодействие с издателями")
@RestController
@RequestMapping("api/v1/publisher")
public class PublisherController {

    private final PublisherService publisherService;


    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @Operation(
            summary = "Получение всех издателей",
            description = "Позволяет получить список всех издателей"
    )
    @GetMapping("/all")
    public ResponseEntity<ListResponse<Publisher>> getAllPublishers() {
        return ResponseEntity.ok(
                new ListResponse<Publisher>(true, "Список издательств", publisherService.findAllPublishers()));
    }

    @Operation(
            summary = "Получение издателя",
            description = "Позволяет получить издателя по идентификатору"
    )
    @GetMapping
    public ResponseEntity<DataResponse<Publisher>> by_id(@RequestParam Long id) {
        return ResponseEntity.ok(
                new DataResponse<Publisher>(true, "Найден следующий издатель", publisherService.findById(id).orElseThrow()));
    }

    @Operation(
            summary = "Сохранить издателя",
            description = "Позволяет сохранить издателя в базу данных"
    )
    @PostMapping
    public ResponseEntity<DataResponse<Publisher>> save(@RequestBody Publisher publisher) {
        return ResponseEntity.ok(
                new DataResponse<Publisher>(true, "Издатель сохранен", publisherService.save(publisher)));
    }

    @Operation(
            summary = "Обновить издателя",
            description = "Позволяет изменить издателя в базе данных"
    )
    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Publisher publisher) {
        publisherService.update(publisher);
        return ResponseEntity.ok(
                new BaseResponse(true, "Издатель обновлен"));
    }

    @Operation(
            summary = "Удалить издателя",
            description = "Позволяет удалить издателя из базы данных"
    )
    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam Long id) {
        publisherService.deleteById(id);
        return ResponseEntity.ok(
                new BaseResponse(true, "Издатель удален"));
    }
}
