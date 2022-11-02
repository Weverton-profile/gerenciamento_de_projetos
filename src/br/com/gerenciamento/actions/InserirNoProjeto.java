package br.com.gerenciamento.actions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gerenciamento.dao.MembrosProjetoDAO;
import br.com.gerenciamento.dao.UsuarioDAO;
import br.com.gerenciamento.jdbc.ConnectionFactory;
import br.com.gerenciamento.model.Usuario;

public class InserirNoProjeto implements Acao {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		String usuarioId = req.getParameter("usuarioId");
		String idProjeto = req.getParameter("idProjeto");

		try (Connection con = new ConnectionFactory().recuperarConexao()) {
			MembrosProjetoDAO membrosProjetoDAO = new MembrosProjetoDAO(con);
			UsuarioDAO usuarioDAO = new UsuarioDAO(con);
			membrosProjetoDAO.adicionarMembro(idProjeto, usuarioId);
			List<Usuario> usuarios = usuarioDAO.pegarTodosOsMembros(idProjeto);
			req.setAttribute("usuarios", usuarios);
			return "redirect:entrada?action=VerTarefa&id=" + idProjeto;
		}
	}

}
