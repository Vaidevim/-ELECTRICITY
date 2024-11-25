package city;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class connectivity {
	static PreparedStatement ps;
	static ResultSet rs;
	public static boolean login(String uname, String upass) {
		boolean states = false;
		try {
			ps = getConnection().prepareStatement("select * from ecity where username=? and password=?");
			ps.setString(1, uname);
			ps.setString(2, upass);
			rs = ps.executeQuery();  
			states =  rs.next();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return states;
	}
	public static boolean anotherlogin(String mob, String server) {
		boolean states = false;
		try {
			ps = getConnection().prepareStatement("select * from simpled where mobile=? and severno=?");
			ps.setString(1, mob);
			ps.setString(2, server);
			rs = ps.executeQuery();  
			states =  rs.next();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return states;
	}
	public static boolean register(String uname,String unumber,String service,String city) {
		boolean states = false;
		try {
			ps = getConnection().prepareStatement("insert into service (`severno`,`mobile`,`name`,`city`)value(?,?,?,?)");
            ps.setString(1, service);
            ps.setString(2,unumber);
            ps.setString(3,uname);
            ps.setString(4,city);
            if(ps.executeUpdate()>0){
                states=true;
            }
		} catch (Exception e) {
			System.out.println(e);
		}
		return states;
	}
	
	public static boolean billRegister(String service,String unit,String date) {
		boolean states = false;
		try {
			ps = getConnection().prepareStatement("INSERT INTO table (serviceno,unit,date)values(?,?,?)");
            ps.setString(1, service);
            ps.setString(2,unit);
            ps.setString(3,date);
            if(ps.executeUpdate()>0){
                states=true;
            }
		} catch (Exception e) {
			System.out.println(e);
		}
		return states;
	}
	
	public static boolean registerUser(String username, String password, String service) {
		boolean states = false;
		PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement("UPDATE `electrycity` SET `username` = ?, `password` = ? WHERE `serviceno` = ?;");
            ps.setString(1,username);
            ps.setString(2,password);
            ps.setString(3, service);
            if(ps.executeUpdate()>0){
                states=true;
            }
		} catch (Exception e) {
			System.out.println(e);
		}
		return states;
	}
	public static boolean registerTable (String vvcity) {
		boolean states = false;
		try {
			String createTableSQL = "CREATE TABLE `"+vvcity+"` ("
			        + "sno INT NOT NULL AUTO_INCREMENT,"
			        + "date VARCHAR(45) NOT NULL,"
			        + "currentunit VARCHAR(45) NOT NULL,"
			        + "statues VARCHAR(45) NOT NULL,"
			        + "PRIMARY KEY (sno),"
			        + "UNIQUE INDEX Date_UNIQUE (Date ASC) VISIBLE"
			        + ")";
			ps = getConnection().prepareStatement(createTableSQL); 
            if(ps.executeUpdate()==0){
                states=true;
            }
		} catch (Exception e) {
			System.out.println(e);
		}
		return states;
	}
	
	public static boolean update(String vvcity) {
		boolean states = false;
		PreparedStatement selectStatement = null;
		PreparedStatement updateStatement = null;
		ResultSet resultSet = null;
		try {
			selectStatement = getConnection().prepareStatement("select * from `"+vvcity+"` ORDER BY statues");
			resultSet = selectStatement.executeQuery();

		    if (resultSet.next()) {
		        int id = resultSet.getInt("sno");
		        String updatedValue = "paid";
		        updateStatement = getConnection().prepareStatement("UPDATE `"+vvcity+"` SET statues = ? WHERE sno = ?");
		        updateStatement.setString(1, updatedValue);
		        updateStatement.setInt(2, id);
		        if(updateStatement.executeUpdate()>0){
	                states=true;
	            }
		    }
		} catch (Exception e) {
			System.out.println(e);
		}
		return states;
	}
	
	public static String fach(String vvcity) {
		String temp = "start"; 
	      ResultSet rs = null;
		try {
			Statement st = getConnection().createStatement();
        	rs = st.executeQuery("select * from `"+vvcity+"` ORDER BY status");
        	String lastValue = null;
        	if(rs.next()) {
                lastValue = rs.getString("status");
                }
        	if (!lastValue.equals("paid")) {
        		rs = st.executeQuery("select * from `"+vvcity+"` ORDER BY unitCurrent DESC LIMIT 2");
	        	String lastValue1 = null;
	        	String lastValue2 = null;
	        	     while (rs.next()) {
	        	          String value = rs.getString("unitCurrent");      
	        	                if (lastValue1 == null) {
	        	                    lastValue1 = value;
	        	                } else {
	        	                    lastValue2 = value;    	                
	        	                    }
	        	            }
	        	            temp=amount(lastValue1, lastValue2);
        	}
        	else {
        		temp="0";
        	}
		}catch (Exception e) {
			System.out.println(e);
		}
		return temp;
		}
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/ebbill","root","371800");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return con;
	}
	
	public static String amount(String f,String s) {
		float f1=Float.parseFloat(f);
		float s1=Float.parseFloat(s);
		float fine = f1-s1;
		if (fine<=100) s="0";
		else if(fine>100&&fine<=200)s=String.valueOf(fine*2.5);
		else s=String.valueOf(fine*4);
		return s;
	}
	
}


