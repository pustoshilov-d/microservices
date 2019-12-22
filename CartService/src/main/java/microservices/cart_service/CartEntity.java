package microservices.cart_service;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "carts")
public class CartEntity {

    @Id
    @Column(name = "user")
    private String user;
    @Column(name = "balance")
    private Double balance = 0.0;

    public CartEntity() {
    }

    public CartEntity(String user) {
        this.user = user;
        this.balance = 100.0;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
