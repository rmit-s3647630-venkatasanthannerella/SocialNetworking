package com.social.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class DeletePersonController extends HttpServlet{
//author:sriharsha
	private ServletConfig config;
	// Setting JSP page
	String page = "deleted.jsp";

	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("searchname");
		System.out.println("------>"+name);
		response.setContentType("text/html");
		try {
			// Load the database driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@emu.cs.rmit.edu.au:1521/GENERAL.cs.rmit.edu.au", "s3675635",
						"iahfCyrR");
			// Select the data from the database
			String sql = "delete from people where name='" + name+"'";
			Statement s = con.createStatement();
			s.executeQuery(sql);

			s.close();
		} catch (Exception e) {
			
			RequestDispatcher rd = request.getRequestDispatcher("addedrelationship.jsp");
			PrintWriter out = response.getWriter();
				out.println("<font color=red>Facing issues while connecting to database.<br>"
						+ "deleting from people.txt<br></font>");
				File inputFile = new File("people.txt");
		        File tempFile = new File("people1.txt");
		        tempFile.createNewFile();
		            BufferedReader objBuffer = new BufferedReader(new FileReader("people.txt"));
		            BufferedWriter writer = new BufferedWriter(new FileWriter("people1.txt", true));
		             String readLine = "";
		             while ((readLine = objBuffer.readLine()) != null) {
		            	 System.out.println(readLine);
		            	 String[] words=readLine.split(",");
						if(!words[0].equalsIgnoreCase(name)){
							 writer.append(words[0]+","+words[1]+","+words[2]+","+words[3]+","+words[4]+","+words[4]);
							 writer.newLine();
						}
		             }
		             boolean successfull = false;
				    writer.close();
				    objBuffer.close();
				    if(inputFile.exists()){
				    	System.out.println("**********"+inputFile.getAbsolutePath());
				    	try{
				    		if(inputFile.delete())
					         {
					             System.out.println("File deleted successfully");
					         }
				    	}catch(Exception exp){
				    		exp.printStackTrace();
				    	}
				    	 
				    	 successfull = tempFile.renameTo(inputFile);
				    }
				    
				    System.out.println(successfull);
				rd.include(request, response);
			System.out.println("Exception is ;" + e);
		}
		// Dispatching request
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
	}
}
