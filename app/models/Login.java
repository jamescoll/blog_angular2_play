package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "blog_login_record")
public class Login extends Model {

    @Id
    @GeneratedValue
    Long id;

    @Constraints.Required
    String username;

    @Constraints.Required
    @Formats.DateTime(pattern="dd/MM/yyyy")
    Date timestamp = new Date();

    @Constraints.Required
    boolean validLogin;

    @Constraints.Required
    String reason;

    public Login(String username, boolean validLogin, String reason){
        this.username = username; this.timestamp = new Date(); this.validLogin = validLogin; this.reason = reason;
    }

    public static Finder<Long, Login> find = new Finder<Long, Login>(Login.class);

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isValidLogin() {
        return validLogin;
    }

    public void setValidLogin(boolean validLogin) {
        this.validLogin = validLogin;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
