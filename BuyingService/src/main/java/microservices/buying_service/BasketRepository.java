package microservices.buying_service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<BasketEntity, String> {
    BasketEntity findByUser(String user);



//    List<BasketEntity> findByType(String type);
//    List<BasketEntity> findAll();

}