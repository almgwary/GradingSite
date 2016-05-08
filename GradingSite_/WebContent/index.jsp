
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
%>
<%@page import="models.Instructor"%>
<%@page import=" database.LoginTasks"%>
<%@page import="database.SignupTasks"%>
<%@page import=" models.Instructor"%>
<%@page import="models.Student"%>
<%@page import="models.User"%>
<%
    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
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
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
		<li><a href="/GradingSite/signup.jsp">SIGN UP</a></li>
      </ul>
    </div>
  </div>
</nav>
<%
//System.out.println("#####################\n"+request.getParameter("email")+"\n"+request.getParameter("type")+"\n"+request.getParameter("pass"));

	String email = request.getParameter("email");
	String pass = request.getParameter("pass"); 
	String type = request.getParameter("type") ;
	
	if(email != null && pass != null && type != null ) {
		//check loggin teacher
		if(type.equals("Teacher")){
			System.out.println("- teacherLogginTest");
			Instructor newInstructor = (Instructor) LoginTasks.userLogin(email, pass, "teachers");
			if(newInstructor != null){
				session.setAttribute("userid", newInstructor.getId());
				session.setAttribute("name", newInstructor.getFirstName() );
				session.setAttribute("type", newInstructor.getUserType());
				session.setAttribute("email", newInstructor.getEmail());
				session.setAttribute("pass", newInstructor.getPassword());
				response.sendRedirect("teacherProfil.jsp");
			}
			
		}
		//check loggin studnent
		else if (type.equals("Student")){
			System.out.println("- studentLogginTest");
			Student newStuden = (Student) LoginTasks.userLogin(email, pass, "students");
			if(newStuden != null){
				session.setAttribute("userid",	newStuden.getId());
				session.setAttribute("name",	newStuden.getFirstName() );
				session.setAttribute("type",	newStuden.getUserType());
				session.setAttribute("email", 	newStuden.getEmail());
				session.setAttribute("pass", 	newStuden.getPassword());
				response.sendRedirect("studentProfil.jsp");
			}
	}
		
		
	}
	
%>
<div class="jumbotron text-center">
  <h1>OnlineGrades</h1> 
  <p>We specialized in organizing courses grades</p> 
  <form class="form-inline" name="login" id="login" method="post">
    
 	<input name="email" type="email" class="form-control" size="50" placeholder="Email Address" required >
    
	<input name="pass" type="password" class="form-control" size="50" placeholder="Password" required>  
	 <br><br>
	 <div class="form-group">
      <select name="type" class="form-control" id="sel1">
        <option>Teacher</option>
        <option>Student</option>
      </select>
	</div>
	
    <button type="submit" class="btn btn-danger">Login </button>
    
  </form>
</div>

 
 

<script src="onlineGrades.js"></script>

</body>
</html>

<%} else {
%>
	Youare LoggedIn You Must Be Directed
<% 
if(session.getAttribute("type").toString().equals("students") ){
	response.sendRedirect("studentProfil.jsp");
	
}else if(session.getAttribute("type").toString().equals("teachers")){
	response.sendRedirect("teacherProfil.jsp");
}
}
%>