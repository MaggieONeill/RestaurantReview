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
     Restaurant newRestaurant = new Restaurant("503Burgers", true, 1, "NE");
     newRestaurant.save();
     assertEquals(Restaurant.all().size(), 1);
    }

  @Test
  public void getName_returnsRestaurantName_503Burgers() {
    Restaurant newRestaurant = new Restaurant("503Burgers", true, 1, "NE");
    assertEquals("503Burgers", newRestaurant.getRestaurantName());
  }

  @Test
  public void getArea_returnsCorrectArea() {
    Restaurant newRestaurant = new Restaurant("503burgers", true, 1, "NE");
    assertEquals("NE", newRestaurant.getArea());
  }

  @Test
  public void getdog_friendly_returnsTrue() {
    Restaurant newRestaurant = new Restaurant("503burgers", true, 1, "NE");
    assertEquals(true, newRestaurant.getDogFriendly());
  }

  @Test
  public void getcuisine_id_returnsCorrectId() {
    Restaurant newRestaurant = new Restaurant("503burgers", true, 1, "NE");
    assertEquals(1, newRestaurant.getCuisineId());
  }
  @Test
  public void save_addsRestaurantToDatabase(){
    Restaurant newRestaurant = new Restaurant("503burgers", true, 1, "NE");
    newRestaurant.save();
    assertEquals(true, Restaurant.all().get(0).equals(newRestaurant));
  }

  @Test
  public void find_findsRestaurantById(){
    Restaurant newRestaurant = new Restaurant("503Burgers", true, 1, "NE");
    newRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(newRestaurant.getId());
    assertEquals(savedRestaurant, newRestaurant);
  }

  @Test
  public void updateName_updatesToNewName() {
    Restaurant newRestaurant = new Restaurant("503Burgers", true, 1, "NE");
    newRestaurant.save();
    newRestaurant.updateName("Bungalow");
    assertEquals("Bungalow", Restaurant.all().get(0).getRestaurantName());
  }

    @Test
    public void updateDogFriendly_updatesToNewDogFriendly(){
      Restaurant newRestaurant = new Restaurant("Bungalow", false, 1, "SW");
      newRestaurant.save();
      newRestaurant.updateDogFriendly(false);
      assertEquals(false, Restaurant.all().get(0).getDogFriendly());
  }


    @Test
      public void updateAre_updatesNewArea(){
      Restaurant newRestaurant = new Restaurant("Bungalow", false, 1, "SW");
      newRestaurant.save();
      newRestaurant.updateArea("SW");
      assertEquals("SW", Restaurant.all().get(0).getArea());
    }

    @Test
    public void updateCuisine_updatesToNewCuisine() {
    Restaurant newRestaurant = new Restaurant("Bungalow", false, 1, "SW");
    newRestaurant.save();
    newRestaurant.updateCuisine(3);
    assertEquals(3, Restaurant.all().get(0).getCuisineId());
  }

  @Test
  public void delete_cuisine(){
    Restaurant newRestaurant = new Restaurant("Bungalow", false, 1, "SW");
    newRestaurant.save();
    newRestaurant.delete();
    assertEquals(0, Restaurant.all().size());
  }

  // @Test
  // public void getId_returnsCorrectValue() {
  //   Restaurant newRestaurant = new Restaurant(1,"503Burgers", "NE", true, 1);
  //   newRestaurant.save();
  //   assertEquals(Restaurant.all().get(0).getId(), newRestaurant.getId());
  // }
 }
