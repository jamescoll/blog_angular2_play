package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import org.mindrot.jbcrypt.BCrypt;
import java.util.*;
import models.*;

public class HomeController extends Controller {

        public Result index(String path) { 
            return ok( views.html.index.render());
        }

        
}
