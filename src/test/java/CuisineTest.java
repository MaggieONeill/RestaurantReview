import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class CuisineTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void getType_returnsType() {
      Cuisine newCuisine = new Cuisine("Italian");
      assertEquals("Italian", newCuisine.getType());
    }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Cuisine.all().size());
  }

  @Test
  public void equals_returnTrueIfTypesMatch(){
    Cuisine firstCuisine = new Cuisine("burgers");
    Cuisine secondCuisine = new Cuisine("burgers");
    assertEquals(true, firstCuisine.equals(secondCuisine));
  }

  @Test
  public void getId_returnsCorrectId() {
    Cuisine newCuisine = new Cuisine("burger");
    newCuisine.save();
    Cuisine savedCuisine = Cuisine.find(newCuisine.getId());
    assertEquals(savedCuisine, newCuisine);
  }

  @Test
  public void update_Cuisine() {
    Cuisine newCuisine = new Cuisine("burgers");
    newCuisine.save();
    newCuisine.delete();
    assertEquals(0, Cuisine.all().size());
  }
}
