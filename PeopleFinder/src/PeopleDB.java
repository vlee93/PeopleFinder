import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


@WebServlet("/PeopleDB")
public class PeopleDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	static Connection conn;

    public PeopleDB() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Statement stmt = null;
		String list = "";
		
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
	            } catch (Exception e) {
	                e.printStackTrace();}
		try{
			String enter = request.getParameter("lastname");
			ResultSet rs = stmt.executeQuery("Select CustomerID, Firstname, Lastname from customers where lastname like '" + enter + "%' OR company like '" + enter + "%'");
			list += "<table class=\"table table-bordered\">";
			list += "<thead><tr><th>CustomerID</th><th>First Name</th><th>Last Name</th></tr></thead><tbody>";
			while (rs.next())
			{
				list += "<tr>";
				list += "<td>";
				list += "<a href=\"Details?CustomerID=" + rs.getString("CustomerID") + "\">";
				list += rs.getString("CustomerID");
	    		list += "</a>";
	    		list += "</td>";
				list += "<td>";
				list += rs.getString("Firstname");
				list += "</td>";
				list += "<td>";
				list += rs.getString("Lastname");
	    		list += "</td>";
	    		list += "</tr>";
			}
			list += "</table></tbody>";
		}catch (Exception e) {
            e.printStackTrace();}
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);
		getServletContext().getRequestDispatcher("/SearchResult.jsp").forward(request, response);

	}

}
