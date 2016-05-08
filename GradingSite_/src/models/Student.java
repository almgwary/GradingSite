package models;

import java.util.ArrayList;

/**
 *
 * @author Mohamed Gamal
 */
public class Student extends User {

    public Student(String firstName, String lastName, String email, String password, String country, String city, String university, String faculty) {
        super(firstName, lastName, email, password, country, city, university, faculty);
    }

    @Override
    public void setCoursesIDs(ArrayList<Integer> coursesIDs) {
        super.setCoursesIDs(coursesIDs); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Integer> getCoursesIDs() {
        return super.getCoursesIDs(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public void setFaculty(String faculty) {
        super.setFaculty(faculty); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUniversity(String university) {
        super.setUniversity(university); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCity(String city) {
        super.setCity(city); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCountry(String country) {
        super.setCountry(country); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(int id) {
        super.setId(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFaculty() {
        return super.getFaculty(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUniversity() {
        return super.getUniversity(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCity() {
        return super.getCity(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCountry() {
        return super.getCountry(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPassword() {
        return super.getPassword(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEmail() {
        return super.getEmail(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLastName() {
        return super.getLastName(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFirstName() {
        return super.getFirstName(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUserType() {
        return "students"; //To change body of generated methods, choose Tools | Templates.
    }
    
}
