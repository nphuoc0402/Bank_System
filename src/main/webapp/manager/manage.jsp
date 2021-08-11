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
    <h1>Customer Management</h1>
    <h2><a href="/banking_system">List Customer</a></h2>
<div class="container">
    <div class="container">
        <a class="btn btn-warning" href="/ManagerCustomer?action=revenue" title="Revenue">Revenue</a>
        <table class="table table-striped">
            <thead class="thead-dark">
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Phone</th>
            <th scope="col">Email</th>
            <th scope="col">Deposit</th>
            <th scope="col">Withdraw</th>
            <th scope="col">Salary</th>
            </thead>
            <c:forEach var="user" items="${customers}">
                <tr>
                    <td><c:out value="${user.getId()}"/></td>
                    <td><c:out value="${user.getName()}"/></td>
                    <td><c:out value="${user.getPhone()}"/></td>
                    <td><c:out value="${user.getEmail()}"/></td>*-/
                    <td><a class="btn btn-warning" href="/ManagerCustomer?action=deposit&id=${user.id}" title="Edit">
                        <i class="far fa-plus-square"></i>
                    </a></td>
                    <td><a class="btn btn-warning" href="/ManagerCustomer?action=withdraw&id=${user.id}" title="Edit">
                        <i class="fas fa-calculator"></i>
                    </a></td>
                    <td><fmt:formatNumber value="${user.getSalary()}" type="currency"
                                          currencySymbol="$"></fmt:formatNumber></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="message">
        <c:if test='${requestScope["success"] != null}'>
            <div class="alert alert-success" role="alert" style="position: fixed; bottom: 0; right: 0; left: 0">
                    ${requestScope["success"]}
            </div>

        </c:if>
        <c:if test='${requestScope["error"] != null}'>
            <div class="alert alert-danger" role="alert" style="position: fixed; bottom: 0; right: 0; left: 0">
                    ${requestScope["error"]}
            </div>
        </c:if>
    </div>
</div>
    <script>
        $('.alert-success').delay(4 * 1000).slideUp(500, function () {
            $('this').alert('close');
        });

        $('.alert-danger').delay(4 * 1000).slideUp(500, function () {
            $('this').alert('close');
        });
    </script>
</body>
</html>
