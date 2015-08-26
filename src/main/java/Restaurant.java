import org.sql2o.*;
import java.util.List;

public class Restaurant {

  private int id;
  private String name;
  private String area;
  private Boolean dog_friendly;
  private int cuisine_id;


  public Restaurant (int id, String name, Boolean dog_friendly, int cuisine_id, String area) {
    this.id = id;
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

  public static List<Restaurant> all() {
    String sql = "SELECT * FROM restaurants";
    try (Connection con = DB.sql2o.open()) {
       return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO restaurants (name, dog_friendly, cuisine_id, area) VALUES (:name, :dog_friendly, :cuisine_id, :area);";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", name)
      .addParameter("area", area)
      .addParameter("dog_friendly", dog_friendly)
      .addParameter("cuisine_id", cuisine_id)
      .executeUpdate()
      .getKey();
    }
  }

}
