package br.com.gerenciamento.actions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gerenciamento.dao.UsuarioDAO;
import br.com.gerenciamento.jdbc.ConnectionFactory;

public class Inscricao implements Acao {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
	
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		String senha = req.getParameter("senha");
		
		try (Connection con = new ConnectionFactory().recuperarConexao()) {
			UsuarioDAO usuarioDAO = new UsuarioDAO(con);
			if(usuarioDAO.encontrarUsuarioEmail(email) != null) {
				req.setAttribute("mensagem", "E-mail ja cadastrado.");
				return "forward:formCadastraUsuario.jsp";
			} else {
				usuarioDAO.criarNovoUsuario(nome, email, senha);
				req.setAttribute("mensagem", "Cadastro efetuado com sucesso!");
				return "forward:logar.jsp";
			}
		}
	}

}
