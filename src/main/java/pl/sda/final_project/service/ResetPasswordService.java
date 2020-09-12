package pl.sda.final_project.service;

import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import pl.sda.final_project.model.user.ResetPasswordEntity;
import pl.sda.final_project.model.user.UserEntity;
import pl.sda.final_project.repo.ResetPasswordRepo;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ResetPasswordService {
    private final ResetPasswordRepo resetPasswordRepo;
    private final MessageService messageService;

    //TODO check if token valid


    public ResetPasswordService(ResetPasswordRepo resetPasswordRepo, MessageService messageService) {
        this.resetPasswordRepo = resetPasswordRepo;
        this.messageService = messageService;
    }

    void saveResetPasswordEntry(UserEntity user) {
        String token = new RandomString(15).nextString();
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(10);
        ResetPasswordEntity resetPasswordEntity = new ResetPasswordEntity();
        resetPasswordEntity.setToken(token);
        resetPasswordEntity.setExpiryDate(expiryTime);
        resetPasswordEntity.setUsed(false);
        resetPasswordEntity.setUser(user);
        resetPasswordRepo.save(resetPasswordEntity);
        messageService.sendResetMail(token, user.getLogin());

    }


    public UserEntity findUserByToken(String token) {
        return resetPasswordRepo.findByToken(token)
                .map(ResetPasswordEntity::getUser).orElseThrow(
                        () -> new RuntimeException("token does not exist")
                );


    }
}
