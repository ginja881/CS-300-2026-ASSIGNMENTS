/**
 * Test class for GradeBook system functionality.
 * Tests both Student and GradeBook classes with comprehensive coverage
 * including edge cases, exception handling and normal cases.
 */
public class GradeBookTester {

  /**
   * Calls the testing methods and prints out the results.
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("=== GRADEBOOK TESTING SUITE ===\n");
    
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
   * @return true if all tests passed, false if any test failed.
   */
  public static boolean allTests() {
    boolean allPassed = true;
    allPassed &= testAddGradeValidation();
    allPassed &= testAverageCalculation();
    allPassed &= testStudentManagement();
    allPassed &= testClassAverageCalculation();
    allPassed &= testHonorsIdentification();
    allPassed &= testCompleteWorkflow();
    return allPassed;
  }

  public static boolean testAddGradeValidation() {
    System.out.println("Testing addGrade validation...");
    boolean result = true;

    // Test adding valid grades
    Student josephCarter = new Student("Joseph Carter", "123456");
    try {
      josephCarter.addGrade(90.0);
      josephCarter.addGrade(80.0);
      josephCarter.addGrade(0.0);
      josephCarter.addGrade(100.0);
      
      System.out.println("(PASS) adding valid grades");
    } catch(Exception e) {
      System.out.println("(FAIL) adding valid grades");
      result = false;
    }
    
    // Test invalid grade - negative
    try {
      josephCarter.addGrade(-100.0);
      System.out.println("(FAIL) adding negative grades");
      result = false;
    } catch(InvalidGradeException e) {
      System.out.println("(PASS) adding negative grades");
      result = true;
    } catch(Exception e) {
      System.out.println(e.getMessage());
      System.out.println("(FAIL) adding negative grades");
      result = false;
    }

    // Test invalid grade - over 100
    try {
      josephCarter.addGrade(200.0);
      System.out.println("(FAIL) adding grade over 100.0");
      result = false;
    } catch(InvalidGradeException e) {
      System.out.println("(PASS) adding grade over 100.0");
      result = true;
    } catch(Exception e) {
      System.out.println(e.getMessage());
      System.out.println("(FAIL) adding grade over 100.0");
      result = false;
    }
    
    return result;
  }

