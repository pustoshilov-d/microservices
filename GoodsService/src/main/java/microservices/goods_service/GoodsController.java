package microservices.goods_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class GoodsController {

    @Autowired
    private GoodsRepository goodsRepository;

    @GetMapping("/getGoods")
    public ResponseEntity<List<GoodEntity>> getAll() {
        List<GoodEntity> goodEntity = goodsRepository.findAll();
        return new ResponseEntity<List<GoodEntity>>(goodEntity, HttpStatus.OK);
    }

    @GetMapping("/getGoods/type/{type}/name/{name}")
    public ResponseEntity<GoodEntity> getByTypeAndName(@PathVariable String type, @PathVariable String name) {
        GoodEntity goodEntity = goodsRepository.findByTypeAndName(type, name);
        return new ResponseEntity<GoodEntity>(goodEntity, HttpStatus.OK);
    }

    @GetMapping("/getGoods/type/{type}")
    public ResponseEntity<List<GoodEntity>> getByType(@PathVariable String type) {
        List<GoodEntity> goodEntity = goodsRepository.findByType(type);
        return new ResponseEntity<List<GoodEntity>>(goodEntity, HttpStatus.OK);
    }
}
