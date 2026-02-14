<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>View Order</title>
</head>
<body>

<h2>View Order Record</h2>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="viewRecord">

    Customer Name:
    <input type="text" name="customerName"><br><br>

    Order Date:
    <input type="date" name="orderDate"><br><br>

    <input type="submit" value="View Order">

</form>

</body>
</html>
