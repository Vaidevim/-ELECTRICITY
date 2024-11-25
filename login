package city;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginvv
 */
@WebServlet("/loginvv")
public class loginvv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginvv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String sub = request.getParameter("submit");
		String nam = request.getParameter("uName");
		String pas = request.getParameter("pass");
		String mob = request.getParameter("mobile");
		String ser = request.getParameter("server");
		if(sub.equals("admin")) {
			if (nam.equals("vaishu")&&pas.equals("Admin@vv")) {
				RequestDispatcher rd = request.getRequestDispatcher("adhome.html");
				rd.forward(request, response);
			} else {
				out.print(" invalid user name or password");
				RequestDispatcher rdr = request.getRequestDispatcher("Login.html");
				rdr.include(request, response);
			}
		}
		else if(sub.equals("consumerLogin")) {
			 if (connectivity.login(nam, pas)) {
					RequestDispatcher rdr = request.getRequestDispatcher("adbill.html");
					rdr.forward(request, response);
				 } else {
						out.print(" invalid user name or password");
						RequestDispatcher rd = request.getRequestDispatcher("Login.html");
						rd.include(request, response);
			 }
		}
		else {
			if (connectivity.anotherlogin(mob, ser)) {
				RequestDispatcher rd = request.getRequestDispatcher("adbill.html");
				rd.forward(request, response);
			 } else {
					out.print(" invalid user name or password");
					RequestDispatcher rd = request.getRequestDispatcher("Login.html");
					rd.include(request, response);
		 }
		}
			
	}

}

