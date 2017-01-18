package models;

import com.avaje.ebean.Model;
import org.apache.commons.lang3.RandomStringUtils;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "blog_userpassword")
public class UserPassword extends Model {

    public static Finder<UUID, UserPassword> find = new Finder<UUID, UserPassword>(UserPassword.class);

    @Id
    @Constraints.Required
    private UUID userId;
    @Constraints.Required
    private String hashedPassword;
    @Constraints.Required
    private boolean change;


    public UserPassword(UUID userId, String hashedPassword, boolean change) {
        this.userId = userId;
        this.hashedPassword = hashedPassword;
        this.change = change;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public boolean isChange() {
        return change;
    }

    public void setChange(boolean change) {
        this.change = change;
    }

    public static String generateFirstPassword() {
        return RandomStringUtils.randomAlphanumeric(12);
    }

    public static UserPassword findByUUID(UUID uuid) {
        return find.where()
                .eq("userId",
                        uuid)
                .findUnique();
    }
}
