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
import br.com.gerenciamento.dao.TarefaDAO;
import br.com.gerenciamento.dao.UsuarioDAO;
import br.com.gerenciamento.jdbc.ConnectionFactory;
import br.com.gerenciamento.model.Projeto;
import br.com.gerenciamento.model.Tarefa;
import br.com.gerenciamento.model.Usuario;

public class VerTarefa implements Acao {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		try (Connection con = new ConnectionFactory().recuperarConexao()) {
			ProjetoDAO projetoDao = new ProjetoDAO(con);
			TarefaDAO tarefaDao = new TarefaDAO(con);
			UsuarioDAO usuarioDAO = new UsuarioDAO(con);
			MembrosProjetoDAO membrosProjetoDAO = new MembrosProjetoDAO(con);
			String paramId = req.getParameter("id");
			Integer id = Integer.valueOf(paramId);
			HttpSession sessao = req.getSession();
			
			List<Integer> membros_id = membrosProjetoDAO.buscarMembros(id);
			List<Tarefa> tarefas = tarefaDao.buscarTarefas(id);
			if(membros_id.contains(sessao.getAttribute("idUsuario"))) {
				Projeto projeto = projetoDao.buscarPorId(id);
				req.setAttribute("projeto", projeto);
				req.setAttribute("tarefas", tarefas);
				List<Usuario> usuarios = usuarioDAO.pegarTodosOsMembros(paramId);
				req.setAttribute("usuarios", usuarios);
				return "forward:verTarefa.jsp";
			} else {
				return "redirect:entrada?action=Projetos";
			}
		}
	}

}
