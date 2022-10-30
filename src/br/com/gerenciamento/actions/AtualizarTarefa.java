package br.com.gerenciamento.actions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.gerenciamento.dao.TarefaDAO;
import br.com.gerenciamento.jdbc.ConnectionFactory;

public class AtualizarTarefa  implements Acao {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		try (Connection con = new ConnectionFactory().recuperarConexao()) {
			TarefaDAO tarefaDao = new TarefaDAO(con);
			String paramId = req.getParameter("idTarefa");
			String paramIdP = req.getParameter("idProjeto");
			String paramStatus = req.getParameter("status");
			Integer id = Integer.valueOf(paramId);
			HttpSession sessao = req.getSession();
			Integer idUsuario = (Integer) sessao.getAttribute("idUsuario");
			
			Integer idUsuarioTarefa = tarefaDao.buscarTarefa(id);
			if(paramStatus.equals("Fazendo")) {
				tarefaDao.atualizarStatus(id, paramStatus, idUsuario);
			} else if (paramStatus.equals("Feito")) {
				if(idUsuario == idUsuarioTarefa) {
					tarefaDao.atualizarStatus(id, paramStatus, idUsuario);
				}
			}
			return "redirect:entrada?action=VerTarefa&id=" + paramIdP;
		}
	}
	
}
