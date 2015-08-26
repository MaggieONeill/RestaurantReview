import org.junit.*;
import static org.junit.Assert.*;


public class RestaurantTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void all_emptyAtFirst() {
      assertEquals(Restaurant.all().size(), 0);
   }

   @Test
   public void all_returnsSavedObjects() {
     Restaurant newRestaurant = new Restaurant(1,"503Burgers", true, 1, "NE");
     newRestaurant.save();
     assertEquals(Restaurant.all().size(), 1);
    }

  @Test
  public void getName_returnsRestaurantName_503Burgers() {
    Restaurant newRestaurant = new Restaurant(1, "503Burgers", true, 1, "NE");
    assertEquals("503Burgers", newRestaurant.getRestaurantName());
  }

  @Test
  public void getArea_returnsCorrectArea() {
    Restaurant newRestaurant = new Restaurant(1, "503burgers", true, 1, "NE");
    assertEquals("NE", newRestaurant.getArea());
  }

  @Test
  public void getdog_friendly_returnsTrue() {
    Restaurant newRestaurant = new Restaurant(1, "503burgers", true, 1, "NE");
    assertEquals(true, newRestaurant.getDogFriendly());
  }

  @Test
  public void getcuisine_id_returnsCorrectId() {
    Restaurant newRestaurant = new Restaurant(1, "503burgers", true, 1, "NE");
    assertEquals(1, newRestaurant.getCuisineId());
  }


  // @Test
  // public void getId_returnsCorrectValue() {
  //   Restaurant newRestaurant = new Restaurant(1,"503Burgers", "NE", true, 1);
  //   newRestaurant.save();
  //   assertEquals(Restaurant.all().get(0).getId(), newRestaurant.getId());
  // }
 }
