/**
 * Test class for DinoPark system functionality.
 * Tests DinoPark, Habitat, and LabTour classes with comprehensive coverage
 * including edge cases, exception handling and normal cases.
 */
public class DinoParkTester {

  /**
   * Calls the testing methods and prints out the results.
   *
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("=== DINOPARK TESTING SUITE ===\n");

    boolean allPassed = allTests();

    System.out.println("\n=== FINAL TEST RESULTS ===");
    if (allPassed) {
      System.out.println("ALL TESTS PASSED!");
    } else {
      System.out.println("Some tests failed. Review output above.");
    }
  }

  /**
   * Calls all the individual testing methods.
   *
   * @return true if all tests passed, false if any test failed.
   */
  public static boolean allTests() {
    boolean allPassed = true;
    allPassed &= testLabTourSafetyChecks();
    allPassed &= testVelociraptorCoexist();
    allPassed &= testTRexCoexist();
    allPassed &= testTriceratopsCoexist();
    allPassed &= testSpinosaurusCoexist();
    allPassed &= testHabitatAddValidation();
    allPassed &= testHabitatRemoveValidation();
    allPassed &= testHabitatHumanSafetyChecks();
    allPassed &= testHabitatDinoSafetyChecks();
    allPassed &= testCompleteWorkflow();
    return allPassed;
  }

  public static boolean testLabTourSafetyChecks() {
    System.out.println("Testing LabTour safety checks...");
    boolean allTestsPassed = true;

    // PROVIDED SCENARIO (Normal Case): Test with total people UNDER capacity
    // Total people is 9, capacity is 10. Safety violations should be 0
    LabTour labTourUnderCapacity = new LabTour("Mr. DNA", 4);
    labTourUnderCapacity.addExperiment(new Experiment("Hatching raptors", 3));
    labTourUnderCapacity.addExperiment(new Experiment("Genome splicing", 2));

    int dinoViolationsUnderCapacity = labTourUnderCapacity.countDinoSafetyViolations();
    int humanViolationsUnderCapacity = labTourUnderCapacity.countHumanSafetyViolations();

    // Note that dino safety violations should *always* be zero,
    // since there should not be any dinos in the lab.
    if (dinoViolationsUnderCapacity != 0) {
      System.out.println("LabTour countDinoSafetyViolations incorrect for case: "
          + "Total people under capacity.\n"
          + "Expected: 0\n"
          + "Actual: " + dinoViolationsUnderCapacity);
      allTestsPassed = false;
    }
    if (humanViolationsUnderCapacity != 0) {
      System.out.println("LabTour countHumanSafetyViolations incorrect for case: "
          + "Total people under capacity.\n"
          + "Expected: 0\n"
          + "Actual: " + humanViolationsUnderCapacity);
      allTestsPassed = false;
    }

    System.out.println("Testing over capacity");
    LabTour labTourOverCapacity = new LabTour("Mr. Lab", 8);
    labTourOverCapacity.addExperiment(new Experiment("Testing dinosaurs", 1));
    labTourOverCapacity.addExperiment(new Experiment("Testing something else", 3));
    
    int overCapacityViolations = labTourOverCapacity.countHumanSafetyViolations();

    if (overCapacityViolations == 1) {
      System.out.println("TEST PASSED");
    } else {
      System.out.println("TEST FAILED, EXPECTED 1 BUT GOT " + overCapacityViolations);
      allTestsPassed = false;
    }
    
    
   
    System.out.println("Testing equal capacity");
    LabTour labTourEqual = new LabTour("Mr. Equal", 9);
    labTourEqual.addExperiment(new Experiment("Hanging out", 1));

    int equalCapacityViolations = labTourEqual.countHumanSafetyViolations();
    if (equalCapacityViolations == 0) {
      System.out.println("TEST PASSED");
    } else {
      System.out.println("TEST FAILED, EXPECTED 0 BUT GOT " + equalCapacityViolations);
      allTestsPassed = false;
    }
    

    System.out.println();
    return allTestsPassed;
  }

