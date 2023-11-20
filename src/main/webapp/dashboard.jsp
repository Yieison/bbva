<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
</head>
<body>
    <h2>Dashboard</h2>
    <div>
        <p>Bienvenido, <%= session.getAttribute("username") %>!</p>
        <p>Email: <%= session.getAttribute("email") %></p>
    </div>

    <h3>Lista de Facturas</h3>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Fecha</th>
                <th>Valor</th>
                <th>Tipo</th>
                <th>Observación</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="bill" items="${billList}">
                <tr>
                    <td>${bill.id}</td>
                    <td>${bill.date_bill}</td>
                    <td>${bill.value}</td>
                    <td>${bill.type}</td>
                    <td>${bill.observation}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

 
    <a href="LogoutServlet">Cerrar Sesión</a>
</body>
</html>
