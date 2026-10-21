public class Spinosaurus extends Dinosaur {
  public Spinosaurus(String id, String name, int age) {
    super(id, name, age, FoodType.MEAT, 16.0);
  }

  public String roar() {
     return "GRRRRR!";
  }

  public boolean canCoexistWith(Dinosaur otherDinosaur) {
    if (otherDinosaur instanceof Spinosaurus) {
      return true;
    } else if (otherDinosaur == this) {
      return true;
    } if (otherDinosaur.canCoexistWith(this)) {
      return true;
    }
    return false;
  }

};
