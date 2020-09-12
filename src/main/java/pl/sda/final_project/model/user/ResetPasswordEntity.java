package pl.sda.final_project.model.user;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class ResetPasswordEntity extends BaseEntity {

    private String token;
    private LocalDateTime expiryDate;
    @ManyToOne
    private UserEntity user;
    private boolean used;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
