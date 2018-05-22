package com.social.controller;

import java.io.BufferedReader;
import java.io.FileReader;
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

import com.social.exception.*;
public class SearchPersonController extends HttpServlet{
//author:santhan
	private ServletConfig config;
	// Setting JSP page
	String page = "searchperson1.jsp";

	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		ResultSet rs;
		String name = request.getParameter("searchname");
		HttpSession session = request.getSession();
		session.setAttribute("searchname", name);
		System.out.println("------>"+name);
		response.setContentType("text/html");
		List dataList = new ArrayList();
		try {
			// Load the database driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@emu.cs.rmit.edu.au:1521/GENERAL.cs.rmit.edu.au", "s3675635",
						"iahfCyrR");
			// Select the data from the database
			String sql = "select * from people where name='" + name+"'";
			Statement s = con.createStatement();
			s.executeQuery(sql);
			rs = s.getResultSet();
			while (rs.next()) {
				// Add records into data list
				dataList.add(rs.getString("name"));
				dataList.add(rs.getString("photo"));
				dataList.add(rs.getString("college"));
				dataList.add(rs.getString("gender"));
				dataList.add(rs.getString("age"));
				dataList.add(rs.getString("state"));
			}
			if (dataList.size() == 0) {
				throw new NoAvailableException("No Data Available");
			}
			rs.close();
			s.close();
		} catch (NoAvailableException ex) {
			System.out.println("Caught");
			RequestDispatcher rd = request.getRequestDispatcher(page);
				out.println("<font color=red>No Data available</font>");
			// Print the message from MyException object
			System.out.println(ex.getMessage());
		} catch (Exception e) {
			System.out.println("Exception is ;" + e);
			
			

       	 BufferedReader b = new BufferedReader(new FileReader("people.txt"));

            String readLine = "";


            while ((readLine = b.readLine()) != null) {
           	 String[] words=readLine.split(",");
           	 if(words[0].equalsIgnoreCase(name)){
           		dataList.add(words[0]);
				dataList.add(words[1]);
				dataList.add(words[2]);
				dataList.add(words[3]);
				dataList.add(words[4]);
				dataList.add(words[5]);
           	 }
           	 	
            }
            request.setAttribute("data",dataList);
			//Disptching request
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		
			
		}
		request.setAttribute("data", dataList);
		// Disptching request
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
	}
}
