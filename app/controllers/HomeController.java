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


public class HomeController extends Controller {


public Result index(String path) {
        return ok( views.html.index.render());
}


public Result routedToPlay() {
        return ok();
}

public Result getJson() {

        Login mostRecent = Login.find.where()
                           .orderBy("date asc")
                           .setMaxRows(1)
                           .findUnique();

        ObjectNode result = Json.newObject();
        result.put("name", mostRecent.name);
        result.put("staff_member", String.valueOf(mostRecent.staffMember));
        result.put("login_date", mostRecent.date.toString());
        return ok(result);
}

@Transactional
@BodyParser.Of(BodyParser.Json.class)
public Result receiveAndDBifyJson() {

        JsonNode json = request().body().asJson();
        String name = json.findPath("name").textValue();
        boolean done = Boolean.valueOf(json.findPath("done").textValue());
        Date date = new Date(new Long(json.findPath("date").textValue()));


        Login login = new Login(name, done, new Date());
        login.save();

        ObjectNode result = Json.newObject();
        result.put("Success", "True");
        return ok(result);

}
}