package br.com.gerenciamento.actions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.gerenciamento.dao.ProjetoDAO;
import br.com.gerenciamento.jdbc.ConnectionFactory;

public class CriarProjeto implements Acao {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		
		String nome = req.getParameter("nomeProjeto");
		String descricao = req.getParameter("descricao");
		
		try (Connection con = new ConnectionFactory().recuperarConexao()) {
			ProjetoDAO projetoDAO = new ProjetoDAO(con);
			HttpSession sessao = req.getSession();
			Integer id = (Integer) sessao.getAttribute("idUsuario");
			projetoDAO.criarProjeto(nome, descricao, id);
		}
		return "redirect:entrada?action=Projetos";
	}
}
