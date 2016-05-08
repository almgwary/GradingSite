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
import models.User;

/**
 *
 * @author Mohamed Gamal
 */
public class CoursesAndGradesTasks {
    
    //use this function to get courses related by specific User 
    public static ArrayList<Course> retrieveUserCourses(User user){
        ArrayList<Integer>IDs=retrieveUserCoursesIDs(user);
        ArrayList<Course>courses=new ArrayList<>();
        
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            for(int i=0;i<IDs.size();i++)
            {
                PreparedStatement ps = con.prepareStatement("select name, TeacherID from courses where ID = '"
                    +IDs.get(i).intValue()+"'");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Course course = new Course(rs.getInt("TeacherID"),rs.getString("name"));
                    course.setStudentsIDs(retrieveCourseStudentsIDs(IDs.get(i).intValue()));
                    course.setId(IDs.get(i).intValue());
                    course.setGradesIDs(retrieveCourseGradesIDs(IDs.get(i).intValue()));
                    courses.add(course);
                }
            }
            if(con!=null)con.close();
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //use this function to get course grades by specific course ID
    public static ArrayList<Grade> retrieveCourseGrades(int courseID){
        ArrayList<Grade>arr=new ArrayList<>();
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            PreparedStatement ps = con.prepareStatement("select ID, grade, maxGrade, typeOfGrade, StudentsID "
                    + "from grade where CoursesID = '" + courseID +"'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Grade grade = new Grade(rs.getInt("StudentsID"), rs.getInt("grade"), rs.getInt("maxGrade"), 
                        rs.getString("typeOfGrade"));
                grade.setId(rs.getInt("ID"));
                grade.setCourseID(courseID);
                arr.add(grade);
            }
            if(con!=null)con.close();
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    //use this function to allow the instructor to add course 
    //NOTE: the course name must be unique and if it already exist before the function will return -1
    //if the insertion succeeded the function would return the course ID
    public static int addCourse(User user, String courseName){
        int courseID=-1;
        if(isCourseExist(courseName)) return -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            Statement st;
            st = con.createStatement();
            int check = st.executeUpdate("insert into courses(name, TeacherID) values ('"+courseName+"', '"+user.getId()+"')");
            if(check>0){
            	
                 int  id =getCourseID(courseName);
                 if(con!=null)con.close();
                 return id ;
            }
            
            else{if(con!=null)con.close(); return -1;}

        } catch (SQLException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courseID;
    }
    
