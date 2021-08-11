
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deleting customer</title>
    <style>
        form{
            width: 300px;
        }
    </style>
</head>
<body>
<h1>Delete customer</h1>
<p>
    <a href="/banking_system">Back to customer list</a>
</p>
<form method="post">
    <h3>Are you sure?</h3>
    <fieldset>
        <legend>Customer information</legend>
        <table>
            <tr>
                <td>ID: </td>
                <td>${requestScope["customer"].getId()}</td>
            </tr>
            <tr>
                <td>Name: </td>
                <td>${requestScope["customer"].getName()}</td>
            </tr>
            <tr>
                <td>Email: </td>
                <td>${requestScope["customer"].getEmail()}</td>
            </tr>
            <tr>
                <td>Address: </td>
                <td>${requestScope["customer"].getSalary()}</td>
            </tr>
            <tr>
                <td><input type="submit" value="Delete customer"></td>
                <td><a href="/banking_system>Back to customer list</a></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>