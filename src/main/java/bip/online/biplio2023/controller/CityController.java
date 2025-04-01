package bip.online.biplio2023.controller;


import bip.online.biplio2023.entity.City;
import bip.online.biplio2023.response.BaseResponse;
import bip.online.biplio2023.response.DataResponse;
import bip.online.biplio2023.response.ListResponse;
import bip.online.biplio2023.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Города", description = "Взаимодействие с городами")
@RestController
@RequestMapping("api/v1/city")
public class CityController {

    private final CityService cityService;
    
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @Operation(
            summary = "Получение всех городов",
            description = "Позволяет получить список всех городов"
    )
    @GetMapping("/all")
    public ResponseEntity<ListResponse<City>> getAllCities() {
        return ResponseEntity.ok(
                new ListResponse<City>(true, "Список городов", cityService.findAllCities()));
    }

    @Operation(
            summary = "Получение города",
            description = "Позволяет получить город по идентификатору"
    )
    @GetMapping
    public ResponseEntity<DataResponse<City>> by_id(@RequestParam Long id) {
        return ResponseEntity.ok(
                new DataResponse<City>(true, "Найден следующий город", cityService.findById(id).orElseThrow()));
    }

    @Operation(
            summary = "Сохранить город",
            description = "Позволяет сохранить город в базу данных"
    )
    @PostMapping
    public ResponseEntity<DataResponse<City>> save(@RequestBody City city) {
        return ResponseEntity.ok(
                new DataResponse<City>(true, "Город сохранен", cityService.save(city)));
    }

    @Operation(
            summary = "Обновить город",
            description = "Позволяет изменить город в базе данных"
    )
    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody City city) {
        cityService.update(city);
        return ResponseEntity.ok(
                new BaseResponse(true, "Город обновлен"));
    }

    @Operation(
            summary = "Удалить город",
            description = "Позволяет удалить город из базы данных"
    )
    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam Long id) {
        cityService.deleteById(id);
        return ResponseEntity.ok(
                new BaseResponse(true, "Город удален"));
    }
}
