package microservices.goods_service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<GoodEntity, Integer> {
    GoodEntity findByTypeAndName(String type, String name);
    List<GoodEntity> findByType(String type);
    List<GoodEntity> findAll();

}