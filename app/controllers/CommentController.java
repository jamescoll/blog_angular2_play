package controllers;

import com.avaje.ebean.Ebean;
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


public class CommentController extends Controller {


public Result getComments() {

        if(!request().getQueryString("postId").isEmpty()){
                List<Comment> comments = Ebean.find(Comment.class)
                        .where()
                        .eq("postId", Integer.parseInt(request().getQueryString("postId")))
                        .findList();
                return ok(Json.toJson(comments));


        } else {
                List<Comment> comments = Comment.find.all();
                return ok(Json.toJson(comments));
        }
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
