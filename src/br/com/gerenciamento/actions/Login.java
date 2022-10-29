package br.com.gerenciamento.actions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.gerenciamento.dao.UsuarioDAO;
import br.com.gerenciamento.jdbc.ConnectionFactory;
import br.com.gerenciamento.model.Usuario;

public class Login implements Acao {
	
	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException {

		String email = req.getParameter("email");
		String senha = req.getParameter("senha");
		Usuario usuario = null;
		try (Connection con = new ConnectionFactory().recuperarConexao()) {
			UsuarioDAO usuarioDAO = new UsuarioDAO(con);
			usuario = usuarioDAO.encontrarUsuarioEmailSenha(email, senha);
		}
		if (usuario != null) {
			HttpSession sessao = req.getSession();
			sessao.setAttribute("usuarioLogado", usuario);
			sessao.setAttribute("idUsuario", usuario.getId());
			return "redirect:entrada?action=Projetos";
		} else {
			return "redirect:entrada?action=Logar";
		}

	}
}
