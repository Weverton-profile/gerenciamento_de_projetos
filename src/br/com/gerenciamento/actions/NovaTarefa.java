package br.com.gerenciamento.actions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gerenciamento.dao.TarefaDAO;
import br.com.gerenciamento.jdbc.ConnectionFactory;

public class NovaTarefa implements Acao {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		try (Connection con = new ConnectionFactory().recuperarConexao()) {
			TarefaDAO tarefaDao = new TarefaDAO(con);
			String paramId = req.getParameter("idProjeto");
			String paramNome = req.getParameter("nomeTarefa");
			String paramTempo = req.getParameter("tempoTarefa");
			Integer id = Integer.valueOf(paramId);
			Integer tempo = Integer.valueOf(paramTempo);
			tarefaDao.criarTarefa(id, paramNome, tempo);
			return "redirect:entrada?action=VerTarefa&id=" + paramId;
		}
	}

}
