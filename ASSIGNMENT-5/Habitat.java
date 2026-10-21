import java.util.ArrayList;

/**
 * Represents a specific physical enclosure or living environment within the park.
 *
 * A Habitat tracks its size, the staff assigned to it, and a collection of
 * dinosaurs currently residing within it. It implements SafetyCheckable
 * to provide audits on space requirements and species compatibility.
 *
 */
public class Habitat implements SafetyCheckable {
  private String name;
  private double area;
  private int parkRangers;

  // A private list to store the Dinosaurs currently in this habitat.
  private ArrayList<Dinosaur> dinosaurs;

  /**
   * Constructs a new Habitat with the specified name, size, and staffing level.
   * @param name The name of the habitat; must not be null or blank.
   * @param area The total square footage/meters; must be greater than 0.
   * @param parkRangers The number of rangers assigned; must be non-negative.
   * @throws IllegalArgumentException if validation rules are violated.
   */
  public Habitat(String name, double area, int parkRangers) {
    if (name == null) {
      throw new IllegalArgumentException("Habitat name cannot be null");
    } else if (name.isBlank()) {
      throw new IllegalArgumentException("Habitat name is blank");
    }

    if (area <= 0) {
      throw new IllegalArgumentException("Area has to be positive");
    }

    if (parkRangers < 0) {
      throw new IllegalArgumentException("park rangers must be non-negative");
    }

    this.dinosaurs = new ArrayList<Dinosaur>();
    this.name = name;
    this.area = area;
    this.parkRangers = parkRangers;
  }

  // --- Getters ---

  /**
   * Retrieves the name of the habitat
   * @return The name of the habitat.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Retrieves the area of the habitat
   * @return The total area of the habitat.
   */
  public double getArea() {
    return this.area;
  }

  /**
   * Retrieves the number of park rangers in this habitat
   * @return The number of rangers assigned to this habitat.
   */
  public int getParkRangers() {
    return this.parkRangers;
  }

  /**
   * Returns a copy of the list of dinosaurs in this habitat.
   * @return A new ArrayList containing the resident dinosaurs.
   */
  public ArrayList<Dinosaur> getDinosaurs() {
    return new ArrayList<Dinosaur>(dinosaurs);
  }

  /**
   * Adds a dinosaur to this habitat.
   * @param dinosaur The dinosaur to add.
   * @throws IllegalArgumentException if the dinosaur is null.
   */
  public void addDinosaur(Dinosaur dinosaur) {
    if (dinosaur == null) {
      throw new IllegalArgumentException("dinosaur cannot be null");
    }
    dinosaurs.add(dinosaur);
  }

  /**
   * Removes a dinosaur from the habitat by its unique ID.
   * @param dinoId The ID of the dinosaur to remove.
   * @return The removed Dinosaur.
   * @throws IllegalArgumentException if dinoId is null or blank.
   * @throws DinoNotFoundException if no dinosaur with that ID exists in this habitat.
   */
  public Dinosaur removeDinosaur(String dinoId) throws DinoNotFoundException {
    if (dinoId == null) {
      throw new IllegalArgumentException("dinoID cannot be null");
    } else if (dinoId.isBlank()) {
      throw new IllegalArgumentException("dinoId cannot be blank");
    }

    Dinosaur chosenDinosaur = null;
    for (int i = 0; i < dinosaurs.size(); i++) {
      Dinosaur currentDinosaur = dinosaurs.get(i);
      if (currentDinosaur.getId().equals(dinoId)) {
        chosenDinosaur = currentDinosaur;
      }
    }

    if (chosenDinosaur == null) {
      throw new DinoNotFoundException("Dino not found");
    }

    dinosaurs.remove(i);
    
    return chosenDinosaur;
  }

  /**
   * Evaluates human safety based on staffing ratios.
   *
   * Violations occur if:
   * 1. The area per ranger is too high (e.g., area > rangers * 10).
   * 2. There are more dinosaurs than rangers.
   *
   * @return The total count of human safety violations.
   */
  @Override
  public int countHumanSafetyViolations() {
    int safetyCount = 0;
    if (this.area > this.parkRangers * 10) {
      safetyCount++;
    } 

    if (this.dinosaurs.size() > this.parkRangers) {
      safetyCount++;
    }
    return safetyCount;
  }

  /**
   * Evaluates dinosaur safety based on space and social compatibility.
   *
   * Violations occur if:
   * 1. The sum of all dinosaurs' preferredArea exceeds the habitat's area.
   * 2. Any two dinosaurs in the habitat cannot coexist (check every pair).
   *
   * @return The total count of dinosaur safety violations.
   */
  @Override
  public int countDinoSafetyViolations() {
    int safetyViolations = 0;

    double areaSum = 0.0;
    boolean cannotCoexist = false;
    for (int i = 0; i < dinosaurs.size(); i++) {
      Dinosaur currentDinosaur = dinosaurs.get(i);
      areaSum += currentDinosaur.getPreferredArea();

      for (int j = 0; j < dinosaurs.size(); j++) {
        Dinosaur otherDinosaur = dinosaurs.get(j);
	if (!currentDinosaur.canCoexistWith(otherDinosaur)) {
	  cannotCoexist = true;
	}
      }
    }

    if (cannotCoexist) {
      safetyViolations++;
    }
    
    if (areaSum > this.area) {
      safetyViolations++;
    }

    
    return safetyViolations;
  }

  /**
   * Returns a detailed string representing the habitat and all its residents.
   * @return A formatted multi-line string.
   */
  @Override
  public String toString() {
    StringBuilder habitatString = new StringBuilder(String.format(
        "Habitat: %s, area=%f m^2, parkRangers=%d\n",
        this.name,
        this.area,
        this.parkRangers
    ));
    for (Dinosaur dinosaur : this.dinosaurs) {
      habitatString.append(String.format("%s\n", dinosaur.toString()));
    }
    return habitatString.toString();
  }
}
