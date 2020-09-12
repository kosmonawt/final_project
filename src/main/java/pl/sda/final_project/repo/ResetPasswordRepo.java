package pl.sda.final_project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.final_project.model.user.ResetPasswordEntity;

import java.util.Optional;

@Repository
public interface ResetPasswordRepo extends JpaRepository<ResetPasswordEntity, Long> {
    Optional<ResetPasswordEntity> findByToken(String token);
}
