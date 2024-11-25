package Electricity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ecc {
	public static boolean validate(String customername, String address, String unit,String amount){
		boolean status=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/payymentec","root","371800");
			
		PreparedStatement ps=con.prepareStatement("INSERT INTO projectc (customername, address, unitconsumed, amount)values(?,?,?,?)");
		ps.setString(1, customername);
		ps.setString(2, address);
		ps.setString(3, unit);
		ps.setString(4, amount);
		   
		
		 if(ps.executeUpdate()>0) {
			status=true;
		 }
		}catch(Exception e) {System.out.println(e);}
		 return status;
		 }
}