  // PROVIDED example test method for Velociraptor
  public static boolean testVelociraptorCoexist() {
    System.out.println("Testing Velociraptor canCoexistWith...");
    boolean allTestsPassed = true;

    Velociraptor velociraptor = new Velociraptor("raptor1", "Alan", 8);

    // PROVIDED: Test where otherDinosaur has food type MEAT. Should return true
    Velociraptor meatDinosaur = new Velociraptor("raptor2", "Radia", 12);
    boolean velociraptorCoexistWithMeat = velociraptor.canCoexistWith(meatDinosaur);
    if (!velociraptorCoexistWithMeat) {
      System.out.println("Velociraptor canCoexistWith incorrect for case: "
          + "Other Dinosaur has food type MEAT.\n"
          + "Expected: true\n"
          + "Actual: false");
      allTestsPassed = false;
    }

    // PROVIDED: Test where otherDinosaur has food type PLANTS. Should return false
    Brachiosaurus plantsDinosaur = new Brachiosaurus("longneck1", "Littlefoot", 4);
    boolean velociraptorCoexistWithPlants = velociraptor.canCoexistWith(plantsDinosaur);
    if (velociraptorCoexistWithPlants) {
      System.out.println("Velociraptor canCoexistWith incorrect for case: "
          + "Other Dinosaur has food type PLANTS.\n"
          + "Expected: false\n"
          + "Actual: true");
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testTRexCoexist() {
    System.out.println("Testing TRex canCoexistWith...");
    boolean allTestsPassed = true;

    
    TRex trex = new TRex("trex1", "Sue", 24);
    
    Velociraptor velociraptor = new Velociraptor("raptor0", "mike", 10);

    if (trex.canCoexistWith(velociraptor)) {
      System.out.println("TEST FAILED, EXPETCED TREX TO NOT EXIST WITH VELOCIRAPTOR");
      allTestsPassed = false;
    }
    
    // PROVIDED SCENARIO: Test calling canCoexistWith on itself. Should return true
    // Uncomment once you have added the TRex class:
   
    boolean trexCoexistWithItself = trex.canCoexistWith(trex);
    if (!trexCoexistWithItself) {
      System.out.println("TRex canCoexistWith incorrect for case: "
                    + "Can coexist with itself.\n"
                    + "Expected: true\n"
                    + "Actual: false");
      allTestsPassed = false;
    }
       

    
    System.out.println();
    return allTestsPassed;
  }

  public static boolean testTriceratopsCoexist() {
    System.out.println("Testing Triceratops canCoexistWith...");
    boolean allTestsPassed = true;
    
    Triceratops tri = new Triceratops("tri4", "trinity", 4);
    TRex trex = new TRex("trex2", "Rob", 2);

    if (tri.canCoexistWith(trex)) {
      System.out.println("TEST FAIL, triceratops cannot exist with TRex");
      allTestsPassed = false;
    } else {
      System.out.println("TEST PASS");
    }

   
    Triceratops tri2 = new Triceratops("tri5", "trinitina", 5);
    if (tri2.canCoexistWith(tri2)) {
      System.out.println("TEST PASS"); 
    } else {
      System.out.println("TEST FAIL, triceratops must exist with triceratops");
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testSpinosaurusCoexist() {
    System.out.println("Testing Spinosaurus canCoexistWith...");
    boolean allTestsPassed = true;
    Spinosaurus spino = new Spinosaurus("spino1", "spipho", 10);

    if (spino.canCoexistWith(spino)) {
      System.out.println("TEST PASS");
    } else {
      System.out.println("TEST FAIL, SPINO MUST EXIST WITH SELF");
      allTestsPassed = false;
    }

    Spinosaurus spino1 = new Spinosaurus("spino2", "siphon", 11);
    if (spino.canCoexistWith(spino1)) {
      System.out.println("TEST PASS");
    } else {
      System.out.println("TEST FAIL, SPINO MUST EXIST WITH OTHER SPINO");
      allTestsPassed = false;
    }

    Triceratops tri = new Triceratops("tri4", "trinity", 10);
    if (spino.canCoexistWith(tri)) {
      System.out.println("TEST PASS");
    } else {
      System.out.println("TEST FAIL, SPINO MUST EXIST WITH TRICERATOPS");
      allTestsPassed = false;
    }

    
    TRex trex  = new TRex("trex1", "trexan", 10);
    if (spino.canCoexistWith(trex)) {
      System.out.println("TEST FAIL, SPINO CAN'T EXIST WITH TREX");
      allTestsPassed = false;
    } else {
      System.out.println("TEST PASS");
    }
    
    System.out.println();
    return allTestsPassed;
  }

  public static boolean testHabitatAddValidation() {
    System.out.println("Testing Habitat addDinosaur validation...");
    boolean allTestsPassed = true;

    Habitat habitat = new Habitat("habitat1", 1, 1);

    // PROVIDED SCENARIO (Normal Case): Test adding a valid dinosaur to a habitat
    try {
      // Code that should NOT throw exception
      habitat.addDinosaur(new Velociraptor("raptor1", "Alan", 2));

      // Check that the dino was actually added
      if (habitat.getDinosaurs().size() != 1) {
        allTestsPassed = false;
      } else if (!habitat.getDinosaurs().get(0).getId().equals("raptor1")) {
        allTestsPassed = false;
      }
    } catch (Exception e) {
      // Should not throw any exception
      System.out.println("Habitat addDinosaur with a valid dinosaur threw exception: "
          + e.getMessage());
      allTestsPassed = false;
    }
    
    try {
      habitat.addDinosaur(null);
      System.out.println("TEST FAIL, MUST RAISE ERROR");
      allTestsPassed = false;
    } catch (IllegalArgumentException e) {
      System.out.println("TEST PASS");
    } catch (Exception e) {
      System.out.println("TEST FAIL, OTHER ERROR");
      allTestsPassed = false;
    }
    

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testHabitatRemoveValidation() {
    System.out.println("Testing Habitat removeDinosaur validation...");
    boolean allTestsPassed = true;

    // TODO: Normal Case: Test removing a valid dinosaur from the habitat

    // TODO: Test trying to remove null. Should throw IllegalArgumentException

    // TODO: Test trying to remove dino that doesn't exist. Should throw DinoNotFoundException

    // TODO: remove these two lines when you complete this tester method
    System.out.println("!! This tester method has not been completed !!");
    allTestsPassed = false;

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testHabitatHumanSafetyChecks() {
    System.out.println("Testing Habitat human safety checks...");
    boolean allTestsPassed = true;

    // PROVIDED SCENARIO (Normal Case): Test with sufficient rangers. Should return 0
    Habitat habitatEnoughRangers = new Habitat("habitat1", 4, 3);
    habitatEnoughRangers.addDinosaur(new Velociraptor("raptor1", "Alan", 2));
    habitatEnoughRangers.addDinosaur(new Velociraptor("raptor2", "Ellie", 2));
    habitatEnoughRangers.addDinosaur(new Velociraptor("raptor3", "Ian", 2));

    int violationsEnoughRangers = habitatEnoughRangers.countHumanSafetyViolations();

    if (violationsEnoughRangers != 0) {
      System.out.println("Habitat countHumanSafetyViolations incorrect for case: "
          + "Enough rangers for area and dinosaurs.\n"
          + "Expected: 0\n"
          + "Actual: " + violationsEnoughRangers);
      allTestsPassed = false;
    }

    // PROVIDED SCENARIO (Edge Case): Test with enough rangers for the number of dinosaurs,
    // but not enough for area size
    Habitat habitatAreaTooLarge = new Habitat("habitat2", 40, 3);

    int violationsAreaTooLarge = habitatAreaTooLarge.countHumanSafetyViolations();

    if (violationsAreaTooLarge != 1) {
      System.out.println("Habitat countHumanSafetyViolations incorrect for case: "
          + "Not too many dinosaurs but area too large.\n"
          + "Expected: 1\n"
          + "Actual: " + violationsAreaTooLarge);
      allTestsPassed = false;
    }

    // TODO: Test with enough rangers for habitat area, but not enough for the number of dinosaurs

    // TODO: Test with not enough rangers for habitat area AND not enough for the number of dinosaurs

    // TODO: remove these two lines when you complete this tester method
    System.out.println("!! This tester method has not been completed !!");
    allTestsPassed = false;

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testHabitatDinoSafetyChecks() {
    System.out.println("Testing Habitat Dinosaur safety checks...");
    boolean allTestsPassed = true;

    // PROVIDED SCENARIO (Normal Case): Test with sufficient area and all dinos can coexist.
    // Should return 0
    Habitat habitatEnoughAreaAndCoexist = new Habitat("habitat1", 16, 0);
    habitatEnoughAreaAndCoexist.addDinosaur(new Velociraptor("raptor1", "Alan", 2));
    habitatEnoughAreaAndCoexist.addDinosaur(new Velociraptor("raptor2", "Ellie", 2));

    int dinoSafetyEnoughAreaAndCoexist = habitatEnoughAreaAndCoexist.countDinoSafetyViolations();

    if (dinoSafetyEnoughAreaAndCoexist != 0) {
      System.out.println("Habitat countDinoSafetyViolations incorrect for case: "
          + "Enough area and all dinos can coexist.\n"
          + "Expected: 0\n"
          + "Actual: " + dinoSafetyEnoughAreaAndCoexist);
      allTestsPassed = false;
    }

    // TODO: Test with all dinos can coexist but not enough area

    // TODO: Test with enough area but not all dinos can coexist

    // TODO: Test where not enough area AND not all dinos can coexist

    // TODO: remove these two lines when you complete this tester method
    System.out.println("!! This tester method has not been completed !!");
    allTestsPassed = false;

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testCompleteWorkflow() {
    System.out.println("Testing complete DinoPark workflow integration...");
    boolean allTestsPassed = true;

    try {
      // TODO: Create a DinoPark

      // TODO: Create a lab tour with 2 experiments

      // TODO: Create 2 habitats with a variety of dinosaurs

      // TODO: Add your lab tour and 2 habitats to the park

      // TODO: Call generateSafetyReport and verify no crashes

    } catch (Exception e) {
      System.out.println("Complete workflow threw exception: "
          + e.getMessage());
      allTestsPassed = false;
    }

    // TODO: remove these two lines when you complete this tester method
    System.out.println("!! This tester method has not been completed !!");
    allTestsPassed = false;

    return allTestsPassed;
  }
}
