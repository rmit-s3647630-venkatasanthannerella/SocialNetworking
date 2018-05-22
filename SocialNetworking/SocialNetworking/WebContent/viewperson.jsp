<%@page language="java" import="java.util.*" %>
<html>
<head>
<title>Data Page</title>
</head>
<body> 
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
	</tr>
	<%}%>
	</table>
</body>
</html>
