package city;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class adminreg
 */
@WebServlet("/adminreg")
public class adminreg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminreg() {
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
		String name = request.getParameter("fname").concat(" "+request.getParameter("lname"));
		String number = request.getParameter("number");
		String service = request.getParameter("service");
		String city = request.getParameter("city");
		if (connectivity.registerTable(service)&&connectivity.register(name, number, service, city)) {
	        Date currentDate = new Date(0);
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			connectivity.billRegister(service, dateFormat.format(currentDate), "0");
			out.print(" successfully registered");
			RequestDispatcher rd = request.getRequestDispatcher("ff.html");
			rd.forward(request, response);
			}
	}


	}


