package microservices.auth_server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carts")
public class UserEntity {

    @Id
    @Column(name = "user")
    private String user;
    @Column(name = "password")
    private String password = null;

    public UserEntity() {
    }

    public UserEntity(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
