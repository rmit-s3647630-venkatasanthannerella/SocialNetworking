package com.social.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.social.exception.NoAvailableException;
import com.social.exception.NotToBeClassmatesException;
import com.social.exception.NotToBeColleaguesException;
import com.social.exception.NotToBeCoupledException;
import com.social.exception.NotToBeFriendsException;
import com.social.exception.TooYoungException;

public class AddRelationController extends HttpServlet{
//author:sri harsha
	private ServletConfig config;
	// Setting JSP page

	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String relationName = request.getParameter("relationName");
		String relationship = request.getParameter("relationship");
		ResultSet rs;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		RequestDispatcher rd=null;
		try {
			 rd = request.getRequestDispatcher("addedrelationship.jsp");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@emu.cs.rmit.edu.au:1521/GENERAL.cs.rmit.edu.au", "s3675635",
						"iahfCyrR");

			boolean dataAvailable=true;
			boolean exception=false;
			String name=null;
			String age=null;
			String name1=null;
			String age1=null;
			String sql = "select * from people where name='" + relationName+"'";
			Statement s = con.createStatement();
			s.executeQuery(sql);
			rs = s.getResultSet();
			while (rs.next()) {
				
				dataAvailable=false;
				name=rs.getString("name");
				age=rs.getString("age");
			}
			
			String sql1 = "select * from people where name='" + username+"'";
			Statement s1 = con.createStatement();
			s1.executeQuery(sql1);
			rs = s1.getResultSet();
			while (rs.next()) {
				
				name1=rs.getString("name");
				age1=rs.getString("age");
			}
				if (dataAvailable) {
					out.println("<H1>No Data Available");
					throw new NoAvailableException("No Data Available");
				}
				if(relationship.equalsIgnoreCase("classmates")){
					if((Integer.parseInt(age1)<3 || Integer.parseInt(age)<3) ){
					//	out.println("<H1>One of the persons age is <3 they cannot be Classmates");
								throw new NotToBeClassmatesException("One of the persons age is <3 they cannot be Classmates NotToBeClassmatesException");
							}
				}
				if(relationship.equalsIgnoreCase("couple")){
					if((Integer.parseInt(age)<16 && Integer.parseInt(age1)>16) ||
							(Integer.parseInt(age1)<16 && Integer.parseInt(age)>16)){
						//out.println("<H1>One of the persons age is <16 they cannot be couples");
								throw new NotToBeCoupledException("One of the persons age is <16 they cannot be couples NotToBeCoupledException");
							}
				}
			
				if(relationship.equalsIgnoreCase("colleagues")){
					if((Integer.parseInt(age1)<16 || Integer.parseInt(age)<16) ){
						//out.println("<H1>One of the persons age is <16 they cannot be colleagues");
								throw new NotToBeColleaguesException("One of the persons age is <16 they cannot be colleagues NotToBeColleaguesException");
							}
				}
				if(!relationship.equalsIgnoreCase("couple")){
					if(((Integer.parseInt(age1)<16)&&(Integer.parseInt(age)>16) && (Integer.parseInt(age)>3) && (Integer.parseInt(age1)>3)) ||
							((Integer.parseInt(age1)>16)&&(Integer.parseInt(age)<16))){
						//out.println("<H1>one person is adult and the other is young");
						throw new NotToBeFriendsException("one person is adult and the other is young");
						
					}
				}
				if((Integer.parseInt(age1)<16)&&(Integer.parseInt(age)<16) && (Integer.parseInt(age)>3) && (Integer.parseInt(age1)>3)
						&&((Integer.parseInt(age1)-Integer.parseInt(age)>=3)) 
						|| ((Integer.parseInt(age)-Integer.parseInt(age1)>=3))){
					//out.println("<H1>Age difference between two are greater than 3 years");
					throw new NotToBeFriendsException("Age difference between two are greater than 3 years");
				}
				
				if(Integer.parseInt(age)<16) {
					//out.println("<H1>User "+name+"is too young to be friends with you!!");
					throw new TooYoungException("User "+name+"is too young to be friends with you!!");
				}
			
			
			System.out.println(username+" "+relationName+" "+relationship);
			
			
				
			
			if(!exception){
				PreparedStatement ps = con
						.prepareStatement("insert into relations (NAME,RELATIVE_NAME,RELATION) values (?,?,?)");
				ps.setString(1, username);
				ps.setString(2, relationName);
				ps.setString(3, relationship);

				 rs = ps.executeQuery();

			}
			rd.forward(request, response);
		}catch(NoAvailableException objNoAvailableException){
			
			 rd = request.getRequestDispatcher("addrelation.jsp");
			out.println("<H1>No Data Available NoAvailableException");
			objNoAvailableException.printStackTrace();
		}catch(NotToBeClassmatesException NotToBeClassmatesException){
			
			 rd = request.getRequestDispatcher("addrelation.jsp");
			out.println("<H1>One of the persons age is <3 they cannot be Classmates NotToBeClassmatesException");
			NotToBeClassmatesException.printStackTrace();
		}catch(NotToBeColleaguesException NotToBeColleaguesException){
			
			 rd = request.getRequestDispatcher("addrelation.jsp");
			out.println("<H1>One of the persons age is <16 they cannot be colleagues NotToBeColleaguesException");
			NotToBeColleaguesException.printStackTrace();
		}catch(NotToBeCoupledException notToBeCoupledException){
			
			 rd = request.getRequestDispatcher("addrelation.jsp");
			out.println("<H1>One of the persons age is <16 they cannot be couples NotToBeCoupledException");
			notToBeCoupledException.printStackTrace();
		}catch(TooYoungException TooYoungException){
			
			 rd = request.getRequestDispatcher("addrelation.jsp");
			out.println("<H1>User is too young to be friends with you!! TooYoungException");
		}catch(NotToBeFriendsException NotToBeFriendsException){
			
			 rd = request.getRequestDispatcher("addrelation.jsp");
			out.println("<H1>one person is adult and the other is young "
					+ "or Age difference between two are greater than 3 years NotToBeFriendsException");
			NotToBeFriendsException.printStackTrace();
		} catch (Exception e2) {
			 rd = request.getRequestDispatcher("addedrelationship.jsp");
				out.println("<H1>Facing issues while connecting to database.<br>"
						+ "Inserting data to relation.txt<br>");
		            BufferedWriter writer = new BufferedWriter(new FileWriter("relations.txt", true));
		           
				    writer.append(username+","+relationName+","+relationship);
				    writer.newLine();
				    writer.close();
				rd.include(request, response);
		}

	}

}