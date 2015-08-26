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

    public static List<Cuisine> all (){
      String sql = "SELECT * FROM RESTAURANTS";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Cuisine.class);
      }
    }

    
  }
