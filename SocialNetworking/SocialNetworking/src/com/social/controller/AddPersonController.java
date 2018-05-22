package com.social.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.social.exception.NoSuchAgeException;
import com.social.exception.NotToBeFriendsException;

/**
 * Servlet implementation class UserDataServlet
 */
public class AddPersonController extends HttpServlet {
	//author:santhan	
	
	private static final long serialVersionUID = 1L;
   
	public AddPersonController() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("fullname");
		String photo = request.getParameter("photo");
		String age = request.getParameter("age");
		String college = request.getParameter("college");
		String gender = request.getParameter("gender");
		String state = request.getParameter("state");
		if (name.isEmpty() || college.isEmpty() || age.isEmpty() || gender.isEmpty() || state.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("addperson.jsp");
			out.println("<font color=red>Please fill all the fields</font>");
			rd.include(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			
			
			try {
				if(Integer.parseInt(age)<0 || Integer.parseInt(age)>150){
					out.println("<font color=red>Age cannot be less than 0 or greater than 150</font>");
					throw new NoSuchAgeException("Age cannot be less than 0 or greater than 150");
				}
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@emu.cs.rmit.edu.au:1521/GENERAL.cs.rmit.edu.au", "s3675635",
						"iahfCyrR");

				PreparedStatement ps = con.prepareStatement(
						"insert into people (NAME,PHOTO,COLLEGE,GENDER,AGE,STATE) values (?,?,?,?,?,?)");
				ps.setString(1, name);
				ps.setString(2, photo);
				ps.setString(3, college);
				ps.setString(4, gender);
				ps.setString(5, age);
				ps.setString(6, state);
				ResultSet rs = ps.executeQuery();
				rd.forward(request, response);
			}catch(NoSuchAgeException exp){ 
				
			}catch (Exception e2) {
				 rd = request.getRequestDispatcher("home.jsp");
				out.println("<font color=red>Facing issues while connecting to database.<br>"
						+ "Inserting data to people.txt</font>");
				      System.out.println(new File("").getAbsolutePath());
		            BufferedWriter writer = new BufferedWriter(new FileWriter("people.txt", true));
		            
				    writer.append(name+","+photo+","+college+","+gender+","+age+","+state);
				    writer.newLine();
				    writer.close();
				rd.include(request, response);
			}
			finally {
				out.close();
			}

		}

	}
}


