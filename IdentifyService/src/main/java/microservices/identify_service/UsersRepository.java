package microservices.identify_service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUser(String user);

}