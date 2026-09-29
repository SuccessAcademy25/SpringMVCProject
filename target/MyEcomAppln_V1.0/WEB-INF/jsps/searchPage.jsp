<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="search-container">

    <h1 class="search-title">E-COMMERCE APPLICATION</h1>
    <h3 class="search-title">Customer Search Form</h3>

    <div class="search-form">
        <form onsubmit="searchCustomers(event, this)">
            <table>
                <tr>
                    <td>ID:</td>
                    <td><input type="text" name="id" maxlength="10"></td>

                    <td>Email:</td>
                    <td><input type="text" name="email"></td>

                    <td>Phone:</td>
                    <td><input type="text" name="phone" maxlength="10"></td>

                    <td><input type="submit" value="Search"></td>
                </tr>
            </table>
        </form>
    </div>

    <table class="customer-table" border="3">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>City</th>
                <th>Salary</th>
                <th>Gender</th>
                <th>DOB</th>
                <th>Delete</th>
                <th>Update</th>
            </tr>
        </thead>

        <tbody>

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
                    <td>
                      <button class="btn-delete" onclick="deleteCustomer('${row.custId}')">DELETE</button>
                    </td>
                    <td>
                        <a class="btn-delete" href="javascript:void(0);" 
                           onclick="openUpdateModal('${row.custId}', '${row.custName}', '${row.custEmail}', '${row.phone}', '${row.custCity}', '${row.custsalary}', '${row.gender}', '${row.date}')">
                            UPDATE
                        </a>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty LIST}">
                <tr>
                    <td colspan="10" class="no-data">No Customer Found</td>
                </tr>
            </c:if>

        </tbody>
    </table>

</div>