import java.util.ArrayList;

public class GradeBook {
  private ArrayList<Student> students;

  public GradeBook() {
    this.students = new ArrayList<>();
  }
  
  public void addStudent(Student student) throws IllegalArgumentException, DuplicateStudentException {
    if (student == null) {
      throw new IllegalArgumentException("Passed in null reference");
    }
    String argStudentID = student.getStudentId();
    for (int i = 0; i < students.size(); i++) {
      Student currentStudent = students.get(i);
      if (currentStudent.getStudentId().equals(argStudentID)) {
        throw new DuplicateStudentException("Duplicate student");
      }
    }

    students.add(student);

  }
  public Student findStudent(String studentId) throws IllegalArgumentException, StudentNotFoundException {
    if (studentId == null) {
      throw new IllegalArgumentException("studentId is NULL");
    } else if (studentId.length() == 0) {
      throw new IllegalArgumentException("studentId is empty");
    }
   
    for (int i = 0; i < students.size(); i++) {
      Student chosenStudent = students.get(i);
      if (chosenStudent.getStudentId().equals(studentId)) {
        return chosenStudent;
      }
    }

    throw new StudentNotFoundException("Failed to find student with id: " + studentId);
  }

  public double getClassAverage() throws NoGradesException {
    if (students.size() == 0) {
      throw new NoGradesException("No students");
    }

    Double gradeAverage = 0.0;
    for (int i = 0; i < students.size(); i++) {
      ArrayList<Double> studentGrades = students.get(i).getGrades();
      for (int j = 0; j < studentGrades.size(); j++) {
        gradeAverage += studentGrades.get(j);
      }
    }
    if (gradeAverage == 0.0) {
      throw new NoGradesException("No grades reported for students");
    }
    return gradeAverage / students.size();
  }
  
  public ArrayList<Student> getHonorsStudents() {
    ArrayList<Student> honorsStudents = new ArrayList<>();
    for (int i = 0; i < students.size(); i++) {
      try {
        Student currentStudent = honorsStudents.get(i);
        if (currentStudent.getLetterGrade().equals("A")) {
          honorsStudents.add(currentStudent);
        }
      } catch (NoGradesException e) {
        continue;
      }
    }
    return honorsStudents;
  }
  public String getReport() {
    String headerMessage = "=== GRADEBOOK REPORT ===";
    if (students.size() == 0) {
      return headerMessage + "\nNo students in gradebook.";
    }
    
    headerMessage += "\nTotal students: " + students.size();
    headerMessage += "\nStudent Details: \n================";
    
    String studentDetails = "";
    for (int i = 0; i < students.size(); i++) {
      Student currentStudent = students.get(i);
      studentDetails += "\n" + currentStudent.toString();
    }
    
    headerMessage += studentDetails;

    String classAverageInfo = "";
    try {
      double classAverage = getClassAverage();
      classAverageInfo = "\nClass Average: " + classAverage + "%";
      headerMessage += classAverageInfo;
    } catch(NoGradesException e) {
      headerMessage += classAverageInfo;
    }
    ArrayList<Student> honorsStudents = getHonorsStudents();
    headerMessage += "\nHonors Students (A average): " + honorsStudents.size();
    
    for (int j = 0; j < honorsStudents.size(); j++) {
      String honorsEntry = "\n - " + honorsStudents.get(j).getName();
      headerMessage += honorsEntry;
    }
    headerMessage += "\n========================";
    return headerMessage;
  }
  public ArrayList<Student> getAllStudents() {
    // Create a defensive copy to maintain encapsulation
    return new ArrayList<>(students);
  }
}
