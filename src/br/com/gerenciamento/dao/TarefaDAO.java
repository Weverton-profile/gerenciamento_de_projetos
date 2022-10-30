package br.com.gerenciamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.gerenciamento.model.Tarefa;

public class TarefaDAO {
	
	private Connection con;
	
	public TarefaDAO(Connection con) {
		this.con = con;
	}

	public void criarTarefa(Integer paramId, String paramNome) throws SQLException {
		String sql = "INSERT INTO TAREFA (id_projeto, andamento, nome) VALUES (?, ?, ?)";
		try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			pstm.setInt(1, paramId);
			pstm.setString(2, "PARA FAZER");
			pstm.setString(3, paramNome);
			pstm.execute();
		}
	}

	public List<Tarefa> buscarTarefas(Integer id) throws SQLException {
		List<Tarefa> tarefas = new ArrayList<>();
		
		String sql = "SELECT * FROM TAREFA WHERE id_projeto = ?;";
		
		try(PreparedStatement pstm = con.prepareStatement(sql)) {
			pstm.setInt(1, id);
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()) {
				while(rst.next()) {
					Tarefa tarefa = new Tarefa(rst.getInt(1), rst.getInt(2), rst.getString(3), rst.getString(4), rst.getInt(5));
					tarefas.add(tarefa);
				}
			}
		}
		return tarefas;
	}

	public void atualizarStatus(Integer id, String status, Integer idUsuario) throws SQLException {
		String sql = "UPDATE TAREFA SET andamento = ?, membro_id = ? WHERE id = ?;";
		try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			if (status.equals("Fazendo")) {
				pstm.setString(1, "FAZENDO");
			} else {
				pstm.setString(1, "FEITO");
			}		
			pstm.setInt(2, idUsuario);
			pstm.setInt(3, id);
			pstm.execute();
		}
	}

	public Integer buscarTarefa(Integer id) throws SQLException {
		Integer tarefa = null;
		String sql = "SELECT membro_id FROM TAREFA WHERE id = ?;";
		
		try(PreparedStatement pstm = con.prepareStatement(sql)) {
			pstm.setInt(1, id);
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()) {
				while(rst.next()) {
					tarefa = rst.getInt(1);
				}
			}
		}
		return tarefa;
	}

	public void excluirTarefa(Integer idP, Integer idT) throws SQLException {
		String sql = "DELETE FROM TAREFA WHERE id_projeto = ? AND id = ?;";
		try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				
			pstm.setInt(1, idP);
			pstm.setInt(2, idT);
			pstm.execute();
		}	
	}
}
