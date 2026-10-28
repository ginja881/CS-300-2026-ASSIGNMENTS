// TODO Add a file header here

import java.util.ArrayList;
import java.util.Random;

/**
 * Test harness for the Dinosaur Monitoring implementation.
 */
public class DinoMonitorTesting {

  /**
   * Main method to run this tester class.
   *
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.print("Test testGreedyMonitoring: ");
    boolean greedyPass = testGreedyMonitoring();
    System.out.println(greedyPass ? "Passed" : "Failed");

    System.out.print("\n\nTest testOptimalMonitoring: ");
    boolean optPass = testOptimalMonitoring();
    System.out.println(optPass ? "Passed" : "Failed");

    System.out.print("\n\nTest testMinCostMonitoring: ");
    boolean minCostPass = testMinCostMonitoring();
    System.out.println(minCostPass ? "Passed" : "Failed");

    boolean allPass = greedyPass && optPass && minCostPass;

    System.out.println("-----------------------------------------------");
    System.out.println("Overall: " + (allPass ? "Pass" : "Failed!"));
  }


  ////////////////////////////// GREEDY MONITORING TESTS ///////////////////////////////////
  public static boolean testGreedyMonitoring() {
    return greedyMonitoringBaseTest() && 
        greedyMonitoringRecursiveTest();
  }

  public static boolean greedyMonitoringBaseTest() {
    // PROVIDED as a model for how to write tests with the provided utilities

    // Create a list of potential candidates with various station coverage
    ResearcherList candidates =
        DinoMonitorTestingUtilities.makeResearcherList(new boolean[][] {
          {true, false, false, false},    // Researcher 0: covers station 0
          {false, true, true, true},      // Researcher 1: covers stations 1,2,3
          {true, false, true, false},     // Researcher 2: covers stations 0,2
          {true, true, false, false}},    // Researcher 3: covers stations 0,1
          new int[]{20, 25, 30, 15});     // Researcher salaries: $20k, $25k, $30k, $15k

    // For the base case, we expect no one to be added to the list
    ResearcherList expectedSolution = new ResearcherList();

    // Call the method with hiresLeft at 0 (base case)
    ResearcherList actualSolution =
        DinoMonitor.greedyMonitoring(candidates, new ResearcherList(), 0);

    // Use the testing utilities provided to compare the contents of these two lists
    return DinoMonitorTestingUtilities.compareResearcherLists(expectedSolution, actualSolution);
  }

  public static boolean greedyMonitoringRecursiveTest() {
    // TODO
    return false;
  }

  ////////////////////////////// OPTIMAL MONITORING TESTS //////////////////////////////////
  public static boolean testOptimalMonitoring() {
    return optimalMonitoringBaseTest() && 
        optimalMonitoringRecursiveTest() && 
        optimalMonitoringFuzzTest();
  }

  public static boolean optimalMonitoringBaseTest() {
    // TODO
    return false;
  }

  public static boolean optimalMonitoringRecursiveTest() {
    // TODO
    return false;
  }

  public static boolean optimalMonitoringFuzzTest() {
    // TODO
    return false;
  }

  ////////////////////////////// MIN COST MONITORING TESTS //////////////////////////////////
  public static boolean testMinCostMonitoring() {
    return minCostMonitoringBaseTest() &&
        minCostMonitoringRecursiveTest() &&
        minCostMonitoringFuzzTest();
  }

  public static boolean minCostMonitoringBaseTest() {
    // TODO
    return false;
  }

  public static boolean minCostMonitoringRecursiveTest() {
    // TODO
    return false;
  }

  public static boolean minCostMonitoringFuzzTest() {
    // TODO
    return false;
  }
}
