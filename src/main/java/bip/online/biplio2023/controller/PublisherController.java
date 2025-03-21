//package bip.online.biplio2023.controller;
//
//import bip.online.biplio2023.entity.Publisher;
//import bip.online.biplio2023.response.BaseResponse;
//import bip.online.biplio2023.response.DataResponse;
//import bip.online.biplio2023.response.ListResponse;
//import bip.online.biplio2023.service.PublisherService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("api/v1/publisher")
//public class PublisherController {
//
//    private final PublisherService publisherService;
//
//
//    public PublisherController(PublisherService publisherService) {
//        this.publisherService = publisherService;
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<ListResponse<Publisher>> getAllPublishers() {
//        return ResponseEntity.ok(
//                new ListResponse<Publisher>(true, "Список издательств", publisherService.findAllPublishers()));
//    }
//
//    @GetMapping
//    public ResponseEntity<DataResponse<Publisher>> by_id(@RequestParam Long id) {
//        return ResponseEntity.ok(
//                new DataResponse<Publisher>(true, "Найден следующий издатель", publisherService.findById(id).orElseThrow()));
//    }
//
//    @PostMapping
//    public ResponseEntity<DataResponse<Publisher>> save(@RequestBody Publisher publisher) {
//        return ResponseEntity.ok(
//                new DataResponse<Publisher>(true, "Издатель сохранен", publisherService.save(publisher)));
//    }
//
//    @PutMapping
//    public ResponseEntity<BaseResponse> update(@RequestBody Publisher publisher) {
//        publisherService.update(publisher);
//        return ResponseEntity.ok(
//                new BaseResponse(true, "Издатель обновлен"));
//    }
//
//    @DeleteMapping
//    public ResponseEntity<BaseResponse> delete(@RequestParam Long id) {
//        publisherService.deleteById(id);
//        return ResponseEntity.ok(
//                new BaseResponse(true, "Издатель удален"));
//    }
//}
