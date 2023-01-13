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
	
    public void criarProjeto(String nome, String descricao, Integer gerente_id) throws SQLException {
        
        String sql = "call criar_projeto(?, ?, ?);";
        try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstm.setString(1, nome);
            pstm.setString(2, descricao);
            pstm.setInt(3, gerente_id);
            pstm.execute();
        }
    }
    
    public List<Projeto> listar(Integer id) throws SQLException {
        List<Projeto> projetos = new ArrayList<>();
        
        String sql = "SELECT id, nome, descricao, gerente_id, andamento FROM projeto p left join membros_projeto mp on (p.id = mp.id_projeto) WHERE membros_id = ?;";
        
        try(PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, id);
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

    public void excluirProjeto(Integer id) throws SQLException {

        String sql = "call deletar_projeto(?)";
        try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstm.setInt(1, id);
            pstm.execute();
        }   
    }

    public void excluirMembroDoProjeto(Integer usuarioId, Integer idProjeto) throws SQLException {
        String sql = "call excluir_membro_do_projeto(?, ?);";
        try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstm.setInt(1, usuarioId);
            pstm.setInt(2, idProjeto);
            pstm.execute();
        }   
    }

    public void atualizarProjeto(Integer idP, String andamento) throws SQLException {
        String sql = "UPDATE projeto SET andamento = ? WHERE id = ?;";
        try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstm.setString(1, andamento);
            pstm.setInt(2, idP);
            pstm.execute();
        }
    }   
}
