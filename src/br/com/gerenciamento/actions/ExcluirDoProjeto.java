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

public class ExcluirDoProjeto implements Acao {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		try (Connection con = new ConnectionFactory().recuperarConexao()) {
			ProjetoDAO projetoDao = new ProjetoDAO(con);
			String projetoId = req.getParameter("idProjeto");
			String usuarioId = req.getParameter("usuarioId");
			Integer idProjeto = Integer.valueOf(projetoId);
			Integer idUsuario = Integer.valueOf(usuarioId);
			HttpSession sessao = req.getSession();
			Projeto projeto = projetoDao.buscarPorId(idProjeto);
			if (sessao.getAttribute("idUsuario").equals(projeto.getGerente_id()) && !sessao.getAttribute("idUsuario").equals(usuarioId)) {
				projetoDao.excluirMembroDoProjeto(idUsuario, idProjeto);
			}
			return "redirect:entrada?action=VerTarefa&id=" + idProjeto;
		}
	}

}
