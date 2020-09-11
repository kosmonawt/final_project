package pl.sda.final_project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.final_project.model.product.ProductEntity;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByTitle(String title);

}
