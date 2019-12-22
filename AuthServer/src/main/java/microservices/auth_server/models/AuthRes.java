package microservices.auth_server.models;

public class AuthRes {

    private final String jwt;

    public AuthRes(String jwt){
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }


}


