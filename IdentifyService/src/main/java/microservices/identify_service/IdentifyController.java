package microservices.identify_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class IdentifyController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserEntity>> showUsers() {
        List<UserEntity> users = usersRepository.findAll();
        return new ResponseEntity<List<UserEntity>>(users, HttpStatus.OK);
    }


    @GetMapping("/getUser/user/{user}")
    public ResponseEntity<UserEntity> showUser(@PathVariable String user) {
        if (!usersRepository.existsById(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            UserEntity userEntity = usersRepository.findByUser(user);
            return new ResponseEntity<UserEntity>(userEntity, HttpStatus.OK);
        }
    }

    @GetMapping("/existsUser/user/{user}")
    public ResponseEntity<Boolean> existsUser(@PathVariable String user) {
        if (!usersRepository.existsById(user)) {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }
    }

    @GetMapping("/logIn/user/{user}/password/{password}")
    public ResponseEntity<Boolean> logIn(@PathVariable String user, @PathVariable String password) {
        if (usersRepository.existsById(user)) {
            UserEntity userEntity = usersRepository.findByUser(user);
            if (userEntity.getPassword().equals(password)) {
                return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // не существует
        }
    }

    @GetMapping("/addUser/user/{user}/password/{password}")
    public ResponseEntity<String> addUser(@PathVariable String user, @PathVariable String password) {
        String result;
        if (!usersRepository.existsById(user)) {
            UserEntity userEntity = new UserEntity(user, password);
            usersRepository.save(userEntity);
            result = "Пользователь добавлен в базу. ";
        } else {
            result = "Пользователь не добавлен в базу. ";
        }

        ResponseEntity<Boolean> answer = restTemplate.getForEntity("http://localhost:9001/createBasket/user/" + user, Boolean.class);
        if (answer.hasBody()) {
            if (answer.getBody()) {
                result = result + "Корзина пользователя создана. ";
            } else {
                result = result + "Корзина пользователя не создана. ";
            }
        } else {
            result = result + "Корзина пользователя не создана. ";
        }

        ResponseEntity<Boolean> answer2 = restTemplate.getForEntity("http://localhost:9002/addUser/user/" + user, Boolean.class);
        if (answer2.hasBody()) {
            if (answer2.getBody()) {
                result = result + "Карта подключена.";
            } else {
                result = result + "Карта не подключена.";
            }
        } else {
            result = result + "Карта не подключена.";
        }

        return new ResponseEntity<String>(result, HttpStatus.OK);


    }
}

