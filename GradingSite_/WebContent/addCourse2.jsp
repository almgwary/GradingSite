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
   <div class="form-group">
	  <label for="usr">Course Name:</label>
	  <input type="text" class="form-control" id="usr">
	</div>
	<div class="form-group">
	  <label for="pwd">Course Id:</label>
	  <input type="text" class="form-control" id="pwd">
	</div>
	
	<div class="form-group">
	  <label for="xgrad">Max Grade:</label>
	  <input type="text" class="form-control" id="pwd">
	</div>
	<h2><span class="label label-success"> <span class="badge">7</span> students </span></h2> 
	<button type="button" class="btn btn-primary btn-md">add task</button>
	<button type="button" class="btn btn-primary btn-md">save</button>
</div>

<!-- Container ( courses inforrmations) --> 
	

<div class="container">
  <!-- <h2>Contextual Classes</h2>
  <p>Contextual classes can be used to color table rows or table cells. The classes that can be used are: .active, .success, .info, .warning, and .danger.</p>            
	-->
  <table class="table">
    <thead>
      <tr>
        <th>#</th>
        <th>Course Name </th>
		<th>task1 [50]</th>
		<th>task1 [50]</th>
		<th>task1 [50]</th>
		<th>task1 [50]</th>
		<th>task1 [50]</th>
		<th>task1 [50]</th>
		<th>task1 [50]</th>
		<th>task1 [50]</th>
		<th>Maxumum Grades</th>
		<th>Grade</th>
 		<th>Grad Rank</th>
      </tr>
    </thead>
    <tbody>
      <tr class="success">
        <td>1</td>
        <td>Internet Application</td>
        <td>351</td>
		<td>351</td>
		<td>351</td>
		<td>351</td>
		<td>351</td>
		<td>351</td>
		<td>351</td>
		<td>351</td>
		<td>340 <span class="label label-success"> Top </span> </td>
		<td>5</td>
		<td> </button>
		 
		</td>
		
      </tr>
	  
	  
	  <tr class="success">
        <td>1</td>
        <td>Internet Application</td>
        <td> <input type="text" class="form-control" id="pwd"></td>
		<td> <input type="text" class="form-control" id="pwd"></td>
		<td> <input type="text" class="form-control" id="pwd"></td>
		<td> <input type="text" class="form-control" id="pwd"></td>
		<td> <input type="text" class="form-control" id="pwd"></td>
		<td> <input type="text" class="form-control" id="pwd"></td>
		<td> <input type="text" class="form-control" id="pwd"></td>
		<td>351</td>
		<td>340 <span class="label label-success"> Top </span> </td>
		<td>5</td>
		<td> </button>
		 
		</td>
		
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