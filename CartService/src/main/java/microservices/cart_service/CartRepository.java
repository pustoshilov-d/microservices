package microservices.cart_service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, String> {
    CartEntity findByUser(String user);

}