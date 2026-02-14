<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Order</title>
</head>
<body>

<h2>Add Order Record</h2>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="newRecord">

    Customer Name:
    <input type="text" name="customerName"><br><br>

    Food Item:
    <input type="text" name="foodItem"><br><br>

    Quantity:
    <input type="number" name="quantity"><br><br>

    Order Date:
    <input type="date" name="orderDate"><br><br>

    Total Amount:
    <input type="number" name="totalAmount"><br><br>

    Remarks:
    <input type="text" name="remarks"><br><br>

    <input type="submit" value="Add Order">

</form>

</body>
</html>
