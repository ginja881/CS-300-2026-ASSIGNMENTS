public class DinoPark {
  // private list of safeties
  private ArrayList<SafetyCheckable> safeties;
  
  /**
   * Constructs new dino park class
   */
  public DinoPark() {
    this.safeties = new ArrayList<SafetyCheckable>();
  }
  /** 
   * Adds safety checkable object.
   *
   * @throws IllegalArgumentException if attraction is null
   * */
  public void addAttraction(SafetyCheckable attraction) {
    if (attraction == null) {
      throw new IllegalArgumentException("attraction is null");
    }

    safeties.add(attraction);
  }
  
  /**
   * Generates safety report
   */
  public void generateSafetyReport() {
    System.out.println("=== SAFETY REPORT ===");
    
    for (int i = 0; i < safeties.size(); i++) {
      SafetyCheckable currentCheckable = safeties.get(i);
      System.out.println(currentCheckable);
      int humanViolations = currentCheckable.countHumanSafetyViolations();
      int dinoViolations = currentCheckable.countDinoSafetyViolations();

      System.out.println("Human Safety Violations: " + humanViolations);
      System.out.println("Dinosaur Safety Violations: " + dinoViolations);
    }
  }


};
