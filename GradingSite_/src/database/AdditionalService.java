/**
 * 
 */
package database;

import java.util.ArrayList;
import java.util.HashMap;
import com.sun.javafx.scene.traversal.TopMostTraversalEngine;

import models.Grade;

/**
 * @author almgwary
 * Dec 30, 2015  11:24:37 AM  
 * GradingSite  database  
 * AdditionalServiceClass.java
 */
public  class AdditionalService {

	// get tasks with thier maximum grad
	public static HashMap<String, Integer> getTasksOfCourse(int courseID){
		// get all course grades
		ArrayList<Grade> courseGrades = CoursesAndGradesTasks.retrieveCourseGrades(courseID);
		
		// make map to get all course tasks uniquely
		HashMap<String, Integer> tasks = new HashMap<String, Integer>();
		
		for(Grade  grade : courseGrades){
			tasks.put(grade.getTypeOfGrade(), grade.getMaxGrade());
		}
		
		return tasks ;
	}

	// get max grad of course 
	public static int getCourseMaxGrade (int courseId){
		HashMap<String, Integer> tasks = getTasksOfCourse(courseId);
		int maxGrade=0;
		for(String task : tasks.keySet()){
			maxGrade += tasks.get(task);
		}
		return maxGrade;
	}

	
	// get every student  grade <studentID,GradesList>
	public static HashMap<Integer,ArrayList<Grade>> getStudentsGrades(int courseId) {
		//get all grades 	
		ArrayList<Grade> courseGrades = CoursesAndGradesTasks.retrieveCourseGrades(courseId);
		// map for each studen his grades only 
		HashMap<Integer,ArrayList<Grade>> studentsGades = new HashMap<>();
		for(Grade grade : courseGrades){
			// check if student exist in map
			if(studentsGades.containsKey(grade.getStudentID())){
				//student exist add grade to map
				
				//get array list
				ArrayList<Grade> gradesX = studentsGades.get(grade.getStudentID());
				// add new grade
				gradesX.add(grade);
				//add new array list
				studentsGades.put(grade.getStudentID(), gradesX);
			}
			// if student not exist on the map
			else {
				// add new grade
				ArrayList<Grade> gradesX = new 	ArrayList<Grade>();
				// add new grade
				gradesX.add(grade);
				studentsGades.put(grade.getStudentID(), gradesX);
				
			}
		}
				
		return studentsGades; 
	}
		
	
	// get every student total grade
	public static HashMap<Integer,Integer> getStudentsTotalGrade(int courseId) {
		//getStudentsGrades
		HashMap<Integer,ArrayList<Grade>> studensGrades = getStudentsGrades(courseId);
		HashMap<Integer,Integer> studensTotalGrade =  new HashMap<Integer,Integer>();
		
		for(int studentId : studensGrades.keySet()){
			int totlGrade = 0;
			for(Grade grade : studensGrades.get(studentId)){
				totlGrade+= grade.getGrade();
			}
		// add this grade to student
			studensTotalGrade.put(studentId, totlGrade) ;
		}
		
		
		return studensTotalGrade; 
	}
	
	//get the hightes totla Grade By student 
	public static Integer getHighestgrade (int courseId ){
		int maxGrade = 0 ;
		
		HashMap<Integer,Integer> studeentTotalGrase =getStudentsTotalGrade(courseId);
		HashMap<Integer, ArrayList<Grade>> gMa = getStudentsGrades(courseId);
		// print student grades on the course
		for(int stId : gMa.keySet()){
			maxGrade = Integer.max(maxGrade, studeentTotalGrase.get(stId));  
		}
		
		
		
		return maxGrade ; 
	}

	//averagGrade
	public static String averageGrade (int courseId) {
		int numberOfStudnts = 0;
		int totalGrades = 0 ;
		
		HashMap<Integer,Integer> studeentTotalGrase = getStudentsTotalGrade(courseId);
		HashMap<Integer, ArrayList<Grade>> gMa = getStudentsGrades(courseId);
		// print student grades on the course
		for(int stId : gMa.keySet()){
			totalGrades+= studeentTotalGrase.get(stId);
			numberOfStudnts++;			 
		}
		
		double avg = (double)totalGrades/numberOfStudnts ;
		avg = (double)Math.round(avg * 100) / 100;
		return  Double.toString(avg);
	}

	public static Integer nuberOfSucceess (int courseId){
		int n = 0 ;
		int halfgrad= getCourseMaxGrade(courseId)/2;
		
		HashMap<Integer,Integer> studeentTotalGrase = AdditionalService.getStudentsTotalGrade(1);
		HashMap<Integer, ArrayList<Grade>> gMa = AdditionalService.getStudentsGrades(1);
		
		// print student grades on the course
		for(int stId : gMa.keySet()){
			int studentTotalGrade = studeentTotalGrase.get(stId);
			if(studentTotalGrade >= halfgrad)++n;
		}
		
		return n ;
	}
}
