<%@ page import="com.wipro.restaurant.bean.RestaurantBean" %>
<!DOCTYPE html>
<html>
<head>
<title>Order Details</title>
</head>
<body>

<%
RestaurantBean bean = (RestaurantBean) request.getAttribute("order");

if(bean != null){
%>

<h2>Order Details</h2>

Record ID: <%=bean.getRecordId()%><br><br>
Customer Name: <%=bean.getCustomerName()%><br><br>
Food Item: <%=bean.getFoodItem()%><br><br>
Quantity: <%=bean.getQuantity()%><br><br>
Order Date: <%=bean.getOrderDate()%><br><br>
Total Amount: <%=bean.getTotalAmount()%><br><br>
Remarks: <%=bean.getRemarks()%><br><br>

<%
} else {
%>

<h3>No matching records exists! Please try again!</h3>

<%
}
%>

</body>
</html>
