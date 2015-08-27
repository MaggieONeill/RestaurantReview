import java.util.Random;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.Map;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public"); // Relative path for images, css, etc.
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> { //User requests the page. The server renders the template back
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cuisines", (request, response) -> {//user requests page, the server collects all of the cuisines and returns page to display them
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Cuisine> cuisines = Cuisine.all();
      model.put("cuisines", cuisines);
      model.put("template", "templates/cuisines.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cuisines/new", (request, response) -> {//user requests to create new cuisine, server retyrns a page wuth a form that will let the user create a new vehicle
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/cuisines-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/cuisines", (request, response) -> {//user submits the form. server
      HashMap<String, Object> model = new HashMap<String, Object>(); //grabs the attributes submitted through the form and uses them to creat a new product
      Cuisine new_cuisine = new Cuisine(request.queryParams("new_cuisine"));
      new_cuisine.save();
      List<Cuisine> cuisines = Cuisine.all();
      model.put("cuisines", cuisines);
      model.put("template", "templates/cuisines.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/cuisines/:id/restaurants", (request, response) -> {//user pulling up which restaurants have are identified under that cuisine.
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Cuisine> cuisines = Cuisine.all();
      Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
      List<Restaurant> restaurants = cuisine.getRestaurants();
      model.put("cuisines", cuisines);
      model.put("cuisine", cuisine);
      model.put("restaurants", restaurants);
      model.put("template", "templates/cuisine-restaurants.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cuisines/:id/restaurants/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
      model.put("cuisine", cuisine);
      model.put("template", "templates/restaurants-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/cuisines/:id/restaurants", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Cuisine> cuisines = Cuisine.all();
      Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
      Restaurant new_restaurant = new Restaurant(request.queryParams("name"), request.queryParams("dog_friendly"), cuisine.getId());
      new_restaurant.save();
      List<Restaurant> restaurants = cuisine.getRestaurants();
      model.put("cuisines", cuisines);
      model.put("cuisine", cuisine);
      model.put("restaurants", restaurants);
      model.put("template", "templates/cuisine-restaurants.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/restaurants", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Restaurant> restaurants = Restaurant.all();
      model.put("restaurants", restaurants);
      model.put("template", "templates/restaurants.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/restaurants/:id/edit", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
      model.put("restaurant", restaurant);
      model.put("template", "templates/restaurant-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/restaurants", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      //String updateName = request.queryParams("restaurantName");

      List<Cuisine> cuisines = Cuisine.all();
      Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
      Restaurant restaurant = Restaurant.find(Integer.parseInt(request.queryParams("id")));
      restaurant.updateName(request.queryParams("restaurantName"));
      // restaurant.updateDescription(request.queryParams("description"));
      List<Restaurant> restaurants = cuisine.getRestaurants();
      model.put("cuisines", cuisines);
      model.put("cuisine", cuisine);
      model.put("restaurants", restaurants);
      model.put("template", "templates/cuisine-restaurants.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

   }

}
