package pl.sda.final_project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.sda.final_project.model.user.UserEntity;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    @Query("select case when count(u)> 0 then true else false end from UserEntity u where lower(u.login) like lower(?1)")
    boolean existsByLogin(String login);

    @Query("select u from UserEntity u where lower(u.login) = lower(?1)")
    Optional<UserEntity> findByLogin(String login);
}
