package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Course;
import models.Grade;
import models.Instructor;
import models.Student;
import models.User;

/**
 *
 * @author Mohamed Gamal
 */
public class LoginTasks {

    //user only this function to login, it will return User with completed Data
    //it may return null if and only if the user doesn't exist
    public static User userLogin(String Email, String password, String userType) {
        int id=isUserExist (Email, password, userType);
        User completedUser;
        if(id!=-1){
            completedUser=retrieveUserData(id, userType);
            completedUser.setId(id);
            return completedUser;
        }
        else return null;
    }
    
    private static int isUserExist(String email, String password, String tableName){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            PreparedStatement ps = con.prepareStatement("select ID from "+tableName+" where email = '" + email + 
                    "' and password = '"+password+"'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	int x = rs.getInt("ID") ;
            	if(con!=null)con.close();
                return x;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    private static User retrieveUserData(int id, String tableName){

        User user;
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            PreparedStatement ps = con.prepareStatement("select * from "+tableName+" where ID = '" + id +"'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(tableName.equals("students")){
                    user=new Student(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), 
                    rs.getString("password"), rs.getString("country"), rs.getString("city"), rs.getString("university"),
                    rs.getString("faculty"));
                    user.setCoursesIDs(retrieveUserCoursesIDs(user));
                    return user;
                }else{
                    user=new Instructor(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), 
                    rs.getString("password"), rs.getString("country"), rs.getString("city"), rs.getString("university"),
                    rs.getString("faculty"));
                    user.setCoursesIDs(retrieveUserCoursesIDs(user));
                    if(con!=null)con.close();
                    return user;
                }
            }
            if(con!=null)con.close(); 
        } catch (SQLException ex) {
            Logger.getLogger(LoginTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private static ArrayList<Integer> retrieveUserCoursesIDs(User user){

        Connection con;
        ArrayList<Integer>arr=new ArrayList<Integer>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            
            if(user.getUserType().equals("students")){
                PreparedStatement ps = con.prepareStatement("select CoursesID from students_courses where StudentsID = '" 
                        + user.getId() +"'");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) arr.add(Integer.valueOf(rs.getInt("CoursesID")));
                if(con!=null)con.close();
                return arr;
            }else{
                PreparedStatement ps = con.prepareStatement("select ID from courses where TeacherID = '" 
                        + user.getId() +"'");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) arr.add(Integer.valueOf(rs.getInt("ID")));
                if(con!=null)con.close();
                return arr;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}