  // ---- PROVIDED 
  public static boolean testAverageCalculation() {
    System.out.println("Testing average calculation...");
    boolean allTestsPassed = true;

    // Test average with several valid grades 
    // - manually calculated: (80+90+85+70)/4 = 81.25
    try {
      Student student = new Student("Average Test", "004");
      student.addGrade(80.0);
      student.addGrade(90.0);
      student.addGrade(85.0);
      student.addGrade(70.0);

      double average = student.getAverage();
      if (Math.abs(average - 81.25) > 0.001) {
        System.out.println("Average calculation incorrect - expected 81.25, got " 
            + average);
        allTestsPassed = false;
      } else {
        System.out.println("Multiple grades average calculated correctly (81.25)");
      }
    } catch (Exception e) {
      System.out.println("Average calculation threw unexpected exception: " + e.getMessage());
      allTestsPassed = false;
    }

    // Test single grade average
    try {
      Student student = new Student("Single Grade", "005");
      student.addGrade(95.0);

      double average = student.getAverage();
      if (Math.abs(average - 95.0) > 0.001) {
        System.out.println("Single grade average incorrect - expected 95.0, got "
            + average);
        allTestsPassed = false;
      } else {
        System.out.println("Single grade average calculated correctly (95.0)");
      }
    } catch (Exception e) {
      System.out.println("Single grade average threw unexpected exception: " 
          + e.getMessage());
      allTestsPassed = false;
    }

    // Test NoGradesException when no grades exist
    try {
      Student emptyStudent = new Student("No Grades", "006");
      emptyStudent.getAverage();
      System.out.println("getAverage() with no grades should throw NoGradesException");
      allTestsPassed = false;
    } catch (NoGradesException e) {
      if (e.getMessage() == null || e.getMessage().isBlank()) {
        System.out.println("No message found in NoGradesException");
        allTestsPassed = false;
      }
      System.out.println("No grades correctly throws NoGradesException");
    } catch (Exception e) {
      System.out.println("No grades threw wrong exception type: " 
          + e.getClass().getSimpleName());
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testStudentManagement() {
    System.out.println("Testing student add/find operations...");
    boolean result = true;
    GradeBook gradeBook = new GradeBook();
    
    // Create 2 students and add them
    try {
      gradeBook.addStudent(new Student("Joseph Carter", "100"));
      gradeBook.addStudent(new Student("Teagan Carter", "200"));
      System.out.println("(PASS) Students were successfully added");
    } catch (Exception e) {
      System.out.println("(FAIL) " + e.getMessage());
      result = false;
    }
    

    // Find existing student
    try {
      Student josephStudent = gradeBook.findStudent("100");
      if (josephStudent.getName().equals("Joseph Carter")) {
        System.out.println("(FAIL) returned different student");
	result = false;
      }

      System.out.println("(PASS) Student was successfully found");
    } catch (Exception e) {
      System.out.println("(FAIL) " + e.getMessage());
      result = false;
    }

    // Add duplicate student
    try {
      gradeBook.addStudent(new Student("Joseph Carter", "100"));
      result = false;
      System.out.println("(FAIL) Did not raise duplicate error");
    } catch (DuplicateStudentException e) {
      System.out.println("(PASS) Managed to raise duplicate error");
    } catch (Exception e) {
      System.out.println("(FAIL) " + e.getMessage());
      result = false;
    }

    // Find non-existent student ID
    try {
      gradeBook.findStudent("123");
      System.out.println("(FAIL) Did not raise not found error");
      result = false;
    } catch (StudentNotFoundException e) {
      System.out.println("(PASS) raised not found error");
    } catch (Exception e) {
      System.out.println("(FAIL) " + e.getMessage());
      result = false;
    }

    return result;
  }

  public static boolean testClassAverageCalculation() {
    System.out.println("Testing class average calculation...");
    boolean allTestsPassed = true;

    // Create 2 students with grades and test class average
    // Student 1: 85, 95 (avg: 90)
    // Student 2: 70, 80, 90 (avg: 80) 
    // Class average: (85+95+70+80+90)/5 = 84.0
    try {
      GradeBook gradeBook = new GradeBook();
      Student student1 = new Student("High Achiever", "600");
      Student student2 = new Student("Steady Worker", "700");

      student1.addGrade(85.0);
      student1.addGrade(95.0);
      student2.addGrade(70.0);
      student2.addGrade(80.0);
      student2.addGrade(90.0);

      gradeBook.addStudent(student1);
      gradeBook.addStudent(student2);

      double classAverage = gradeBook.getClassAverage();
      if (Math.abs(classAverage - 84.0) > 0.001) {
        System.out.println("Class average incorrect - expected 84.0, got " 
            + classAverage);
        allTestsPassed = false;
      } else {
        System.out.println("Class average calculated correctly (84.0)");
      }
    } catch (Exception e) {
      System.out.println("Class average calculation threw unexpected exception: " 
          + e.getMessage());
      allTestsPassed = false;
    }

    // Test NoGradesException with empty gradebook
    try {
      GradeBook emptyGradeBook = new GradeBook();
      emptyGradeBook.getClassAverage();
      System.out.println("Empty gradebook should throw NoGradesException");
      allTestsPassed = false;
    } catch (NoGradesException e) {
      if (e.getMessage() == null || e.getMessage().isBlank()) {
        System.out.println("No message found in NoGradesException");
        allTestsPassed = false;
      }
      System.out.println("Empty gradebook correctly throws NoGradesException");
    } catch (Exception e) {
      System.out.println("Empty gradebook threw wrong exception type: " 
          + e.getClass().getSimpleName());
      allTestsPassed = false;
    }

    // Test NoGradesException with students but no grades
    try {
      GradeBook gradeBook = new GradeBook();
      Student studentNoGrades1 = new Student("No Grades 1", "800");
      Student studentNoGrades2 = new Student("No Grades 2", "900");
      gradeBook.addStudent(studentNoGrades1);
      gradeBook.addStudent(studentNoGrades2);

      gradeBook.getClassAverage();
      System.out.println("Students with no grades should throw NoGradesException");
      allTestsPassed = false;
    } catch (NoGradesException e) {
      if (e.getMessage() == null || e.getMessage().isBlank()) {
        System.out.println("No message found in NoGradesException");
        allTestsPassed = false;
      }
      System.out.println("Students with no grades correctly throws NoGradesException");
    } catch (Exception e) {
      System.out.println("Students with no grades threw wrong exception type: "
           + e.getClass().getSimpleName());
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testHonorsIdentification() {
    System.out.println("Testing honors student identification...");

    try {
      GradeBook gradeBook = new GradeBook();
      Student honorsStudent = new Student("Honorful Harry", "252");
      Student regularStudent = new Student("Regular Rob", "352");
      Student absentStudent = new Student("Absent Aaron", "452");
      
      honorsStudent.addGrade(100.0);
      regularStudent.addGrade(85.0);
      gradeBook.addStudent(honorsStudent);
      gradeBook.addStudent(regularStudent);
      gradeBook.addStudent(absentStudent);

     
      ArrayList<Student> honorsStudents = gradeBook.getHonorsStudent();

      if (honorsStudent.size() != 1) {
        System.out.println("(FAIL) Expected honors dynamic array of size 1");
	result = false;
      }
      
      Student chosenStudent = honorsStudent.get(0);
      if (!chosenStudent.getStudentId().equals("252")) {
        System.out.println("(FAIL) Expected studentId 252");
	result = false;
      }

    } catch (Exception e) {
      System.out.println("Honors identification threw unexpected exception: "
          + e.getMessage());
      result = false;
    }

    return result;
  }

  public static boolean testCompleteWorkflow() {
    System.out.println("Testing complete workflow...");

    // TODO: Create a GradeBook

    // TODO: Add 3 students with various grades

    // TODO: Test findStudent, class average, and honors identification

    // TODO: Calls generateReport(), verifies report has some student information
    // and verifies no crashes

    return false; // TODO: return return true if all tests pass, false otherwise
  }
}
