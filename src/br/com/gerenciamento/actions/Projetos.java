package br.com.gerenciamento.actions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.gerenciamento.dao.MembrosProjetoDAO;
import br.com.gerenciamento.dao.ProjetoDAO;
import br.com.gerenciamento.jdbc.ConnectionFactory;
import br.com.gerenciamento.model.MembrosProjeto;
import br.com.gerenciamento.model.Projeto;


public class Projetos implements Acao {
	
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		try (Connection con = new ConnectionFactory().recuperarConexao()) {
			ProjetoDAO projetoDao = new ProjetoDAO(con);
			MembrosProjetoDAO membrosProjetoDao = new MembrosProjetoDAO(con);
			List<Projeto> listaDeProjetos = projetoDao.listar();
			HttpSession sessao = req.getSession();
			Integer id = (Integer) sessao.getAttribute("idUsuario");
			List<MembrosProjeto> membrosDoProjeto = membrosProjetoDao.membrosProjeto(id);
			req.setAttribute("projetos", listaDeProjetos);
			req.setAttribute("membrosDoProjeto", membrosDoProjeto);

			return "forward:projetos.jsp";
		}	
	}
	
}
