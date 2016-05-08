package database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author Mohamed Gamal
 */
public class SignupTasks{
    
    //use only this function to signup .. it will return the user ID to you   
    public static int insertUser(User user) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignupTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            if(isEmailExist(user.getEmail(), user.getUserType())) return -1;
            Statement st;
            st = con.createStatement();
            int check = st.executeUpdate("insert into "+user.getUserType()+"(firstName, lastName, email, password, country, "
                    + "city, university, faculty) values ('"+user.getFirstName()+"', '"+user.getLastName()
                    +"', '"+user.getEmail()+"', "+"'"+user.getPassword()+"', '"
                    +user.getCountry()+"', '"+user.getCity()+"', "
                    + "'"+user.getUniversity()+"', '"+user.getFaculty()+"')");
            if(check>0){
                user.setId(getID(user.getEmail(), user.getUserType()));
                if(con!=null)con.close();
                return getID(user.getEmail(), user.getUserType());
            }
            else {
            	if(con!=null)con.close(); 
            	return -1;
            	}

        } catch (SQLException ex) {
        	
            Logger.getLogger(SignupTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }
    
    private static boolean isEmailExist(String email, String tableName){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignupTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con;
        try {
            con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            PreparedStatement ps = con.prepareStatement("select email from "+tableName+" where email = '" + email + "'");
            ResultSet rs = ps.executeQuery();
           
             boolean b = rs.next();
             if(con!=null)con.close();
             return b ;
        } catch (SQLException ex) {
            Logger.getLogger(SignupTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private static int getID(String email, String tableName){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignupTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            PreparedStatement ps = con.prepareStatement("select ID from "+tableName+" where email = '" + email + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	int x =rs.getInt("ID");
            	if(con!=null)con.close();
                return x;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignupTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
