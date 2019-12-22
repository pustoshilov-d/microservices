package microservices.auth_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import microservices.auth_server.models.*;

@RestController
public class HelloResource {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/hello")
    public String hello(){
        return "Hello nah";
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken (@RequestBody AuthReq authReq) throws Exception{
        try{
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
        }
        catch (Exception e) {
            throw new Exception("Неверный пароль или имя", e);
        }

        final UserDetails userDetails = userDetailsService
            .loadUserByUsername(authReq.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthRes(jwt));
    }
}

