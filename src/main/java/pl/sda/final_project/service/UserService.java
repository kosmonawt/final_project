package pl.sda.final_project.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.final_project.dto.ChangePasswordDto;
import pl.sda.final_project.dto.RegistrationDto;
import pl.sda.final_project.dto.ResetPasswordDto;
import pl.sda.final_project.dto.UserDto;
import pl.sda.final_project.model.user.UserEntity;
import pl.sda.final_project.model.user.UserRole;
import pl.sda.final_project.repo.UserRepo;
import pl.sda.final_project.repo.UserRoleRepo;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;
    private final PasswordEncoder passwordEncoder;
    private final ResetPasswordService resetPasswordService;


    public UserService(UserRepo userRepo, UserRoleRepo userRoleRepo, PasswordEncoder passwordEncoder, ResetPasswordService resetPasswordService) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.passwordEncoder = passwordEncoder;
        this.resetPasswordService = resetPasswordService;
    }


    public void registerUser(RegistrationDto registrationDto) {
        String pass = passwordEncoder.encode(registrationDto.getPassword());
        if (userWithEmailExists(registrationDto.getLogin())) {
            throw new RuntimeException("Użytkownik o emailu " + registrationDto.getLogin() + "istnieje");
        }
        UserEntity userToSave = UserEntity.apply(registrationDto, pass);
        userToSave.addRole(userRoleRepo.findByRoleName(UserRole.Roles.USER.name()));
        userRepo.save(userToSave);

    }

    private boolean userWithEmailExists(String login) {
        return userRepo.existsByLogin(login);
    }

    public UserDto getCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByLogin(userName)
                .map(UserDto::apply)
                .orElseThrow(() -> new RuntimeException("Cant find user"));
    }

    public void sendResetLink(ResetPasswordDto resetPasswordDto) {
        userRepo.findByLogin(resetPasswordDto.getLogin())
                .ifPresentOrElse(resetPasswordService::saveResetPasswordEntry,
                        () -> {
                            throw new RuntimeException("User does not exist");
                        });
    }

    public void changePassword(ChangePasswordDto changePasswordDto) {
        UserEntity userEntity = resetPasswordService.findUserByToken(changePasswordDto.getToken());
        userEntity.setPassword(passwordEncoder.encode(changePasswordDto.getPassword()));
        userRepo.save(userEntity);


    }
}


