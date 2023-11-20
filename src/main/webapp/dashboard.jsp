<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="co.empresa.bbva.modelo.Bill"%>
<%@ page import="co.empresa.bbva.modelo.Usuario"%>
<%@ page import="co.bbva.test.dao.UsuarioDaoPostgreSQL"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard BBVA</title>
<style type="text/css">
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.dashboard-container {
	width: 80%;
	max-width: 600px;
	background-color: #fff;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
	text-align: center;
}

.header {
	margin-bottom: 20px;
}

.movements-list {
	text-align: left;
	margin-bottom: 20px;
}

.movement-item {
	display: flex;
	justify-content: space-between;
	align-items: center;
	border-bottom: 1px solid #ddd;
	padding: 10px 0;
}

.movement-date {
	display: block;
	color: #666;
	margin-bottom: 5px;
}

.movement-description {
	display: block;
	color: #333;
	margin-bottom: 5px;
}

.movement-amount {
	font-weight: bold;
}

.income {
	color: #28a745;
}

.expense {
	color: #dc3545;
}

.see-more-button {
	background-color: #004a99;
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.see-more-button:hover {
	background-color: #003366;
}

.add-movement-button-container {
	text-align: center;
	padding-top: 20px;
}

.add-movement-button {
	background-color: #007bff;
	border: none;
	border-radius: 50%;
	padding: 15px 20px;
	font-size: 24px;
	line-height: 1;
	color: #fff;
	cursor: pointer;
	width: 50px;
	height: 50px;
	position: fixed;
	right: 30px;
	bottom: 30px;
}

.add-movement-button:hover {
	background-color: #0056b3;
}
</style>
</head>

<body>
	<%
	Usuario usuario = (Usuario) session.getAttribute("user");
	if (usuario != null) {
		UsuarioDaoPostgreSQL usuarioDao = new UsuarioDaoPostgreSQL();
		List<Bill> bills = usuarioDao.selectBillsByUser(usuario.getId());
	%>
	<div class="dashboard-container">
		<div class="header">
			<img
				src="data:image/png;base64,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"
				alt="BBVA Logo" style="width: 150px;">
			<h1>Movimientos</h1>
		</div>
		<div class="movements-list">
			<%
			for (Bill bill : bills) {
			%>
			<div class="movement-item">
				<span class="movement-date"><%=bill.getDateBill()%></span> <span
					class="movement-description"><%=bill.getObservation()%></span> <span
					class="movement-amount <%=bill.getType() == 1 ? "income" : "expense"%>">
					<%=bill.getType() == 1 ? "+" : "-"%> $<%=bill.getValue()%>
				</span>
			</div>
			<%
			}
			%>

			<div class="see-more-container">
				<button class="see-more-button" onclick="loadMoreMovements()">Ver
					más</button>
			</div>
		</div>
		<div class="add-movement-button-container">
			<button id="addMovementButton" class="add-movement-button"
				onclick="showAddModal()">+</button>
		</div>
	</div>

<%
	} else {
	response.sendRedirect("login.jsp");
	}
	%>
</body>
</html>
