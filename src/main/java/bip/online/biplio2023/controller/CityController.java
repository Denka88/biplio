package bip.online.biplio2023.controller;


import bip.online.biplio2023.entity.City;
import bip.online.biplio2023.response.BaseResponse;
import bip.online.biplio2023.response.DataResponse;
import bip.online.biplio2023.response.ListResponse;
import bip.online.biplio2023.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/city")
public class CityController {

    private final CityService cityService;


    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/all")
    public ResponseEntity<ListResponse<City>> getAllCities() {
        return ResponseEntity.ok(
                new ListResponse<City>(true, "Список городов", cityService.findAllCities()));
    }

    @GetMapping
    public ResponseEntity<DataResponse<City>> by_id(@RequestParam Long id) {
        return ResponseEntity.ok(
                new DataResponse<City>(true, "Найден следующий город", cityService.findById(id).orElseThrow()));
    }

    @PostMapping
    public ResponseEntity<DataResponse<City>> save(@RequestBody City city) {
        return ResponseEntity.ok(
                new DataResponse<City>(true, "Город сохранен", cityService.save(city)));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody City city) {
        cityService.update(city);
        return ResponseEntity.ok(
                new BaseResponse(true, "Город обновлен"));
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam Long id) {
        cityService.deleteById(id);
        return ResponseEntity.ok(
                new BaseResponse(true, "Город удален"));
    }
}
