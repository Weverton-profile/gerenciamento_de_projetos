package br.com.gerenciamento.actions;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout implements Acao {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {

		HttpSession sessao = req.getSession();
		sessao.removeAttribute("usuarioLogado");
		sessao.invalidate();
		return "redirect:Bem-vindo.jsp";
	}

}
