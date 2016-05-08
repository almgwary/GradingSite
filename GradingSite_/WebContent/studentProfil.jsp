<%@page import="java.util.HashMap"%>
<%@page import="database.AdditionalService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Course"%>
<%@page import="database.CoursesAndGradesTasks"%>
<%@page import="models.Instructor"%>
<%@page import=" database.LoginTasks"%>
<%@page import="database.SignupTasks"%>
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
 if(session.getAttribute("type").toString().equals("teachers")){response.sendRedirect("teacherProfil.jsp");}
 if(session.getAttribute("type").toString().equals("students") ){ 
	 //get user opject
	 Student studentOpject = (Student) LoginTasks.userLogin(session.getAttribute("email").toString(), session.getAttribute("pass").toString(), session.getAttribute("type").toString());
 	//get student corses 
 	CoursesAndGradesTasks coursesAndGradesTasks = new CoursesAndGradesTasks();
	ArrayList<Course> studentCourses =   coursesAndGradesTasks.retrieveUserCourses(studentOpject);
 	
	int couresesNumber = studentCourses.size();
	String sId =session.getAttribute("userid").toString() ;
 	int studentId =  Integer.parseInt(sId);
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
  <style type="text/css">
  .links {z-index:9999;}
  </style>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
    
        <li><a href="./studentProfil.jsp">Profile</a></li>
		<li><a class="links"  href="logout.jsp">LOG OUT</a></li>
      </ul>
    </div>
  </div>
</nav>
<!-- pasic information -->
<div id="about" class="container-fluid">
  <div class="row">
    <!-- profile picture --> 
	<div class="col-sm-3">
      <img src="src/img/av1.png" height="250" width="250"> </img>
    </div>
	<!-- information table -->
	
	<div class="col-sm-8">
	<div class="col-xs-12" style="height:50px;"></div>
	<div class="row"> 
				<!-- name <div class="col-sm-2"> <h2> Name : </h2> </div> -->
				<div class="col-sm-8"> <h2> <% out.print(studentOpject.getFirstName() + " " +studentOpject.getLastName() ); %> </h2></div>
 	</div>
	<div class="row">
		<div class="col-sm-5">
		  <p> University of <% out.print(studentOpject.getUniversity() ); %> </p>
		  <p> Faculty of  <% out.print(studentOpject.getFaculty() ); %> </p>
		  <p> Address :  <% out.print(studentOpject.getCountry()+", "+studentOpject.getCity() ); %> </p>
		  <p> Email :  <% out.print(studentOpject.getEmail() ); %> </p>
		  
		  	   
		</div>
	</div>
  </div>
</div>

<!-- Container ( courses inforrmations) --> 
	<h2><span class="label label-success"> <span class="badge"><%=couresesNumber %></span> course</span>
	<button type="button" onclick="location.href='./joinCourse.jsp'" class="btn btn-primary">Add Course</button> </h2> 

<div class="container">
  <!-- <h2>Contextual Classes</h2>
  <p>Contextual classes can be used to color table rows or table cells. The classes that can be used are: .active, .success, .info, .warning, and .danger.</p>            
	-->
  <table class="table">
    <thead>
      <tr>
        <th>ID</th>
        <th>Course Name </th>
		<th>Maxumum Grades</th>
		<th>Grade</th>
  
		<th>Show grades</th>
      </tr>
    </thead>
    <tbody>
    <%for(Course course : studentCourses){	
    	HashMap<Integer,Integer> studeentTotalGrase = AdditionalService.getStudentsTotalGrade(course.getId());
    	HashMap<Integer, ArrayList<Grade>> gMa = AdditionalService.getStudentsGrades(course.getId());
    	
		
		
    
    
    %>
      <tr class="success">
        <td><%out.print(course.getId()); %></td>
        <td><%out.print(course.getName()); %></td>
        <td><%out.print(AdditionalService.getCourseMaxGrade(course.getId())); %> 	</td>
		<td><%out.print(studeentTotalGrase.get(studentId)); %> 	 </td>
		
		<form>
			<input name="course_name" type="hidden"  value="<%out.print( course.getName());%>"><br>
			<td><button name="updated"  formaction="./studentCourseGrade.jsp" formmethod="POST" type="submit" class="btn btn-primary">	show grade </button> </td>
		</form>
		
      </tr>
	  <%	} %>
	  
	  
      
    </tbody>
  </table>
</div>
<script src="src/js/onlineGrades.js"></script>

</body>
</html>

<%		
	}
}
%>