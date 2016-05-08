

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import database.CoursesAndGradesTasks;
import database.LoginTasks;
import models.Instructor;

/**
 * Servlet implementation class ServeletScervice
 */
@WebServlet("/someservlet/*")
public class ServeletScervice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeletScervice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			
			/*String courseName ="sdfsdf";
			
			String email 	= request.getSession().getAttribute("email").toString();
			String pass 	= request.getSession().getAttribute("pass").toString();
			String userType = request.getSession().getAttribute("type").toString();
			
			Instructor  user = (Instructor) LoginTasks.userLogin(email,pass,userType);
			*/
			///System.out.println(user.getFirstName());
			System.out.println("88888888");
			//CoursesAndGradesTasks.addCourse(user, courseName);
		 	String text = "some text";
		    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
		    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
		    response.getWriter().write(text);       // Write response body.
		    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
