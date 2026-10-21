public class Triceratops extends Dinosaur {

  public Triceratops(String id, String name, int age) {
    super(id, name, age, FoodType.PLANT, 6.0);
  }

  public String roar() {
     return "HMMMPH!";
  }

  public boolean canCoexistWith(Dinosaur otherDinosaur) {
    FoodType otherFoodType = otherDinosaur.getFoodType();

    if (otherFoodType == MEAT) {
      return false;
    }
    return true;
  }

  
};
