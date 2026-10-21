public class TRex extends Dinosaur {
  public TRex(String id, String name, int age) {
    super(id, name, age, FoodType.MEAT, 20.0);
  }
  
  public String roar() {
     return "ROOOAAAR!";
  }
  
  public boolean canCoexistWith(Dinosaur otherDinosaur) {
    if (otherDinosaur == this) {
      return true;
    }
    return false;
  }
  
};
