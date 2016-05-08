import java.util.ArrayList;
import java.util.HashMap;

import database.AdditionalService;
import database.LoginTasks;
import database.SignupTasks;
import models.Grade;
import models.Instructor;
import models.Student;
import models.User;



/**
 * @author almgwary
 * Dec 29, 2015  5:44:04 PM  
 * GradingSite    
 * TestCalss.java
 */



public class TestCalss {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("test.start");
		/*
		 * 	HashMap< String, Integer> ma = AdditionalService.getTasksOfCourse(1);
			System.out.println(ma.toString());
		 */
		
		HashMap< String, Integer> ma = AdditionalService.getTasksOfCourse(1);
		System.out.println("Tasks: "+ma.toString());
		
		int h = AdditionalService.getHighestgrade(1); 
		int max = AdditionalService.getCourseMaxGrade(1);
		System.out.println("Max"+ h+ "/"+max);
		
		
		HashMap<Integer,Integer> studeentTotalGrase = AdditionalService.getStudentsTotalGrade(1);
		HashMap<Integer, ArrayList<Grade>> gMa = AdditionalService.getStudentsGrades(1);
		
		// print student grades on the course
		for(int stId : gMa.keySet()){
			System.out.println("sudent: " + stId + " Total:"+ studeentTotalGrase.get(stId));
			for(Grade g : gMa.get(stId)){
				System.out.println("		"+g.getTypeOfGrade() + " [" + g.getGrade()+ "/"+ g.getMaxGrade() +"]");
			}
			 
		}
		
		
		
		
	}

}
