import org.sql2o.*;
import java.util.List;

public class Restaurant {

  private int id;
  private String name;
  private String area;
  private Boolean dog_friendly;
  private int cuisine_id;


  public Restaurant (String name, Boolean dog_friendly, int cuisine_id, String area) {
    this.name = name;
    this.area = area;
    this.dog_friendly = dog_friendly;
    this.cuisine_id = cuisine_id;
  }

  public int getId() {
    return id;
  }

  public String getRestaurantName() {
    return name;
  }

  public String getArea() {
    return area;
  }

  public Boolean getDogFriendly() {
    return dog_friendly;
  }

  public int getCuisineId() {
    return cuisine_id;
  }

  @Override
  public boolean equals(Object otherRestaurant) {
    if(!(otherRestaurant instanceof Restaurant)){
      return false;
    }else{
      Restaurant newRestaurant =(Restaurant) otherRestaurant;
      return this.getRestaurantName().equals(newRestaurant.getRestaurantName()) &&
              this.getId() == newRestaurant.getId();
    }
  }


  public static List<Restaurant> all() {
    String sql = "SELECT * FROM restaurants";
    try (Connection con = DB.sql2o.open()) {
       return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO restaurants (id, name, dog_friendly, cuisine_id, area) VALUES (:id, :name, :dog_friendly, :cuisine_id, :area);";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
      .addParameter("id", id)
      .addParameter("name", name)
      .addParameter("area", area)
      .addParameter("dog_friendly", dog_friendly)
      .addParameter("cuisine_id", cuisine_id)
      .executeUpdate()
      .getKey();
    }
  }

  public static Restaurant find(int id) {
    String sql = "SELECT * FROM restaurants WHERE id = :id;";
    try (Connection con = DB.sql2o.open()) {
      Restaurant restaurant = con.createQuery(sql)
                            .addParameter("id", id)
                            .executeAndFetchFirst(Restaurant.class);
      return restaurant;
    }
  }

  public void updateName(String new_name) {
    this.name = new_name;
    String sql = "UPDATE restaurants SET name = :name WHERE id = :id;";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateDogFriendly(boolean new_dog_friendly) {
    this.dog_friendly = new_dog_friendly;
    String sql = "UPDATE restaurants SET dog_friendly = :dog_friendly WHERE id = :id;";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("dog_friendly", dog_friendly)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateArea(String new_area) {
    this.area = new_area;
    String sql = "UPDATE restaurants SET area = :area WHERE id = :id;";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("area", area)
        .addParameter("id", id)
        .executeUpdate();
    }
  }


  public void updateCuisine(int new_cuisine_id) {
    this.cuisine_id = new_cuisine_id;
    String sql = "UPDATE restaurants SET cuisine_id = :cuisine_id WHERE id = :id;";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("cuisine_id", cuisine_id)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    String sql = "DELETE FROM restaurants WHERE id = :id;";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }













}
