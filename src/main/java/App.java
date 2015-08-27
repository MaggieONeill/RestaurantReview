import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

import java.lang.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import spark.ModelAndView;

import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App{
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model =new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
    }, new VelocityTemplateEngine());
  }
}
