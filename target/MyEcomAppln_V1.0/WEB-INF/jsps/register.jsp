<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<div style="max-width: 600px; margin: 0 auto; font-family: Arial, sans-serif; padding: 10px;">
    <h2 style="text-align: center; color: #333;">E-COMMERCE APPLICATION</h2>
    <h4 style="text-align: center; color: #555;">Customer Application Form</h4>

    <form id="registerForm" onsubmit="event.preventDefault(); submitRegistration(this);">
        <table style="width: 100%; border-spacing: 12px; text-align: left;">
            <tr>
                <td style="width: 30%;"><strong>Name:</strong></td>
                <td><input type="text" name="custName" style="width: 100%; padding: 8px; font-size: 15px; box-sizing: border-box;" required></td>
            </tr>
            <tr>
                <td><strong>Email:</strong></td>
                <td><input type="email" name="custEmail" style="width: 100%; padding: 8px; font-size: 15px; box-sizing: border-box;" required></td>
            </tr>
            <tr>
                <td><strong>Phone:</strong></td>
                <td><input type="text" name="phone" maxlength="10" style="width: 100%; padding: 8px; font-size: 15px; box-sizing: border-box;" required></td>
            </tr>
            <tr>
                <td><strong>City:</strong></td>
                <td><input type="text" name="custCity" style="width: 100%; padding: 8px; font-size: 15px; box-sizing: border-box;"></td>
            </tr>
            <tr>
                <td><strong>Salary:</strong></td>
                <td><input type="number" step="0.01" name="custsalary" style="width: 100%; padding: 8px; font-size: 15px; box-sizing: border-box;"></td>
            </tr>
            <tr>
                <td><strong>DoB:</strong></td>
                <td><input type="date" name="date" style="width: 100%; padding: 8px; font-size: 15px; box-sizing: border-box;"></td>
            </tr>
            <tr>
                <td><strong>Gender:</strong></td>
                <td>
                    <label style="margin-right: 20px;">
                        <input type="radio" name="gender" value="Male" checked> Male
                    </label>
                    <label>
                        <input type="radio" name="gender" value="Female"> Female
                    </label>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center; padding-top: 15px;">
                    <input type="submit" value="Register" style="background-color: #28a745; color: white; border: none; padding: 10px 30px; font-size: 16px; cursor: pointer; border-radius: 4px; font-weight: bold;">
                </td>
            </tr>
        </table>
    </form>

    <div id="registerMsg" style="text-align: center; color: red; margin-top: 15px; font-weight: bold;"></div>
</div>