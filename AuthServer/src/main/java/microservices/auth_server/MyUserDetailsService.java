package microservices.auth_server;

import microservices.auth_server.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        ResponseEntity<UserEntity> user = restTemplate.getForEntity("http://localhost:9004//getUser/user/" + userName, UserEntity.class);
        return new User(userName, user.getBody().getPassword(), new ArrayList<>());
    }
}
