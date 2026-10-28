// TODO Add a file header here

/**
 * This class contains methods to solve variants of the dinosaur monitoring coverage problem:
 * given a set of researchers with different monitoring capabilities,
 * determine which researchers to hire to maximize coverage subject to certain constraints.
 */
public class DinoMonitor {
  /**
     * Greedy monitoring approach: iteratively select the researcher that covers
     * the most previously-uncovered monitoring stations.
     *
     * This method works recursively: at each step, identify which remaining researcher
     * would increase the total coverage the most, add them to the hired list, and recurse.
     *
     * @param candidates available researchers not yet considered for hiring
     * @param hired currently hired researchers
     * @param hiresLeft number of additional researchers we can hire
     * @return the selected team of researchers (union of hired and greedily selected)
     */
  public static ResearcherList greedyMonitoring(ResearcherList candidates, ResearcherList hired, int hiresLeft ) {
    if (hiresLeft == 0)
       return hired;
    if (hired.isEmpty())
       return hired;

    
  
    int numStations = hired.get(0).getStationCapability().length;
    boolean[] stations = new boolean[numStations];
    int falseIndex = 0;

    for (int i = 0; i < candidates.size(); i++) {
       Researcher currentCandidate = candidates.get(i);
       boolean[] stationCoverage = candidates.getStationCapability();
       for (int j = 0; j < numStations; j++) {
         stations[j] = stationCoverage[j];
       }
    }

    if (falseIndex >= numStations) {
      return candidates;
    }
    
    
    Researcher chosenHire = null;
    int coverage = 0;
    for (int h = 0; h < hired.size(); h++) {
       Researcher currentHire = hired.get(h);
       int resultingCoverage = 0;
       boolean[] hireStations = currentHire.getStationCapability();
       for (int b = 0; b < stations.length; b++) {
         if (!stations[b] && hireStations[b]) {
	   resultingCoverage++;
	 }
       }

       if (resultingCoverage > coverage) {
         chosenHire = currentHire;
       }
    }

    hired = hired.copyAndRemoveResearcher(chosenHire).get(0);

         
    return greedyMonitoring(candidates, hired, hiresLeft - 1);
  }

  /**
     * Optimal monitoring approach: exhaustively search all possible teams
     * up to the hire limit to find the team covering the most unique stations.
     *
     * This method works recursively: try adding each remaining researcher to the hired set,
     * recurse, and keep track of the solution with the highest coverage.
     *
     * @param candidates available researchers not yet considered for hiring
     * @param hired currently hired researchers
     * @param hiresLeft number of additional researchers we can hire
     * @return the optimal team of researchers (union of hired and optimally selected)
     */
  public static ResearcherList optimalMonitoring(ResearcherList candidates, ResearcherList hired, int hiresLeft) {
    // TODO
    return null;
  }

  /**
     * Cost-aware monitoring: find the least expensive team that covers
     * at least the required number of monitoring stations.
     *
     * This method works recursively: try adding each remaining researcher to the hired set,
     * recurse until minimum coverage is achieved, and keep track of the solution with
     * the minimum total annual salary.
     *
     * @param candidates available researchers not yet considered for hiring
     * @param hired currently hired researchers
     * @param minStations minimum monitoring stations that must be covered
     * @return the lowest-cost team meeting the coverage requirement, or null if impossible
     */
  public static ResearcherList minCostMonitoring(ResearcherList candidates, ResearcherList hired, int minStations) {
    // TODO
    return null;
  }
}
