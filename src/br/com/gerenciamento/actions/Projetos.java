package br.com.gerenciamento.actions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gerenciamento.dao.ProjetoDAO;
import br.com.gerenciamento.jdbc.ConnectionFactory;
import br.com.gerenciamento.model.Projeto;


public class Projetos implements Acao {
	
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		try (Connection con = new ConnectionFactory().recuperarConexao()) {
			ProjetoDAO projetoDao = new ProjetoDAO(con);
			List<Projeto> listaDeProjetos = projetoDao.listar();
			
			req.setAttribute("projetos", listaDeProjetos);

			return "forward:projetos.jsp";
		}	
	}
	
}
