<%@page import="models.Instructor"%>
<%@page import=" database.LoginTasks"%>
<%@page import="database.SignupTasks"%>
<%@page import=" models.Instructor"%>
<%@page import="models.Student"%>
<%@page import="models.*"%>
<%@page import="database.*"%>
<%
    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
    	response.sendRedirect("index.jsp");
%>
<%} else {
%>
	
<% 
 if(session.getAttribute("type").toString().equals("students")){response.sendRedirect("studentProfil.jsp");}
 if(session.getAttribute("type").toString().equals("teachers") ){ 
	 //get user opject
	 User teacherOpject = (User) LoginTasks.userLogin(session.getAttribute("email").toString(), session.getAttribute("pass").toString(), session.getAttribute("type").toString());
	//add course 
	String coursName = request.getParameter("course_name");
	if(coursName != null){
		System.out.println("- addCourseGrade : " + coursName);
		
		String studen_email 	= request.getParameter("studen_email");
		String grade 			= request.getParameter("grade");
		String type_of_grade 	= request.getParameter("type_of_grade");
		String max_grade 		= request.getParameter("max_grade");
		
		if(studen_email != null  &&  grade != null &&type_of_grade !=null && max_grade != null){
			System.out.println("adding Grade Course: "+coursName+" Student:" +studen_email  );
			CoursesAndGradesTasks andGradesTasks = new CoursesAndGradesTasks();
			andGradesTasks.addGrade(teacherOpject , coursName, studen_email, type_of_grade, grade, max_grade);
		}
		 
		}else {
		System.out.println("CourseName: Null" );
		response.sendRedirect("teacherProfil.jsp");
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

  <form class="form" name="login" id="login"  method="post">
	<br><br>
	<div class="form col-lg-7 text-left">
	<div class="form-group">
		<label for="usr">Course Name:</label>
		<input  name="course_name" 		type="text" class="form-control" size="50" value="<%out.print( coursName);%>"   readonly="readonly"><br>
	</div>
	<div class="form-group">
		<label for="usr">Student Email:</label>
		<input  name="studen_email" 	type="text" class="form-control" size="50"> <br>
	</div>
	<div class="form-group">
		<label for="usr">Grade :</label>
		<input  name="grade" 			type="text" class="form-control" size="50"> <br>
	</div>
	<div class="form-group">
		<label for="usr">Type Of grade:</label>
		<input  name="type_of_grade" 	type="text" class="form-control" size="50"> <br>
	</div>
	<div class="form-group">
		<label for="usr"> Max Grad:</label>
		<input  name="max_grade" 		type="text" class="form-control" size="50"> <br>
	</div>	
		<br><br>
   		<button type="submit" class="btn btn-danger">Add Grade </button>
	</div>
  </form>
</div>
<script src="onlineGrades.js"></script>
</body>
</html>









<%		
	}
}
%>