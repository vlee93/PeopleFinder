import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection conn;
       

    public Details() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String custID = request.getParameter("CustomerID");
		System.out.println("custID " + custID);
		response.setContentType("text/html");
		String allDetail = "";
		Statement stmt = null;
		
		try {
		String url = "jdbc:oracle:thin:testuser/password@localhost"; 
		 Class.forName("oracle.jdbc.driver.OracleDriver");
	    
		//properties for creating connection to Oracle database
	    Properties props = new Properties();
	    props.setProperty("user", "testdb");
	    props.setProperty("password", "password");
	   
	    //creating connection to Oracle database using JDBC
	    
			conn = DriverManager.getConnection(url,props);
			stmt = conn.createStatement();
	    	
			String query = "select c.customerid, c.fullname, c.street, l.city, l.states, t.company from customers c inner join Companies t on c.companyID = t.companyID inner join Locations l on c.locationID = l.locationID where customerID = " + custID;
			System.out.println(query);
    		ResultSet result = stmt.executeQuery(query);
    		result.next();

    		
    		allDetail += "<table class=\"table table-bordered\">";
    		allDetail += "<thead><tr><th>CustomerID</th><th>Full Name</th><th>Street</th><th>City</th><th>State</th><th>Company</th></tr></thead><tbody>";
    		allDetail += "<tr>";
    		allDetail += "<td>";
    		allDetail += "<a href=\"Details?customerID=" + result.getString("customerid") + "\">";
    		allDetail+= result.getString("customerid");
    		allDetail += "</a>";
    		allDetail+= "</td>";
    		allDetail += "<td>";
    		allDetail+= result.getString("fullname");
    		allDetail+= "</td>";
    		allDetail += "<td>";
    		allDetail += result.getString("street");
    		allDetail+= "</td>";
    		allDetail += "<td>";
    		allDetail += result.getString("city");
    		allDetail+= "</td>";
    		allDetail += "<td>";
    		allDetail += result.getString("states");
    		allDetail+= "</td>";
    		allDetail += "<td>";
    		allDetail += result.getString("company");
    		allDetail+= "</td>";
    		allDetail += "</tr>";
    		allDetail += "</table></tbody>";
    		
		}catch (Exception e) {

			e.printStackTrace();}
		
		request.setAttribute("allDetail", allDetail);	
		getServletContext().getRequestDispatcher("/peopledetails.jsp").forward(request, response);
		}
	}


