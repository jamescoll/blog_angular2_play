package models;

import be.objectify.deadbolt.java.models.Role;
import com.avaje.ebean.Model;
import play.data.validation.Constraints.Required;

import javax.persistence.*;
import java.util.List;


@Entity
@SequenceGenerator(name="startZero", initialValue=1)
@Table(name = "blog_security_role")
public class SecurityRole extends Model implements Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="startZero")
    public Long id;
    @Required
    public String name;
    @ManyToMany(mappedBy = "roles")
    public List<User> users;

    public SecurityRole(String name) {
        this.name = name;
    }

    public static SecurityRole findByName(String name) {
        return find.where()
                .eq("name",
                        name)
                .findUnique();
    }

    public String getName() {
        return name;
    }

    public static Finder<Long, SecurityRole> find = new Finder<Long, SecurityRole>(
            SecurityRole.class);
}
