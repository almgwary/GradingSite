

<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@page import="models.Instructor"%>
<%@page import=" database.LoginTasks"%>
<%@page import="database.SignupTasks"%>
<%@page import=" models.Instructor"%>
<%@page import="models.Student"%>
<%@page import="models.User"%>
<%
    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
%>




<%
//System.out.println("#####################\n"+request.getParameter("email")+"\n"+request.getParameter("type")+"\n"+request.getParameter("pass"));

	String fname = request.getParameter("fname");
	String lname = request.getParameter("lname");
	String email = request.getParameter("email");
	String pass = request.getParameter("pass"); 
	String type = request.getParameter("type") ;
	
	String country = request.getParameter("country") ;
	String city = request.getParameter("city") ;
	
	String university = request.getParameter("university") ;
	String faculty = request.getParameter("faculty") ;

	
	
	if(email != null && pass != null && type != null ) {
		//check  teacher
		if(type.equals("Teacher")){
			System.out.println("- teacherSignUpTest");
			SignupTasks signupTasks = new SignupTasks();
			Instructor newInstructor = new Instructor(fname,//firstName, 
					lname,//lastName, 
					email,//email, 
					pass,//password, 
					country,//country, 
					city,//city, 
					university,//university, 
					faculty//faculty
					);

		    
			int id = signupTasks.insertUser(newInstructor);
			if(id != -1 ){
				Instructor instructor = (Instructor) LoginTasks.userLogin(email, pass, "teachers");
				
				if(instructor != null){
					session.setAttribute("userid", instructor.getId());
					session.setAttribute("name", instructor.getFirstName() );
					session.setAttribute("type", instructor.getUserType());
					response.sendRedirect("teacherProfil.jsp");
				}
			}
		}
		//check  studnent
		else if (type.equals("Student")){
			System.out.println("- studentSighnUpTest");
			
			SignupTasks signupTasks = new SignupTasks();
			Student newStudent = new Student(fname,//firstName, 
					lname,//lastName, 
					email,//email, 
					pass,//password, 
					country,//country, 
					city,//city, 
					university,//university, 
					faculty//faculty
					);
			int id = signupTasks.insertUser(newStudent);
			
			if(id != -1 ){
				Student student = (Student) LoginTasks.userLogin(email, pass, "students");
				if(student != null){
				session.setAttribute("userid",	student.getId());
				session.setAttribute("name",	student.getFirstName() );
				session.setAttribute("type",	student.getUserType());
				response.sendRedirect("studentProfil.jsp");
			}
				
			}
	}
		
		
	}
	
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Online Grades</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="src/css/bootstrap.min.css">
  <link href="http://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <link href="http://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <link rel="stylesheet" type="text/css" href="src/css/onlineGrades.css">
  <script src="src/js/jquery.min.js"></script>
  <script src="src/js/bootstrap.min.js"></script>
</head>

<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60" class="jumbotron">

<nav class="navbar navbar-default navbar-fixed-top text-center">
	<h1>Welcome On OnlineGrades</h1> 
</nav>

<div class="jumbotron text-center row">

  <form class="form" name="login" id="login">
	<div class="form-inline text-left col-lg-10">
		<input name="fname"type="text" class="form-control" size="50" placeholder="First Name" required>
		<input name="lname"type="text" class="form-control" size="50" placeholder="Last Name" required>
	</div>
	<br><br>
	<div class="form col-lg-7 text-left">
		<input  name="email" type="email" class="form-control" size="50" placeholder="Email Address" required><br>
		<input  name="pass" type="password" class="form-control" size="50" placeholder="Password" required><br>
		<input  name="" type="password" class="form-control" size="50" placeholder="Confirm Password" required><br>
		<input  name="country" type="text" class="form-control" size="50" placeholder="Country" required><br>
		<input  name="city" type="text" class="form-control" size="50" placeholder="City" required><br>
		<input  name="university" type="text" class="form-control" size="50" placeholder="University" required><br>
		<input  name="faculty" type="text" class="form-control" size="50" placeholder="Faculty" required><br>
		<br><br>
		 <div class="form-group">
	      <select name="type" class="form-control" id="sel1">
	        <option>Teacher</option>
	        <option>Student</option>
	      </select>
		</div>
   		<button type="submit" class="btn btn-danger">Register </button>
	</div>
  </form>
</div>
<script src="onlineGrades.js"></script>
</body>
</html>



<%} else {
 
if(session.getAttribute("type").toString().equals("students") ){
	response.sendRedirect("studentProfil.jsp");
	
}else if(session.getAttribute("type").toString().equals("teachers")){
	response.sendRedirect("teacherProfil.jsp");
}
}
%>