package co.bbva.test.controller;

import co.bbva.test.dao.UsuarioDaoPostgreSQL;
import co.bbva.test.modelo.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UsuarioDaoPostgreSQL usuarioDao = new UsuarioDaoPostgreSQL();
		Usuario usuario = usuarioDao.checkLogin(username, password);

		if (usuario != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", usuario);
			response.sendRedirect("dashboard.jsp"); // Redirige al usuario a una página de inicio después del login
		} else {
			String errorMessage = "Invalid username or password";
			request.setAttribute("error", errorMessage);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
