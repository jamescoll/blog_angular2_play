package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.nimbusds.jwt.JWTClaimsSet;
import models.Login;
import models.User;
import models.UserPassword;
import org.mindrot.jbcrypt.BCrypt;
import play.Logger;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import services.JsonWebTokenServiceImpl;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static play.libs.Json.toJson;

public class LoginController extends Controller {

    @Inject
    private JsonWebTokenServiceImpl jwtService;

    @BodyParser.Of(BodyParser.Json.class)
    public Result login() {

        JsonNode json = request().body().asJson();
        String username = json.findPath("username").textValue();
        String password = json.findPath("password").textValue();

        
        if (User.find.where().eq("emailAddress", username).findRowCount() != 0) {

            User user = User.find.where().eq("emailAddress", username).findUnique();
            UserPassword userPassword;
            if (user != null) {
                userPassword = UserPassword.find.byId(user.getUUID());

                if (userPassword != null) {

                    if (BCrypt.checkpw(password, userPassword.getHashedPassword())) {
                        String token;
                        try {
                            token = jwtService.sign(user.getEmailAddress());
                        } catch (Exception e) {
                            Logger.warn("Problem calling jwt service");
                            token = "";
                        }

                        if(userPassword.isChange()){
                            new Login(username, true, "Successful login with redirect").save();
                            final Map<String, String> map = new HashMap<>();
                            map.put("success", "redirect");
                            map.put("token", token);
                            map.put("firstUse", String.valueOf(user.isFirstTimeUser()));
                            return ok(toJson(map));
                        }

                        new Login(username, true, "Successful login").save();
                        final Map<String, String> map = new HashMap<>();
                        map.put("success", "true");
                        map.put("token", token);
                        map.put("firstUse", String.valueOf(user.isFirstTimeUser()));
                        return ok(toJson(map));
                    } else {

                        new Login(username, false, "Userpassword not found in database for user " + username).save();
                        final Map<String, String> map = new HashMap<>();
                        map.put("success", "false");
                        map.put("token", "");
                        return ok(toJson(map));
                    }
                }
            } else {
                new Login(username, false, "User not found in database for user " + username).save();
                final Map<String, String> map = new HashMap<>();
                map.put("success", "false");
                map.put("token", "");
                return ok(toJson(map));
            }


        } else {
            new Login(username, false, "User doesn't exist for user " + username).save();
            final Map<String, String> map = new HashMap<>();
            map.put("success", "false");
            map.put("token", "");
            return ok(toJson(map));
        }


        final Map<String, String> map = new HashMap<>();
        map.put("success", "false");
        map.put("token", "");
        return ok(toJson(map));
    }


}