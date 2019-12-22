package microservices.goods_service;

import org.springframework.beans.factory.annotation.Autowired;
//
//@RestController
//public class ForexRestController {
//
//    @Autowired
//    private GoodsRepository goodsRepository;
//
//    @GetMapping("/forex-exchange/from/{from}/to/{to}")
//    public ResponseEntity<GoodsDB> getCurrencyExchange(@PathVariable String type, @PathVariable String name) {
//        GoodsDB goodsDB = goodsRepository.findByTypeAndName(type, name);
//        return new ResponseEntity<GoodsDB>(goodsDB, HttpStatus.OK);
//    }
//}

//
//@RestController
//public class ForexRestController {

class TestMain{
    @Autowired
    private GoodsRepository goodsRepository;

    public void TestMain(String[] args){

        GoodEntity goodEntity = goodsRepository.findByTypeAndName("","");
    }
}