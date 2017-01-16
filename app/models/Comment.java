package models;
import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="blog_comment")
public class Comment extends Model {

@Id
@GeneratedValue
public Long id;

@Constraints.Required
public int postId;

@Constraints.Required
public String name;

@Formats.DateTime(pattern="dd/MM/yyyy")
public Date created = new Date();

@Constraints.Required
public String email;

@Constraints.Required
@Column(columnDefinition = "TEXT")
public String body;

public static Finder<Long, Comment> find = new Finder<Long, Comment> (Comment.class);

public Comment(int postId, String name, String email, String body){
    this.postId = postId; this.name = name; this.created = new Date(); this.email = email; this.body = body;
}

}
