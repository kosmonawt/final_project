package pl.sda.final_project.model.user;

import pl.sda.final_project.dto.RegistrationDto;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

;

@Entity
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    @Embedded
    private Address address;
    private LocalDate birthDate;
    private String pesel;
    private String login;
    private String password;
    private String phoneNumber;
    private boolean preferEmails;
    @ManyToMany
    private List<UserRole> roles;

    public static User apply(RegistrationDto registrationDto, String pswdHash) {
        User user = new User();
        user.firstName = registrationDto.getFirstName();
        user.lastName = registrationDto.getLastName();
        user.login = registrationDto.getLogin();
        user.password = pswdHash;
        user.address = Address.apply(registrationDto);
        user.birthDate = LocalDate.parse(registrationDto.getBirthDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return user;
    }

    public void addRole(UserRole userRole) {
        if (roleExists(userRole)) {
            return;
        }
        if (roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(userRole);
    }

    private boolean roleExists(UserRole userRole) {
        return roles != null && roles.stream().anyMatch(r -> userRole.getRoleName().equals(r.getRoleName()));
    }
}
