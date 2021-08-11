<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 8/10/2021
  Time: 11:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'></link>
    <script type="text/javascript"
            src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body class="container" >
<div align="center">
    <caption><h2>Form Deposit Customers</h2></caption>
    <h2><a href="/ManagerCustomer">Manager Customer</a></h2>
    <form class="form-group" novalidate method="post">
        <div class="row">
            <div class="col-6">
                <label >User Name: </label>
                <input  type="text" name="name" size="45" class="form-control" disabled
                        value="<c:out value='${customer.getName()}'/>" >
            </div>
            <div class="col-md-6">
                <label>Phone:</label>
                <input type="text" name="phone" id="phone" size="45" class="form-control" disabled
                       value="<c:out value='${customer.getPhone()}'/>">
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <label>Email:</label>
                <input type="text" name="email" id="email" size="45" class="form-control" disabled
                       value="<c:out value='${customer.getEmail()}'/>">
            </div>
            <div class="col-md-6">
                <label>Balance</label>
                <input type="text" name="balance" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-warning">Update</button>
            <a href="/banking_system">Back to customer list</a>
        </div>
    </form>
</div>

<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
</body>
</html>
