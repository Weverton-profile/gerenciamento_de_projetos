package br.com.gerenciamento.actions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gerenciamento.dao.UsuarioDAO;
import br.com.gerenciamento.jdbc.ConnectionFactory;
import br.com.gerenciamento.model.Usuario;

public class BuscarUsuario implements Acao {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		
		String stringUsuario = req.getParameter("stringUsuario");
		stringUsuario = stringUsuario.concat("%");
		stringUsuario = ("%").concat(stringUsuario);
		String idProjeto = req.getParameter("idProjeto");

		try (Connection con = new ConnectionFactory().recuperarConexao()) {
			UsuarioDAO usuarioDAO = new UsuarioDAO(con);
			List<Usuario> usuarios = usuarioDAO.retornarDadosUsuarios(stringUsuario);
			req.setAttribute("usuariosRetornados", usuarios);
			req.setAttribute("idProjeto", idProjeto);
		}
		return "forward:listarBuscaDeUsuarios.jsp";
	}

}
