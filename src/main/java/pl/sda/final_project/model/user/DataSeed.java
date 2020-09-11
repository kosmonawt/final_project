package pl.sda.final_project.model.user;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import pl.sda.final_project.model.product.ProductCategoryEntity;
import pl.sda.final_project.repo.ProductCategoryRepo;
import pl.sda.final_project.repo.UserRoleRepo;

@Component
public class DataSeed implements InitializingBean {
    // afterPropertiesSet() runs after SPRING context init

    private UserRoleRepo userRoleRepo;
    private ProductCategoryRepo productCategoryRepo;

    public DataSeed(UserRoleRepo userRoleRepo, ProductCategoryRepo productCategoryRepo) {
        this.productCategoryRepo = productCategoryRepo;
        this.userRoleRepo = userRoleRepo;
    }


    @Override
    public void afterPropertiesSet() {
        createRole(UserRole.Roles.ADMIN);
        createRole(UserRole.Roles.USER);
        addCategory(new ProductCategoryEntity("mleko"));
        addCategory(new ProductCategoryEntity("Piwo"));
    }

    private void createRole(UserRole.Roles role) {
        if (!userRoleRepo.roleExists(role.name())) {
            userRoleRepo.save(UserRole.apply(role));
        }
    }

    private void addCategory(ProductCategoryEntity productCategory) {

        if (!productCategoryRepo.existsByTitle(productCategory.getTitle())) {
            productCategoryRepo.save(productCategory);
        }

    }
}
