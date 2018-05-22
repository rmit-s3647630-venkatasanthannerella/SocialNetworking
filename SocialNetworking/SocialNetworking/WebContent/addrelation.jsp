<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="AddRelationController" method="post">

<% String  userName = session.getAttribute("searchname").toString();  %>

<input type="text" name="username" value="<%=userName%>" />
Enter Relations Name <input type="text" name="relationName"/>
Enter RelationShip <input type="text" name="relationship"/>
<input type="submit" value="Add Relationship"/>
</form>
</body>
</html>