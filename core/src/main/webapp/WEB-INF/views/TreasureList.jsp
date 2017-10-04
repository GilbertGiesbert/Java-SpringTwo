<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Treasure List</title>
    </head>
    <body>
        <div align="center">
	        <h1>Treasure List</h1>
	        <h2><a href="new">New Treasure</a></h2>
	        
        	<table border="1">
	        	<th>No</th>
	        	<th>Id</th>
	        	<th>name</th>
	        	<th>valueInCents</th>
	        	<th>Actions</th>
	        	
				<c:forEach var="treasure" items="${treasureList}" varStatus="status">
		        	<tr>
		        		<td>${status.index + 1}</td>
		        		<td>${treasure.id}</td>
						<td>${treasure.name}</td>
						<td>${treasure.valueInCents}</td>
						<td>
							<a href="edit?id=${treasure.id}">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="delete?id=${treasure.id}">Delete</a>
						</td>
		        	</tr>
				</c:forEach>	        	
        	</table>
        </div>
    </body>
</html>
