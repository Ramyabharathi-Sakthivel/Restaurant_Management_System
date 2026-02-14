<%@ page
	import="java.util.List,com.wipro.restaurant.bean.RestaurantBean"%>
<!DOCTYPE html>
<html>
<head>
<title>All Orders</title>
</head>
<body>

	<%
	List<RestaurantBean> list = (List<RestaurantBean>) request.getAttribute("orders");

	if (list != null && !list.isEmpty()) {
	%>

	<h2>All Order Records</h2>

	<table border="1">
		<tr>
			<th>Record ID</th>
			<th>Customer Name</th>
			<th>Food Item</th>
			<th>Quantity</th>
			<th>Order Date</th>
			<th>Total Amount</th>
			<th>Remarks</th>
		</tr>

		<%
		for (RestaurantBean b : list) {
		%>
		<tr>
			<td><%=b.getRecordId()%></td>
			<td><%=b.getCustomerName()%></td>
			<td><%=b.getFoodItem()%></td>
			<td><%=b.getQuantity()%></td>
			<td><%=b.getOrderDate()%></td>
			<td><%=b.getTotalAmount()%></td>
			<td><%=b.getRemarks()%></td>
		</tr>
		<%
		}
		%>

	</table>

	<%
	} else {
	%>

	<h3>No records available!</h3>

	<%
	}
	%>

</body>
</html>
