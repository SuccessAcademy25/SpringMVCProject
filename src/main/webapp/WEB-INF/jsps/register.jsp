<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isELIgnored="false" %>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>registration</title>
</head>
<body bgcolor="pink">

<center><br>
<h1>ECOMERCE APPLICATION</h1>
<br />
<h4>Customer Application Form</h4>
<div>
<form name="FORM" action="addCustomers.jsa">
<table>
	<tr>
		<td>Name:</td>
		<td><input type="text" value="${customer.custName}" name="custName" style="color: red; font-size: 20"></input></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><input type="text" name="custEmail" value="${customer.custEmail}"  color: red; font-size: 20"></input></td>
	</tr>
	<tr>
		<td>Phone:</td>
		<td><input type="text" name="phone" value="${customer.phone}" maxlength="10" style="color: red; font-size: 20"></input></td>
	</tr>
	<tr>
		<td>City:</td>
		<td><input type="text" name="custCity" value="${customer.custCity}" style="color: red; font-size: 20"></input></td>
	</tr>
	<tr>
		<td>Salary:</td>
		<td><input type="text" name="custsalary" value="${customer.custsalary}" style="color: red; font-size: 20"></input></td>
	</tr>
	<tr>
		<td>DoB:</td>
		<td align="center" colspan="2"><input type="date" name="date" value="${customer.date}"></td>


	</tr>
	<tr>
		<td align="center" colspan="5">
		<INPUT TYPE="Radio" Name="gender" 
			Value="Male">Male <INPUT TYPE="Radio" Name="gender"
			Value="Female">Female</td>
	</tr>
	<tr>
		<td colspan="2" align="centre"><input type="SUBMIT"
			value="Register" style="color: green; font-size: 20"></input></td>
	</tr>
</table>
</form>
</div>
<div>
<h4>${MSG}</h4>
</div>
<div>
<h4><a href="index.jsp">HOME PAGE</a></h4>
</div>
<font size="4">All right reserved &copy;Jitendra@Kumar</font>
</center>
</body>
</html>