    //use this function to allow the instructor to update course 
    //NOTE: the course name must be unique and if it already exist before the function will return false
    //if the insertion succeeded the function would return true
    public static boolean updateCourse(User user, String courseName){
        int courseID=getCourseID(courseName);;
        if(isCourseExist(courseName)) return false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            Statement st;
            st = con.createStatement();
            int check = st.executeUpdate("update courses set name='"+courseName+"', TeacherID='"+user.getId()+
                    "' WHERE ID='"+courseID+"'");
            if(check>0){
            	if(con!=null)con.close();
                return true;
            }
            else{if(con!=null)con.close();  return false;}

        } catch (SQLException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //use this function to allow instructor to add grade to specific course, it returns the grade ID
    //NOTE: typeOfGrade should be unique according to the course 
    //ex: if we have course name = "data structues" and it have typeOfGrade named by "quiz1" you cannot add "quiz1" again
    //because it wouldn't be unique for "data structures" 
    //in this case function will return -1
    public static int addGrade(User user, String courseName, String studentEmail, String typeOfGrade, String  grade, String maxGrade){
        int gradeID=-1;
        int courseID=getCourseID(courseName);
        int studentID=getStudentID(studentEmail);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            Statement st;
            st = con.createStatement();
            int check = st.executeUpdate("insert into grade(grade, maxGrade, typeOfGrade, CoursesID, StudentsID)"
                    + " values ('"+grade+"', '"+maxGrade+"', '"+ typeOfGrade +"', '"+courseID+"', '"+studentID+"')");
            if(check>0){
            	
                int gid = getGradeID(courseName, typeOfGrade);
                if(con!=null)con.close();
                return gid ;
            }
            else {if(con!=null)con.close();return -1;}

        } catch (SQLException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gradeID;
    }
    
    //use this function to allow instructor to update grade to specific course, it returns true
    //NOTE: typeOfGrade should be unique according to the course 
    //ex: if we have course name = "data structues" and it have typeOfGrade named by "quiz1" you cannot add "quiz1" again
    //because it wouldn't be unique for "data structures" 
    //in this case function will return false
    public static boolean updateGrade(User user, String courseName, String studentEmail, String typeOfGrade, 
            int grade, int maxGrade){
        int gradeID=getGradeID(courseName, typeOfGrade);
        int courseID=getCourseID(courseName);
        int studentID=getStudentID(studentEmail);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            Statement st;
            st = con.createStatement();
            int check = st.executeUpdate("update grade set grade='"+grade+"', maxGrade='"+maxGrade+"'"
                    + ", typeOfGrade='"+typeOfGrade+"', CoursesID='"+courseID+"', StudentsID='"+studentID+"'"+
                    "' WHERE ID='"+gradeID+"'");
            if(check>0){
            	if(con!=null)con.close();
                return true;
            }
            else {if(con!=null)con.close(); return false;}

        } catch (SQLException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //use this function to make student join course by select a course name
    public static boolean joinCourse(User user, String courseName){
        int courseID=getCourseID(courseName);
        
        ArrayList<Integer>courseStudentsIDs = retrieveCourseStudentsIDs(courseID);
        //System.out.println("corseId: "+ courseID);
       // System.out.println("allStudenInThisCorse: "+ courseStudentsIDs.toString());
        if(courseStudentsIDs.contains(Integer.valueOf(user.getId()))) return false;
        else{
        	//System.out.println("joiningCourse");
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection con;
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
                Statement st;
                st = con.createStatement();
                int check = st.executeUpdate("insert into students_courses(StudentsID, CoursesID) values ('"+user.getId()+"', '"+courseID+"')");
                if(con!=null)con.close();
                if(check>0) return true;
                else return false;

            } catch (SQLException ex) {
                Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public static int getCourseID(String courseName){
        int ID=-1;
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            
            PreparedStatement ps = con.prepareStatement("select ID from courses where name = '"+courseName+"'");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) ID=rs.getInt("ID");
            {if(con!=null)con.close(); return ID;}
        } catch (SQLException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ID;
    }
    
    public static int getStudentID(String studentEmail){
        int ID=-1;
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            
            PreparedStatement ps = con.prepareStatement("select ID from students where email = '"+studentEmail+"'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) ID=rs.getInt("ID");
            if(con!=null)con.close();
            return ID;
        } catch (SQLException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ID;
    }
    
    public static int getGradeID(String courseName, String gradeName){
        int ID=-1;
        int courseID=getCourseID(courseName);
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            
            PreparedStatement ps = con.prepareStatement("select ID from grade where typeOfGrade = '"+gradeName+"' and "
                    + "CoursesID = '"+courseID+"'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) ID=rs.getInt("ID");
            if(con!=null)con.close();
            return ID;
        } catch (SQLException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ID;
    }
    
    private static boolean isCourseExist(String courseName){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            PreparedStatement ps = con.prepareStatement("select* from courses where name = '" + courseName +"'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {if(con!=null)con.close(); return true;}
        } catch (SQLException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private static ArrayList<Integer> retrieveCourseGradesIDs(int courseID){
        ArrayList<Integer>arr=new ArrayList<>();
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            PreparedStatement ps = con.prepareStatement("select ID from grade where CoursesID = '" + courseID +"'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                arr.add(Integer.valueOf(rs.getInt("ID")));
            }
            if(con!=null)con.close();
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    private static ArrayList<Integer> retrieveCourseStudentsIDs(int courseID){
        ArrayList<Integer>arr=new ArrayList<>();
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            PreparedStatement ps = con.prepareStatement("select StudentsID from students_courses where CoursesID = '" 
                        + courseID +"'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) arr.add(Integer.valueOf(rs.getInt("StudentsID")));
            if(con!=null)con.close();
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    private static ArrayList<Integer> retrieveUserCoursesIDs(User user){
        Connection con;
        ArrayList<Integer>arr=new ArrayList<Integer>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            
            if(user.getUserType().equals("students")){
                PreparedStatement ps = con.prepareStatement("select CoursesID from students_courses where StudentsID = '" 
                        + user.getId() +"'");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) arr.add(Integer.valueOf(rs.getInt("CoursesID")));
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
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private static ArrayList<Course> retrieveAllCourses(){
        ArrayList<Course>courses=new ArrayList<>();
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade_schema", "root", "admin");
            
            PreparedStatement ps = con.prepareStatement("select* from courses");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course(rs.getInt("TeacherID"),rs.getString("name"));
                course.setStudentsIDs(retrieveCourseStudentsIDs(rs.getInt("ID")));
                course.setId(rs.getInt("ID"));
                course.setGradesIDs(retrieveCourseGradesIDs(rs.getInt("ID")));
                courses.add(course);
            }
            if(con!=null)con.close();
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(CoursesAndGradesTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }
    
}
