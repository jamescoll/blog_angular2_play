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
@Table(name="login_info")
public class Login extends Model {
  @Id
@GeneratedValue
public Long id;

@Constraints.Required
public String name;

public boolean staffMember;

@Formats.DateTime(pattern="dd/MM/yyyy")
public Date date = new Date();

public static Finder<Long, Login> find = new Finder<Long, Login> (Login.class);

public Login(String name, boolean staffMember, Date date){
    this.name = name; this.staffMember = staffMember; this.date = date;
}

}
