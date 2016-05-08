<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Instructor"%>
<%@page import=" database.LoginTasks"%>
<%@page import="database.*"%>
<%@page import=" models.Instructor"%>
<%@page import="models.Student"%>
<%@page import="models.*"%>

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

	//get  corses
	String coursName = request.getParameter("course_name");
	if(coursName == null) response.sendRedirect("./index.jsp");
	System.out.println("making report for course: "+ coursName);
	
	int courseId =CoursesAndGradesTasks.getCourseID(coursName);
	HashMap<String,Integer> tasks = AdditionalService.getTasksOfCourse(courseId);
	HashMap<Integer,Integer> studeentTotalGrase = AdditionalService.getStudentsTotalGrade(courseId);
	HashMap<Integer, ArrayList<Grade>> gMa = AdditionalService.getStudentsGrades(courseId);
	
	 	
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
  <div class="row">
    <!-- profile picture --> 
	<div class="col-sm-3">
      <img src="src/img/av8.png" height="250" width="250"> </img>
    </div>
	<!-- information table -->
	
	<div class="col-sm-8">
	<div class="col-xs-12" style="height:50px;"></div>
	<div class="row"> 
				<!-- name <div class="col-sm-2"> <h2> Name : </h2> </div> -->
				<div class="col-sm-8"> <h2> <% out.print(teacherOpject.getFirstName() + " " +teacherOpject.getLastName() ); %> </h2></div>
 	</div>
	<div class="row">
		<div class="col-sm-5">
		  <p> University of <% out.print(teacherOpject.getUniversity() ); %> </p>
		  <p> Faculty of  <% out.print(teacherOpject.getFaculty() ); %> </p>
		  
		  <p> Course :  <% out.print(coursName ); %> </p>
		  
		  	   
		</div>
	</div>
  </div>
</div>

 
  <table class="table">
    <thead>
      <tr>
        <th>ID</th>
       
        <% for (String taskName : tasks.keySet()){
        %>
        <th><%out.print(taskName+" ["+tasks.get(taskName)+"]"); %></th>
        <%
        }%>
      
		<th>Total</th>
		<th><!-- edit --> </th>
		<th><!-- Genrat report --> </th>
      </tr>
    </thead>
    <tbody>
    
    	<% // print student grades on the course
    	
		for(int stId : gMa.keySet()){
			out.print("<tr class='danger'>");
			out.println("<td>" + stId + "</td>");
			//out.println("<td>" +  + "</td>");
			
			
			for (String taskName : tasks.keySet())
				{
				boolean absent = true ; 
				for(Grade g : gMa.get(stId)){
						if(taskName.equals(g.getTypeOfGrade()))
						{
							out.println("<td>" + g.getGrade()+ "</td>");
							absent = false ;
						}
					}
				if(absent)
					out.println("<td> absent </td>");
				}
			
			
			out.println("<td>"+ studeentTotalGrase.get(stId)+"</td>");
			out.print("</tr>");
		}
		
		%>
       
		
      
       
       
    </tbody>
  </table>
</div>
<script src="onlineGrades.js"></script>

</body>
</html>

<%		
	}
}
%>