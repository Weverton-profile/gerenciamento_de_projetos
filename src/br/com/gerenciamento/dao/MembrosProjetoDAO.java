package br.com.gerenciamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.gerenciamento.model.MembrosProjeto;

public class MembrosProjetoDAO {
	
	private Connection con;
	
	public MembrosProjetoDAO(Connection con) {
		this.con = con;
	}
	
	public void adicionarMembro(Integer id_projeto, Integer id_gerente) throws SQLException {
		String sql = "INSERT INTO membros_projeto (id_projeto, membros_id) VALUES (?, ?);";
		try(PreparedStatement pstm = con.prepareStatement(sql)) {
			
			pstm.setInt(1, id_projeto);
			pstm.setInt(1, id_gerente);
			
			pstm.execute();
		}
	}
	

	public List<MembrosProjeto> membrosProjeto() throws SQLException {
		List<MembrosProjeto> membrosProjeto = new ArrayList<>();
		
		String sql = "SELECT * FROM MEMBROS_PROJETO";
		try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()) {
				if(rst != null) {
					while(rst.next()) {
						MembrosProjeto membroProjeto = new MembrosProjeto(rst.getInt(1), rst.getInt(2));
						membrosProjeto.add(membroProjeto);
					}
				}
			}
		}
		return membrosProjeto;
	}
}
