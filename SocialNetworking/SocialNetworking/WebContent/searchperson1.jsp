<%@page language="java" import="java.util.*" %>
<html>
<head>
<title>Data Page</title>
</head>
<body> 
<form action="DeletePersonController" method="post">
	<table border="1" width="303">
	  <tr>
		<td width="119"><b>Name</b></td>
		<td width="168"><b>Photo</b></td>
		<td width="168"><b>College</b></td>
		<td width="168"><b>Age</b></td>
		<td width="168"><b>Gender</b></td>
		<td width="168"><b>State</b></td>
	  </tr>
	<%Iterator itr;%>
	<% List data= (List)request.getAttribute("data");
	/* String deleteName=null;
	for (itr=data.iterator(); itr.hasNext(); ){
		deleteName=(String)itr.next();
	}
	session.setAttribute("deleteName", deleteName); */
		for (itr=data.iterator(); itr.hasNext(); )
		{
			
	%>
	<tr>
		<td width="119"><%=itr.next()%></td>
		<td width="168"><%=itr.next()%></td>
		<td width="168"><%=itr.next()%></td>
		<td width="168"><%=itr.next()%></td>
		<td width="168"><%=itr.next()%></td>
		<td width="168"><%=itr.next()%></td>
		<td><input type="submit" value="delete"/></td>
		<%-- <td><input type="hidden" name="deletePerson" value=<%=deleteName %>/></td> --%>
		<td><input type="button" onclick="window.location.href='addrelation.jsp'" value="Add Relation" /></td>
	</tr>
	<%}%>
	</table>
	
</form>
</body>
</html>
