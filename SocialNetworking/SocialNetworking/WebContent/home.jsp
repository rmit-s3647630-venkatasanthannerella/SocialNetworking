<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display</title>
<style>
table#nat{
	width: 50%;
	background-color: grey;
	padding: 10px;
	border: 5px solid grey;
	margin: 0px
}
</style>
</head>
<body>  
<% String name =  request.getParameter("fullname");
	String photo = request.getParameter("photo");
	String age = request.getParameter("age");
	String college = request.getParameter("college");
	String gender = request.getParameter("gender");
	String state = request.getParameter("state"); %>
<table id ="nat">
<tr>
	<td>Full Name</td>
	<td><%= name %></td>
</tr>
<tr>
	<td>photo</td>
	<td><%= photo %></td>
</tr>
<tr>
	<td>Age</td>
	<td><%= age %></td>
</tr>
<tr>
	<td>college</td>
	<td><%= college %></td>
</tr>
<tr>
	<td>gender</td>
	<td><%= gender %></td>
</tr>
<tr>
	<td>state</td>
	<td><%= state %></td>
</tr>
</table>
</body>
</html>