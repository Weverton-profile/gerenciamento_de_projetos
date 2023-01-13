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
	
    public List<MembrosProjeto> membrosProjeto(Integer id) throws SQLException {
        List<MembrosProjeto> membrosProjeto = new ArrayList<>();
        
        String sql = "SELECT * FROM MEMBROS_PROJETO WHERE membros_id = ?";
        try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setInt(1, id);
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
    
    public List<Integer> buscarMembros(Integer id) throws SQLException {
        List<Integer> membrosProjeto = new ArrayList<>();
        
        String sql = "SELECT membros_id FROM MEMBROS_PROJETO WHERE id_projeto = ?";
        try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setInt(1, id);
            pstm.execute();
            
            try(ResultSet rst = pstm.getResultSet()) {
                if(rst != null) {
                    while(rst.next()) {
                        membrosProjeto.add(rst.getInt(1));
                    }
                }
            }
        }
        return membrosProjeto;
    }
    
    public void adicionarMembro(String idProjeto, String usuarioId) throws SQLException {
        String sql = "INSERT INTO membros_projeto (id_projeto, membros_id) VALUES (?, ?);";
        try(PreparedStatement pstm = con.prepareStatement(sql)) {
            
            pstm.setString(1, idProjeto);
            pstm.setString(2, usuarioId);
            
            pstm.execute();
        }
    }
}
