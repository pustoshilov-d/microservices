package microservices.cart_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CartController {

    @Autowired
    private CartRepository cartRepository;

//    Баланс
    @GetMapping("/getBalance/user/{user}")
    public ResponseEntity<CartEntity> showBalance(@PathVariable String user){
        if (!cartRepository.existsById(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            CartEntity cartEntity = cartRepository.findByUser(user);
            return new ResponseEntity<CartEntity>(cartEntity, HttpStatus.OK);
        }
    }

    //Добавление юзера
    @GetMapping("/addUser/user/{user}")
    public ResponseEntity<Boolean> addUser(@PathVariable String user){
        if (!cartRepository.existsById(user)) {
            CartEntity cartEntity = new CartEntity(user);
            cartRepository.save(cartEntity);
            return new ResponseEntity<> (Boolean.TRUE, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<> (Boolean.FALSE, HttpStatus.OK);
        }
    }

//    Оплата
    @GetMapping("/pay/user/{user}/value/{value}")
    public ResponseEntity<Boolean> pay(@PathVariable String user, @PathVariable Double value){
        if (!cartRepository.existsById(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            CartEntity cartEntity = cartRepository.findByUser(user);
            if (cartEntity.getBalance() - value >= 0){
                cartEntity.setBalance(cartEntity.getBalance() - value);
                cartRepository.save(cartEntity);
                return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
            }


        }
    }
}
