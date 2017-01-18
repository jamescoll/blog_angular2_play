package models;

import be.objectify.deadbolt.java.models.Permission;
import be.objectify.deadbolt.java.models.Role;
import be.objectify.deadbolt.java.models.Subject;
import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "blog_user")
public class User extends Model implements Subject {

    @Id
    @GeneratedValue
    UUID id;

    String firstName;
    String lastName;

    @Constraints.Required
    @Constraints.Email
    @Column(unique = true, name = "username")
    String emailAddress;

    boolean firstTimeUser;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "blog_user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    List<SecurityRole> roles;

    public User(){}

    public User(String firstName, String lastName, String emailAddress, List<SecurityRole> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.roles = roles;
        this.firstTimeUser = true;
    }

    @Override
    public List<? extends Role> getRoles() {

        return roles;
    }

    //Note - placeholder for fine-grained permissions
    @Override
    public List<? extends Permission> getPermissions() {
        return null;
    }

    @Override
    public String getIdentifier() {
        return emailAddress;
    }

    public UUID getUUID() {
        return id;
    }

    public static Finder<UUID, User> find = new Finder<UUID, User>(User.class);

    public static User findByUserName(String userName) {
        return find.where()
                .eq("username",
                        userName)
                .findUnique();
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setRoles(List<SecurityRole> roles) {
        this.roles = roles;
    }

    public boolean isFirstTimeUser() {
        return firstTimeUser;
    }

    public void setFirstTimeUser(boolean firstTimeUser) {
        this.firstTimeUser = firstTimeUser;
    }
}
