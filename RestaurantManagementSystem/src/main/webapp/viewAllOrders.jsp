<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>View All Orders</title>
</head>
<body>

<h2>View All Order Records</h2>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="viewAllRecords">

    <input type="submit" value="View All Records">

</form>

</body>
</html>
