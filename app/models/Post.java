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
@Table(name="blog_post")
public class Post extends Model {

@Id
@GeneratedValue
public Long id;

@Constraints.Required
public int userId;

public String title;

@Formats.DateTime(pattern="dd/MM/yyyy")
public Date created = new Date();

@Constraints.Required
@Column(columnDefinition = "TEXT")
public String body;

public static Finder<Long, Post> find = new Finder<Long, Post> (Post.class);

public Post(int userId, String title, String body){
    this.userId = userId; this.title = title; this.created = new Date(); this.body = body; 
}

}
