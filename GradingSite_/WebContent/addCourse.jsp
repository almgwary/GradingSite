<%@page import="models.Instructor"%>
<%@page import=" database.LoginTasks"%>
<%@page import="database.SignupTasks"%>
<%@page import=" models.Instructor"%>
<%@page import="models.Student"%>
<%@page import="models.User"%>
<%@page import="database.*"%>
<%
    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
    	response.sendRedirect("index.jsp");
%>
<%} else {
%>
	Youare LoggedIn You Must Be Directed
<% 
 if(session.getAttribute("type").toString().equals("students")){response.sendRedirect("studentProfil.jsp");}
 if(session.getAttribute("type").toString().equals("teachers") ){ 
	 //get user opject
	 Instructor teacherOpject = (Instructor) LoginTasks.userLogin(session.getAttribute("email").toString(), session.getAttribute("pass").toString(), session.getAttribute("type").toString());
	//add course 
	String coursName = request.getParameter("course_name");
	if(coursName != null){
		System.out.println("- add new course : " + coursName);
		CoursesAndGradesTasks coursesAndGradesTasks = new CoursesAndGradesTasks();
		int courseID = coursesAndGradesTasks.addCourse(teacherOpject, coursName);
		if(courseID != -1){
			response.sendRedirect("teacherProfil.jsp");
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
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="./addCourse.jsp">+ Course</a></li>
        <li><a href="./teacherProfil.jsp">profile</a></li>
		<li><a href="logout.jsp">LOG OUT</a></li>
      </ul>
    </div>
  </div>
</nav>
<!-- pasic information -->
<div id="about" class="container-fluid">
   	<form class="form-inline" name="login" id="login" method="post"> 
	   	<div class="form-group">
		  <label for="usr">Course Name:</label>
		  <input name="course_name" type="text" class="form-control" id="usr">
		</div>
		<button type="submit" class="btn btn-primary btn-md">add</button>
	</form>

</div>


<script src="onlineGrades.js"></script>

</body>
</html>

<%		
	}
}
%>