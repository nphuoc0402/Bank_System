<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 8/11/2021
  Time: 6:14 AM
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
<body>
<div class="container">
    <h2><a href="/banking_system">Manager Customer</a></h2>
    <table class="table table-striped">
        <thead class="thead-dark">
        <th scope="col">ID</th>
        <th scope="col">Name Sender</th>
        <th scope="col">Name Receiver</th>
        <th scope="col">Amount</th>
        <th scope="col">Fee_percent</th>
        <th scope="col">Fee_Transaction</th>
        </thead>
        </thead>

        <c:forEach var="transfer" items="${transfers}">
            <tr>
                <td><c:out value="${transfer.getId()}"/></td>
                <td><c:out value="${customers.get(transfer.getIdSender()-1).getName()}"/></td>
                <td><c:out value="${customers.get(transfer.getIdReceiver()-1).getName()}"/></td>
                <td><c:out value="${transfer.getAmount()}"/></td>
                <td><c:out value="${transfer.getTransaction_fee()}"/></td>
                <td><c:out value="${transfer.getTotal_amount()}"/></td>
            </tr>
        </c:forEach>
    </table>
    <p  style="float: right;font-weight: bold; margin-right: 170px ">Total:<c:out value="${total}"/></p>
</div>
</body>
</html>
