package br.com.gerenciamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.gerenciamento.model.Tarefa;

public class TarefaDAO {
	
	private Connection con;
	
	public TarefaDAO(Connection con) {
		this.con = con;
	}
	
    public void criarTarefa(Integer paramId, String paramNome, Integer paramTempo) throws SQLException {
        String sql = "INSERT INTO TAREFA (id_projeto_tarefa, andamento, nome, tempo_estimado ) VALUES (?, ?, ?, ?)";
        try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstm.setInt(1, paramId);
            pstm.setString(2, "PARA FAZER");
            pstm.setString(3, paramNome);
            pstm.setInt(4, paramTempo);
            pstm.execute();
        }
    }
    
    public List<Tarefa> buscarTarefas(Integer id) throws SQLException {
        List<Tarefa> tarefas = new ArrayList<>();
        
        String sql = "SELECT * FROM TAREFA WHERE id_projeto_tarefa = ?;";
        
        try(PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.execute();
            
            try(ResultSet rst = pstm.getResultSet()) {
                while(rst.next()) {
                    Tarefa tarefa = new Tarefa(rst.getInt(1), rst.getInt(2), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getInt(6), rst.getString(7), rst.getString(8));
                    tarefas.add(tarefa);
                }
            }
        }
        return tarefas;
    }
    
    public void excluirTarefa(Integer idP, Integer idT) throws SQLException {
        String sql = "DELETE FROM TAREFA WHERE id_projeto_tarefa = ? AND id = ?;";
        try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                
            pstm.setInt(1, idP);
            pstm.setInt(2, idT);
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
    
    public void atualizarStatus(Integer id, String status, Integer idUsuario) throws SQLException {
        String sql = "";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dt1 = LocalDateTime.now().format(f);
        if (status.equals("Fazendo")) {
            sql = "UPDATE TAREFA SET andamento = ?, membro_id = ?, data_fazendo = ? WHERE id = ?;";
        } else {
            sql = "UPDATE TAREFA SET andamento = ?, membro_id = ?, data_feito = ? WHERE id = ?;";
        }
        try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            if (status.equals("Fazendo")) {
                pstm.setString(1, "FAZENDO");
                pstm.setString(3, dt1);
            } else {
                pstm.setString(1, "FEITO");
                pstm.setString(3, dt1);
            }       
            pstm.setInt(2, idUsuario);
            pstm.setInt(4, id);
            pstm.execute();
        }
    }
}
