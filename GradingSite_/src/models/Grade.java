package models;

/**
 *
 * @author Mohamed Gamal
 */
public class Grade {
    private int id, studentID, grade, maxGrade, courseID;
    private String typeOfGrade;

    public Grade(int studentID, int grade, int maxGrade, String typeOfGrade) {
        this.studentID = studentID;
        this.grade = grade;
        this.maxGrade = maxGrade;
        this.typeOfGrade = typeOfGrade;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(int maxGrade) {
        this.maxGrade = maxGrade;
    }

    public String getTypeOfGrade() {
        return typeOfGrade;
    }

    public void setTypeOfGrade(String typeOfGrade) {
        this.typeOfGrade = typeOfGrade;
    }
    
}
