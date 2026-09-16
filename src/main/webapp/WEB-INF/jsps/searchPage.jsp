<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#669999">
<center>
<br><h1>ECOMERCE APPLICATION</h1><br/>
<h4>Customer Search Form</h4>
<div>
<form name="FORM" action="searchCustomers.htm">
<table>
	<tr>
		<td>Id:</td>
		<td><input type="text" name="id" maxlength="10"
			style="color: red; font-size: 20"></input></td>
	
		<td>Email:</td>
		<td><input type="text" name="email"
			style="color: red; font-size: 20"></input></td>
	
	
		<td>Phone:</td>
		<td><input type="text" name="phone" maxlength="10"
			style="color: red; font-size: 20"></input></td>
	
		<td colspan="2" align="centre"><input type="SUBMIT"
			value="Search" style="color: red; font-size: 20"></input></td>
	</tr>
</table>
<div align="center">
        <table border="1" cellpadding="5" width="40%">
            <caption><h2>List Of Customer</h2></caption>
            <tr bgcolor="green">
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>City</th>
                <th>Salary</th>
                <th>Gender</th>
                <th>Date</th>
                <th>delete</th>
                <th>update</th>
                 
            </tr>
            
            <c:forEach var="row" items="${LIST}">
                <tr>
                    <td><c:out value="${row.custId}" /></td>
                    <td><c:out value="${row.custName}" /></td>
                    <td><c:out value="${row.custEmail}" /></td>
                    <td><c:out value="${row.phone}" /></td>
                    <td><c:out value="${row.custCity}" /></td>
                    <td><c:out value="${row.custsalary}" /></td>
                    <td><c:out value="${row.gender}" /></td>
                    <td><c:out value="${row.date}" /></td>
                    
                     <td><a href="deleteCustomers.htm?CustId=${row.custId}">DELETE </a></td>
                      <td><a href="updateCustomers.htm?CustId=${row.custId}">UPDATE</a></td>
                    
                </tr>
            </c:forEach>
        </table>
    </div>
</form>
 

<div>
<h4>${DELETEMSG}</h4>
</div>
</div>
<div>
<h4><a href="index.jsp">HOME PAGE</a></h4>
</div>
<font size="4">All right reserved &copy;Hitendra@Kumar</font>
</center>
</body>
</html>