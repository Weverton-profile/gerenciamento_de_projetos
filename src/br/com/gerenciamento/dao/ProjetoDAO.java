package br.com.gerenciamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.gerenciamento.model.Projeto;

public class ProjetoDAO {
	
	private Connection con;
	
	public ProjetoDAO(Connection con) {
		this.con = con;
	}
	
	public List<Projeto> listar() throws SQLException {
		List<Projeto> projetos = new ArrayList<>();
		
		String sql = "SELECT * FROM PROJETO";
		
		try(PreparedStatement pstm = con.prepareStatement(sql)) {
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()) {
				while(rst.next()) {
					Projeto projeto = new Projeto(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5));
					projetos.add(projeto);
				}
			}
		}
		return projetos;
	}
	
	public Projeto buscarPorId(Integer id) throws SQLException {
		String sql = "SELECT * FROM PROJETO WHERE id = ?";
		Projeto projeto = null;
		try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			pstm.setInt(1, id);
			
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()) {
				while(rst.next()) {
					projeto = new Projeto(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5));
				}
			}
		}
		return projeto;
	}
	
}
