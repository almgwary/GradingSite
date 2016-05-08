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

	//get teacher corses 
	 	CoursesAndGradesTasks coursesAndGradesTasks = new CoursesAndGradesTasks();
		ArrayList<Course> teacherCourses =   coursesAndGradesTasks.retrieveUserCourses(teacherOpject);
	 	
		int couresesNumber = teacherCourses.size();
		
		HashMap <Integer,ArrayList<Grade>> coursesGradsMaps = new HashMap <Integer,ArrayList<Grade>> ();//for every course get all grades
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
		  <p> Address :  <% out.print(teacherOpject.getCountry()+", "+teacherOpject.getCity() ); %> </p>
		  <p> Email :  <% out.print(teacherOpject.getEmail() ); %> </p>
		  
		  	   
		</div>
	</div>
  </div>
</div>

<!-- Container ( courses inforrmations) --> 
	<h2><span class="label label-success"> <span class="badge"><%=couresesNumber %></span> course</span>
	<button type="button" onclick="location.href='./addCourse.jsp'" class="btn btn-primary">Add Course</button> </h2> 

<div class="container">
  <!-- <h2>Contextual Classes</h2>
  <p>Contextual classes can be used to color table rows or table cells. The classes that can be used are: .active, .success, .info, .warning, and .danger.</p>            
	-->
  <table class="table">
    <thead>
      <tr>
        <th>ID</th>
        <th>Course Name </th>
        <th>Number Of Students </th>
		<th>Grade</th>
		<th>Max Grade</th>
		<th>Averge Grade </th>
		<th>Number of Success</th>
		<th>Number of fails</th>
		<th><!-- edit --> </th>
		<th><!-- Genrat report --> </th>
      </tr>
    </thead>
    <tbody>
    <%for(Course course : teacherCourses){	
    	//AdditionalService.getTasksOfCourse(course.getId());
    	int courseID =course.getId();
    	String CourseName = course.getName();
    	int numberOdStudents = course.getStudentsIDs().size() ;
    	int maxGrade = AdditionalService.getCourseMaxGrade(course.getId());
    	int highGrade =AdditionalService.getHighestgrade(course.getId());
    	String averageeGrade =  AdditionalService.averageGrade(course.getId()) ;
    	int numberOfSuccess = AdditionalService.nuberOfSucceess(course.getId()); 
    	int numbrOfFails= numberOdStudents - numberOfSuccess ;
    	
    
    %>
      <tr <% if(numberOfSuccess >numbrOfFails ){ %>class="success" <%}else{ %>class="danger"<%} %>>
        <td><%out.print(courseID); %></td>
        <td><%out.print(CourseName); %></td>
        <td><%out.print(numberOdStudents); %></td>
		<td><%out.print(maxGrade); %></td>
		<td><%out.print(highGrade); %></td>
		<td><%out.print(averageeGrade); %></td>
		<td><%out.print(numberOfSuccess); %></td>
		<td><%out.print(numbrOfFails); %></td>
		
		<form>
			<input name="course_name" type="hidden"  value="<%out.print( course.getName());%>"><br>
			<td><button name="updated"  formaction="./addcourseGrade.jsp" formmethod="POST" type="submit" class="btn btn-primary">	add Grade		</button> </td>
			<td><button name="updated"  formaction="./courseReport.jsp"   formmethod="POST" type="submit" class="btn btn-primary">	Course Report	</button> </td>
		</form>
		
		 
		
      </tr>
       <%	} %>
      <tr class="danger">
         <td>2</td>
        <td>Arabic</td>
        <td>500</td>
		<td>100</td>
		<td>50</td>
		<td>76</td>
		<td>70</td>
		<td>6</td>
		<td><button type="button" class="btn btn-primary">add Grade</button> </td>
		<td><button type="button" class="btn btn-primary">Course Report</button> </td>
      </tr>
       
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