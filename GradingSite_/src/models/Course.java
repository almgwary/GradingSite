package models;

import java.util.ArrayList;

/**
 *
 * @author Mohamed Gamal
 */
public class Course {
    private int id, instructorID;
    private String name;
    private ArrayList<Integer>studentsIDs;
    private ArrayList<Integer>gradesIDs;

    public Course(int instructorID, String name) {
        this.instructorID = instructorID;
        this.name = name;
    }

    public ArrayList<Integer> getGradesIDs() {
        return gradesIDs;
    }

    public void setGradesIDs(ArrayList<Integer> gradesIDs) {
        this.gradesIDs = gradesIDs;
    }

    public ArrayList<Integer> getStudentsIDs() {
        return studentsIDs;
    }

    public void setStudentsIDs(ArrayList<Integer> studentsIDs) {
        this.studentsIDs = studentsIDs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
