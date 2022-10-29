package br.com.gerenciamento.actions;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NovoProjeto implements Acao {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {

		return "forward:novoProjeto.jsp";
	}

}
