
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

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'></link>
    <style>
        label.error{
            color: red;
        }
    </style>
    <script type="text/javascript"
            src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="/resource/js/jquery.validate.min.js"></script>
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
                <input  type="text" class="form-control"  name="idS" id="validationCustom01" value="<c:out value='${customer.getId()}'/>"  >
            </div>
            <div class="col-md-4 mb-3">
                <label >Customer Name</label>
                <input  type="text" name="name" size="45" class="form-control"
                       value="<c:out value='${customer.getName()}'/>"  disabled>
            </div>
            <div class="col-md-4 mb-3">
                <label >Balance</label>
                <input  type="text" name="balance" class="form-control" value="<c:out value='${customer.getSalary()}'/>" disabled>

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
                <input  id="Input"  type="text" name="feetransaction" class="form-control" disabled>

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
        $('.alert-success').delay(4 * 1000).slideUp(500, function () {
            $('this').alert('close');
        });

        $('.alert-danger').delay(4 * 1000).slideUp(500, function () {
            $('this').alert('close');
        });
    </script>
    <script>
        $(function() {
            $(".needs-validation").validate({
                rules: {
                    onfocusout: false,
                    onkeyup: false,
                    onclick: false,
                    idS: {
                        required : true
                    },
                    idR: {
                        required: true
                    },
                    amount: {
                        required: true,
                    },

                },
                messages: {
                    idS: {
                        required: "Please enter your name",
                        number: "ID Sender is required"
                    },
                    idR:{
                        required: "Please provide a phone",
                        number: "ID Receiver is required"
                    },
                    amount: {
                        required: "Please provide a email",
                        number: "amount is required"

                    },

                },
                submitHandler: function(form) {
                    form.submit();
                }
            });
        });
    </script>
</div>
</body>
</html>