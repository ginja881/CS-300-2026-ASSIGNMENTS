import java.util.ArrayList;

public class Student {

  // Grade scale constants
  public static final double A_THRESHOLD = 90.0;
  public static final double B_THRESHOLD = 80.0;
  public static final double C_THRESHOLD = 70.0;
  public static final double D_THRESHOLD = 60.0;
  public static final double MIN_GRADE = 0.0;
  public static final double MAX_GRADE = 100.0;

  private String name;
  private String studentId;
  private ArrayList<Double> grades;

  /**
   * Student constructor
   * @param name initial name
   * @param studentId initial student ID
   */
  public Student(String name, String studentId) {
    this.name = name;
    this.studentId = studentId;
    this.grades = new ArrayList<Double>();
  }
  
  /**
   * Computing mean of all grades
   * @return arithmetic mean of all grades
   * @throws NoGradesException no grades are recorded in grades 
   */
  public double getAverage() throws NoGradesException {
    // Check grades.size()
    if (grades.size() == 0) {
      throw new NoGradesException("No grades for student " + this.studentId);
    }

    // Compute mean
    double gradeSum = 0.0;
    for (int i = 0; i < grades.size(); i++) {
      gradeSum += grades.get(i);
    }

    return gradeSum / grades.size();
  }
  
  /**
   * Adds grade to grades dynamic array
   * @param grade new grade
   * @throws InvalidGradeException when grade 
   * outside of [MIN_GRADE, MAX_GRADE]
   */
  public void addGrade(Double grade) throws InvalidGradeException {
    if (grade < MIN_GRADE || grade > MAX_GRADE) {
      throw new InvalidGradeException("Invalid grade");
    }

    grades.add(grade);

  }
  /**
   * grabs the letter grade from GPA
   * @return String representing letter grade
   * @throws NoGradesException if no grades
   */
  public String getLetterGrade() throws NoGradesException {
    double average = getAverage();
    
    // Check if in A threshold and then fall through for subsequent letters
    if (average >= A_THRESHOLD) {
      return "A";
    } else if (average >= B_THRESHOLD) {
      return "B";
    } else if (average >= C_THRESHOLD) {
      return "C";
    } else if (average >= D_THRESHOLD) {
      return "D";
    }
    
    return "F";
  }
  
  public String getName() {
    return name;
  }

  public String getStudentId() {
    return studentId;
  }

  public ArrayList<Double> getGrades() {
    // Create a defensive copy to maintain encapsulation
    return new ArrayList<>(grades);
  }

  /**
   * Returns a string representation of the student including name, ID, average, and letter grade.
   * If no grades are recorded, indicates that no grades are available.
   *
   * @return a formatted string containing student information
   */
  @Override public String toString() {
    try {
      String formattedString = String.format(
        "%s (ID: %s) - Average: %.2f (%s)",
        name,
        studentId,
        getAverage(),
        getLetterGrade()
      );
      return formattedString;
    } catch (NoGradesException e) {
      return String.format("%s (ID: %s) - No grades recorded", name, studentId);
    }
  }
}