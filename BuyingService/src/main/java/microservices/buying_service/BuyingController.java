package microservices.buying_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.StyledEditorKit;
import java.util.List;
import java.util.Map;

@RestController
public class BuyingController {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getBasket")
    public ResponseEntity<List<BasketEntity>> getAll() {
        List<BasketEntity> basketEntity = basketRepository.findAll();
        return new ResponseEntity<List<BasketEntity>>(basketEntity, HttpStatus.OK);
    }

    @GetMapping("/getBasket/user/{user}")
    public ResponseEntity<BasketEntity> getByUser(@PathVariable String user) {
        if (basketRepository.existsById(user)) {
            BasketEntity basketEntity = basketRepository.findByUser(user);
            basketEntity.updateTotalPrice();
            return new ResponseEntity<BasketEntity>(basketEntity, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/createBasket/user/{user}")
    public ResponseEntity<Boolean> createBasket(@PathVariable String user) {
        if (!basketRepository.existsById(user)) {
            BasketEntity basketEntity = new BasketEntity(user);
            basketRepository.save(basketEntity);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(Boolean.FALSE,HttpStatus.OK);
        }
    }

    @GetMapping("/payBasket/user/{user}")
    public ResponseEntity<String> payBasket(@PathVariable String user) {
        if (!basketRepository.existsById(user)) {
            return new ResponseEntity<>("Неверный пользователь", HttpStatus.BAD_REQUEST);
        }
        else {
            BasketEntity basketEntity = basketRepository.findByUser(user);
            basketEntity.updateTotalPrice();

            ResponseEntity<Boolean> answer = restTemplate.getForEntity("http://localhost:9002/pay/user/" + user + "/value/" + basketEntity.getTotalPrice(), Boolean.class);
            if (answer.hasBody()) {
                if (answer.getBody()) {
                    basketRepository.delete(basketEntity);
                    BasketEntity newBasketEntety = new BasketEntity(user);
                    basketRepository.save(newBasketEntety);
                    return new ResponseEntity<>("Покупка совершена", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Не хватает денег на покупку", HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("Неверный пользователь", HttpStatus.BAD_REQUEST);
            }
        }
    }


    //Добавить в корзину
    @PutMapping("/addToBasket/user/{user}/good/{good}")
    public ResponseEntity<Boolean> addToBasket(@PathVariable String user,@PathVariable String good, @RequestBody Map<String, Integer> body) {
        if (basketRepository.existsById(user)) {
            BasketEntity basketEntity = basketRepository.findByUser(user);
            try {
                Integer cur_count = (Integer) basketEntity.getClass().getMethod("get" + good).invoke(basketEntity);
//            System.out.println(cur_count);
                basketEntity.getClass().getMethod("set"+good, Integer.class).invoke(basketEntity, cur_count+body.get("count"));
                basketRepository.save(basketEntity);
            }
            catch (Exception err) {
                System.out.print("Ошибка названия: " + err);
            }
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //Убрать из карзины
    @PutMapping("/delFromBasket/user/{user}/good/{good}")
    public ResponseEntity<Boolean> delToBasket(@PathVariable String user, @PathVariable String good, @RequestBody Map<String, Integer> body) {
        System.out.println(user+good+body);
        if (basketRepository.existsById(user)) {
            BasketEntity basketEntity = basketRepository.findByUser(user);
            try {
                Integer cur_count = (Integer) basketEntity.getClass().getMethod("get" + good).invoke(basketEntity);
                Integer count = body.get("count");
                basketEntity.getClass().getMethod("set" + good, Integer.class).invoke(basketEntity, Math.max(cur_count - count, 0));
                basketRepository.save(basketEntity);
            }
            catch (Exception err) {
                System.out.print("Ошибка названия: " + err);
            }
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    //Убрать из карзины всё
    @DeleteMapping("/delBasket/user/{user}")
    public ResponseEntity<Boolean> delBasket(@PathVariable String user) {
        if (basketRepository.existsById(user)) {
            BasketEntity basketEntity = basketRepository.findByUser(user);
            basketRepository.delete(basketEntity);
            BasketEntity newBasketEntety = new BasketEntity(user);
            basketRepository.save(newBasketEntety);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
