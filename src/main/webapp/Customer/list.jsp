<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <script src="/resource/js/jquery.validate.min.js"></script>
</head>
<body>
<center>
    <h1>Customer Management</h1>
</center>
<div class="container">
    <div align="center">
        <caption><h2>List of Customers</h2></caption>
        <h2><a href="/ManagerCustomer">Manager Customer</a></h2>
        <form class="needs-validation row"  method="post">
            <div class="col-3">
                <label for="validationCustom01">User Name: </label>
                <input type="text" name="name" size="45" class="form-control" id="validationCustom01" required>
            </div>
            <div class="col-3">
                <label>Phone:</label>
                <input type="text" name="phone" id="phone" size="45" class="form-control" required>
            </div>
            <div class="col-3">
                <label>Email:</label>
                <input type="text" name="email" id="email" size="45" class="form-control" required>
            </div>
            <div class="col-3">
                <label>Salary:</label>
                <input type="text" disabled name="salary" value="0" class="form-control" required>
            </div>
            <div class="col-4">
                <button  type="submit" class="btn btn-success">ADD NEW CUSTOMER</button>
            </div>
        </form>
    </div>
    <div class="row justify-content-between">
        <form class="form-inline col-4" method="post" action="/banking_system?action=search">
            <input type="text" class="form-control" name="search" placeholder="Searching ...">
            <button type="submit" class="btn btn-success mr-3">SEARCH</button>
        </form>
    </div>
    <div class="container">
        <table class="table table-striped">
            <thead class="thead-dark">
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Phone</th>
            <th scope="col">Email</th>
            <th scope="col">Salary</th>
            <th scope="col">Actions</th>
            </thead>
            </thead>

            <c:forEach var="user" items="${customers}">
                <tr>
                    <td><c:out value="${user.getId()}"/></td>
                    <td><c:out value="${user.getName()}"/></td>
                    <td><c:out value="${user.getPhone()}"/></td>
                    <td><c:out value="${user.getEmail()}"/></td>
                    <td><fmt:formatNumber value="${user.getSalary()}" type="currency"
                                          currencySymbol="$"></fmt:formatNumber></td>
                    <td>
                        <a class="btn btn-warning" href="/banking_system?action=edit&id=${user.id}" title="Edit"><i
                                class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
                        <a class="btn btn-success" href="/banking_system?action=transfer&id=${user.id}"
                           title="Transfer"><i
                                class="fa fa-exchange" aria-hidden="true"></i></a>
                        <a class="btn btn-danger" href="/banking_system?action=delete&id=${user.id}" title="Delete"><i
                                class="fa fa-trash-o" aria-hidden="true"></i></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="message">
        <c:if test='${requestScope["success"] != null}'>
            <div id="div1" class="alert alert-success" role="alert">
                    ${requestScope["success"]}
            </div>
        </c:if>
        <c:if test='${requestScope["error"] != null}'>
            <div id="div1" class="alert alert-danger" role="alert">
                    ${requestScope["error"]}
            </div>
        </c:if>
    </div>
</div>
<%@include file="validate.jsp"%>
</body>
</html>
