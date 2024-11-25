package Electricity;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/fourth")
public class fourth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fourth() {
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
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
	String cn=request.getParameter("customername");
	String ad=request.getParameter("address");
	String uc=request.getParameter("unitconsumed");
	String am=request.getParameter("amount");
	
	if(ecc.validate(cn, ad, uc, am)){
		RequestDispatcher rd=request.getRequestDispatcher("ecppp");
		rd.forward(request,response);
		
	}
	
	else {
		out.print(" payment not add");
		RequestDispatcher rd=request.getRequestDispatcher("pay.html");
		rd.include(request,response);
		
		
	}
	  out.close();
	}
	
	

		
	}


