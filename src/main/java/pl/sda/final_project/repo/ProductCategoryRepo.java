package pl.sda.final_project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sda.final_project.model.product.ProductCategoryEntity;

@Repository
public interface ProductCategoryRepo extends JpaRepository<ProductCategoryEntity, Long> {

    @Query("select case when count(pc)> 0 then true else false end from ProductCategoryEntity pc where lower(pc.title) like lower(?1)")
    boolean existsByTitle(String title);

}
