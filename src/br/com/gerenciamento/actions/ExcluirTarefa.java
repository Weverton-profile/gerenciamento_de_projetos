package br.com.gerenciamento.actions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.gerenciamento.dao.ProjetoDAO;
import br.com.gerenciamento.dao.TarefaDAO;
import br.com.gerenciamento.jdbc.ConnectionFactory;
import br.com.gerenciamento.model.Projeto;

public class ExcluirTarefa implements Acao {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		try (Connection con = new ConnectionFactory().recuperarConexao()) {
			TarefaDAO tarefaDao = new TarefaDAO(con);
			ProjetoDAO projetoDao = new ProjetoDAO(con);
			String paramId = req.getParameter("idTarefa");
			String paramIdP = req.getParameter("idProjeto");
			String paramAndamento = req.getParameter("andamento");
			Integer idT = Integer.valueOf(paramId);
			Integer idP = Integer.valueOf(paramIdP);
			HttpSession sessao = req.getSession();
			Integer idUsuario = (Integer) sessao.getAttribute("idUsuario");
			
			Projeto projeto = projetoDao.buscarPorId(idP);
			if(projeto.getGerente_id() == idUsuario && paramAndamento != "") {	
				tarefaDao.excluirTarefa(idP, idT);
			}
			return "redirect:entrada?action=VerTarefa&id=" + paramIdP;
		}
	}

}
