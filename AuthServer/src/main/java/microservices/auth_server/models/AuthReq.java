package microservices.auth_server.models;

public class AuthReq {

    private String username;
    private String password;

    public AuthReq(){
    }

    public  AuthReq(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
