import org.sql2o.*;
import java.util.List;

public class Cuisine{

  private int id;
  private String type;

  public Cuisine (String type) {
    this.type = type; //italian, chinese, burgers etc.
  }

  public String getType() {
    return type;
  }

  public int getId(){
    return id;
  }

  public List<Restaurant> getRestaurants(){
    String sql = "SELECT * FROM restaurants WHERE cuisine_id = :id;";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetch(Restaurant.class);
    }
  }

  public static List<Cuisine> all (){
    String sql = "SELECT * FROM cuisine";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Cuisine.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO cuisine (type) VALUES (:type);";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int)con.createQuery(sql, true)
        .addParameter("type", type)
        .executeUpdate()
        .getKey();
    }
  }

  public static Cuisine find(int id) {
    String sql = "SELECT * FROM cuisine WHERE id = :id;";
    try (Connection con = DB.sql2o.open()) {
      Cuisine cuisine = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Cuisine.class);
    return cuisine;
    }
  }

  @Override
  public boolean equals(Object otherCuisine) {
    if(!(otherCuisine instanceof Cuisine)) {
      return false;
    }else{
      Cuisine newCuisine = (Cuisine) otherCuisine;
      return this.getType().equals(newCuisine.getType()) &&
          this.getId() == newCuisine.getId();
      }
  }

  public void update (String new_type) {
    this.type = new_type;
    String sql = "UPDATE cuisine WHERE id = :id;";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void delete() {
    String sql = "DELETE FROM cuisine WHERE id = :id;";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
