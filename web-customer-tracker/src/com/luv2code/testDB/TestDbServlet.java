package com.luv2code.testDB;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//set up connection varaibles
		String url="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&servertimezone=UTC";
		String user="springstudent";
		String pass="springstudent";
		String driver="com.mysql.cj.jdbc.Driver";
		
		//get connection to db
		try {
			
			PrintWriter out=response.getWriter();
			
			out.println("Connecting to db..=>  "+url);
			
			Class.forName(driver);
			
			Connection myConn=DriverManager.getConnection(url, user, pass);
			
			out.println();
			out.println();
			out.println();
			
			out.println("Connected..!");
			
			myConn.close();
		
		}
		catch(Exception exc) {
			exc.printStackTrace();
			throw new ServletException(exc);
		}
	}

}
