<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Data</title>
</head>
<style>
div.ex {
	text-align: right width:50px;
	padding: 10px;
	border: 5px solid grey;
	margin: 0px
}
</style>
<body>
	<h1>Registration Form</h1>
	<div class="ex">
		<form action="AddPersonController" method="post">
			<table style="with: 5%">
				<tr>
					<td>Full Name</td>
					<td><input type="text" name="fullname" /></td>
				</tr>
				<tr>
					<td>Photo</td>
					<td><input type="text" name="photo" /></td>
				</tr>
				<tr>
					<td>Studies At</td>
					<td><input type="text" name="college" /></td>
				</tr>
				<tr>
					<td>Gender</td><td><input type="radio" name="gender" value="M">Male
					<input type="radio" name="gender" value="F">Female</td>
				</tr>
				<tr>
					<td>Age</td>
					<td><input type="text" name="age" /></td>
				</tr>
				<tr>
					<td>State</td>
					<td><input type="text" name="state" /></td>
				</tr>
			</table>
			<input type="submit" value="Add Person" />
		</form>
	</div>
</body>
</html>