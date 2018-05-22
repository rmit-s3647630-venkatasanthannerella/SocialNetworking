package com.social.controller;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.social.exception.NoAvailableException;

public class ViewPersonController extends HttpServlet{
//author:santhan
	private ServletConfig config;
	//Setting JSP page
	String page="viewperson.jsp";

	public void init(ServletConfig config)
	  throws ServletException{
		 this.config=config;
	   }
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		
		PrintWriter out = response.getWriter();
		ResultSet rs;
		response.setContentType("text/html");
		List dataList=new ArrayList(); 
			try {
			 // Load the database driver
				Class.forName("oracle.jdbc.driver.OracleDriver");  
		    	Connection con=DriverManager.getConnection(  
		    	"jdbc:oracle:thin:@emu.cs.rmit.edu.au:1521/GENERAL.cs.rmit.edu.au", "s3675635",
						"iahfCyrR");  
			//Select the data from the database
			String sql = "select * from people";
			Statement s = con.createStatement();
			s.executeQuery (sql);
			rs = s.getResultSet();
			while (rs.next ()){
				//Add records into data list
				dataList.add(rs.getString("name"));
				dataList.add(rs.getString("photo"));
				dataList.add(rs.getString("college"));
				dataList.add(rs.getString("gender"));
				dataList.add(rs.getString("age"));
				dataList.add(rs.getString("state"));
			}
			if(dataList.size()==0) {
				throw new NoAvailableException("No Data Available");
			}
			rs.close ();
			s.close ();
			}
			catch (NoAvailableException ex){
	            System.out.println("Caught");
	 
	            // Print the message from MyException object
	            System.out.println(ex.getMessage());
	        }catch(Exception e){
	        	 BufferedReader b = new BufferedReader(new FileReader("people.txt"));

	             String readLine = "";


	             while ((readLine = b.readLine()) != null) {
	            	 System.out.println(readLine);
	            	 String[] words=readLine.split(",");
	            	 dataList.add(words[0]);
	 				dataList.add(words[1]);
	 				dataList.add(words[2]);
	 				dataList.add(words[3]);
	 				dataList.add(words[4]);
	 				dataList.add(words[5]);
	             }
	             request.setAttribute("data",dataList);
	 			//Disptching request
	 			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			}
			request.setAttribute("data",dataList);
			//Disptching request
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			if (dispatcher != null){
				dispatcher.forward(request, response);
			} 
	}
}