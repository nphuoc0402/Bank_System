
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
<html>
<head>
    <title>User Management Application</title>
    <style>
        .message{
            color:white;
        }
        .error{
            color: red;
            font-family: Arial;
            font-weight: bold;
        }
        .success{
            color: lightgreen;
            font-weight: bold;
            font-family: Arial;
        }

    </style>
</head>
<body>
<center>
    <h2>
        <a href="/banking_system">List All Users</a>
    </h2>
</center>
<div align="center" class="container">
    <form class="needs-validation" novalidate method="post">
        <h2>Transfer Transaction</h2>

        <div class="form-row">
            <div class="col-md-4 mb-3">
                <label for="validationCustom01">ID Sender</label>
                <input  type="text" class="form-control" name="idS" id="validationCustom01" value="<c:out value='${customer.getId()}'/>" required>
            </div>
            <div class="col-md-4 mb-3">
                <label >Customer Name</label>
                <input  type="text" name="name" size="45" class="form-control"
                       value="<c:out value='${customer.getName()}'/>" required>
            </div>
            <div class="col-md-4 mb-3">
                <label >Balance</label>
                <input  type="text" name="balance" class="form-control" value="<c:out value='${customer.getSalary()}'/>" >

            </div>
        </div>
        <div class="form-row">

            <div class="col-md-3 mb-3">
                <label >ID Receiver</label>
                <input type="text" name="idR" class="form-control" required >
                <div class="invalid-feedback">
                    Id Customer is required
                </div>
            </div>
            <div class="col-md-3 mb-3">
                <label>Amount</label>
                <input onchange="myFunction()" type="text" name="amount" id="amount" size="15" class="form-control" required/>
                <div class="invalid-feedback">
                    Amount is required
                </div>
            </div>
            <div class="col-md-3 mb-3">
                <label >Fee Percent</label>
                <input  type="text" id="feepercent" name="feepercent" class="form-control" value="<c:out value='${feePercent}'/>" >

            </div>
            <div class="col-md-3 mb-3">
                <label >Fee Transaction</label>
                <input  id="Input"  type="text" name="feetransaction" class="form-control" >

            </div>
        </div>

        <button class="btn btn-primary" type="submit">Transfer Transaction</button>
    </form>
    <div>
        <c:if test='${requestScope["success"] != null}'>
            <span class="message success">${requestScope["success"]}</span>
        </c:if>
        <c:if test='${requestScope["error"] != null}'>
            <span class="message error">${requestScope["error"]}</span>
        </c:if>
    </div>
    <script>
        function myFunction() {
            var x = document.getElementById("amount").value;
            var y = document.getElementById("feepercent").value;
            document.getElementById("Input").value = (x * y)/100;
        }
    </script>
</div>
</body>
</html>