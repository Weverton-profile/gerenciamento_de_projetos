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
import br.com.gerenciamento.model.Projeto;

public class ExcluirProjeto implements Acao {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		try (Connection con = new ConnectionFactory().recuperarConexao()) {
			ProjetoDAO projetoDao = new ProjetoDAO(con);
			String paramId = req.getParameter("id");
			Integer id = Integer.valueOf(paramId);
			HttpSession sessao = req.getSession();
			Projeto projeto = projetoDao.buscarPorId(id);
			if (sessao.getAttribute("idUsuario").equals(projeto.getGerente_id())) {
				projetoDao.excluirProjeto(id);
			}
			return "redirect:entrada?action=Projetos";
		}
	}

}
