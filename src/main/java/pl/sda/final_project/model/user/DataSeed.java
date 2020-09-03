package pl.sda.final_project.model.user;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import pl.sda.final_project.repo.UserRoleRepo;

@Component
public class DataSeed implements InitializingBean {
    // afterPropertiesSet() runs after SPRING context init

    private UserRoleRepo userRoleRepo;

    public DataSeed(UserRoleRepo userRoleRepo) {
        this.userRoleRepo = userRoleRepo;
    }


    @Override
    public void afterPropertiesSet() {
        createRole(UserRole.Roles.ADMIN);
        createRole(UserRole.Roles.USER);
    }

    private void createRole(UserRole.Roles role) {
        if (!userRoleRepo.roleExists(role.name())) {
            userRoleRepo.save(UserRole.apply(role));
        }
    }
}
