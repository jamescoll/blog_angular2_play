package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.BodyParser;
import play.libs.Json;
import java.util.List;
import java.util.Date;
import com.avaje.ebean.annotation.Transactional;
import models.*;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;


public class PostController extends Controller {


public Result getPosts() {

        List<Post> posts = Post.find.all();

        return ok(Json.toJson(posts));

}

public Result getPost(Long id) {

        Post post = Post.find.byId(id);

        return ok(Json.toJson(post));

}


/*@Transactional
@BodyParser.Of(BodyParser.Json.class)
public Result receiveAndDBifyJson() {

        JsonNode json = request().body().asJson();
        String name = json.findPath("name").textValue();
        boolean done = Boolean.valueOf(json.findPath("done").textValue());
        Date date = new Date(new Long(json.findPath("date").textValue()));

        ObjectNode result = Json.newObject();
        result.put("Success", "True");
        return ok(result);

}*/
}
