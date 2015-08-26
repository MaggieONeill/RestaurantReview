import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class CuisineTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void getType_returnsType() {
    Cuisine newCuisine = new Cuisine("burgers");
    assertEquals("burgers", newCuisine.getType());
  }

}
